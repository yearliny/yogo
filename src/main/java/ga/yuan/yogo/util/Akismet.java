package ga.yuan.yogo.util;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Anti-Spam tool
 *
 * @see <a href="https://akismet.com">Akismet</a>
 */
@SuppressWarnings("WeakerAccess")
@Slf4j
public class Akismet {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String key;
    private final String blog;

    /**
     * verifyParam.put("key", "f9e206045640");
     * verifyParam.put("blog", "https://yuan.ga");
     *
     * @param key  The API key being verified for use with our API endpoint.
     * @param blog The front page or home URL of the instance making the request. For a blog, site, or wiki this would be the front page. Note: Must be a full URI, including http://.
     */
    public Akismet(String key, String blog) {
        this.key = key;
        this.blog = blog;
    }

    /**
     * 把一个 map 使用 url encode 转码成字符串
     *
     * @param map 需要转码的map
     * @return 转码后的字符串
     */
    private static String encodeMap(Map<String, String> map) {
        StringBuilder result = new StringBuilder();
        // 过滤空参数
        Map<String, String> filtered = map.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<String, String> e : filtered.entrySet()) {
            String param = String.format("%s=%s&", e.getKey(), URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8));
            result.append(param);
        }
        return result.substring(0, result.length() - 1);
    }

    private String getCommentParam(CommentDO comment) {
        Map<String, String> param = new HashMap<>();
        param.put("blog", this.blog);
        param.put("user_ip", comment.getIp());
        param.put("user_agent", comment.getAgent());
        param.put("referrer", comment.getReferrer());
        param.put("comment_type", "comment");
        param.put("comment_author", comment.getAuthor());
        param.put("comment_author_email", comment.getEmail());
        param.put("comment_author_url", comment.getUrl());
        param.put("comment_content", comment.getBody());
        return encodeMap(param);
    }

    private String getVerifyParam() {
        Map<String, String> param = new HashMap<>();
        param.put("key", key);
        param.put("blog", blog);
        return encodeMap(param);
    }

    /**
     * 验证 API_KEY 是否有效
     *
     * @return 如果有效返回 true
     */
    public boolean verify() {
        String result = "";
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://rest.akismet.com/1.1/verify-key"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(getVerifyParam()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            result = response.body();
            if (result.equals("invalid")) {
                log.error("X-akismet-debug-help: " + response.headers().map().get("X-akismet-debug-help").toString());
            } else if (result.equals("valid")) {
                log.info("Akismet is valid!");
            }
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        return result.equals("valid");
    }

    /**
     * 检查评论是否是垃圾评论
     *
     * @return 评论状态[同意、待定、垃圾评论、回收站]
     */
    public CommentStatusEnum check(CommentDO comment) {
        String API_URL = String.format("https://%s.rest.akismet.com/1.1/comment-check", key);
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getCommentParam(comment)))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.body()) {
                case "true":
                    return CommentStatusEnum.SPAM;
                case "false":
                    return CommentStatusEnum.APPROVE;
                case "invalid":
                    log.error("CommentDO {} -> X-akismet-debug-help: {}", comment.getCoid(), response.headers().map().get("X-akismet-debug-help").toString());
            }
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        return CommentStatusEnum.HOLD;
    }

    /**
     * 提交被误判的评论
     */
    private void submitComment(CommentDO comment, CommentType type) {
        final String HAM_URL = String.format("https://%s.rest.akismet.com/1.1/submit-ham", key);
        final String SPAM_URL = String.format("https://%s.rest.akismet.com/1.1/submit-spam", key);
        String API_URL;

        switch (type) {
            case HAM:
                API_URL = HAM_URL;
                break;
            case SPAM:
                API_URL = SPAM_URL;
                break;
            default:
                log.error("Unknown comment type");
                return;
        }

        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getCommentParam(comment)))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().equals("Thanks for making the web a better place.")) {
                log.info("comment {} submit success!", comment.getCoid());
            }
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 提交漏判的垃圾评论
     */
    public void submitSpam(CommentDO comment) {
        submitComment(comment, CommentType.SPAM);
    }

    /**
     * 提交误判的正常评论
     */
    public void submitHam(CommentDO comment) {
        submitComment(comment, CommentType.HAM);
    }
}

/**
 * 评论类型，垃圾评论或误判评论
 */
enum CommentType {
    SPAM, HAM;
}