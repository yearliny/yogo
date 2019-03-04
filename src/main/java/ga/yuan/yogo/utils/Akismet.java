package ga.yuan.yogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.yuan.yogo.model.enums.CommentStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Anti-Spam tool @see <a href="https://akismet.com">Akismet</a>
 */
@Slf4j
public class Akismet {
    private final String API_URL = "https://rest.akismet.com/1.1/";
    private Map<String, String> param = new HashMap<>();

    public Akismet() {
    }

    public Akismet(String API_KEY, String SITE_URL) {
        param.put("key", "f9e206045640");
        param.put("blog", "https://yuan.ga");
    }

    private static String postJSON(String url,
                                   Map<String, String> map)
            throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        return HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();
    }

    /**
     * 验证 API_KEY 是否有效
     *
     * @return 如果有效返回 true
     */
    public boolean verify() {
        String result = "";
        try {
            result = postJSON("https://rest.akismet.com/1.1/verify-key", param);
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
    public CommentStatus check() {
        return null;
    }

    /**
     * 提交被误判的评论
     *
     * @return 提交成功则返回 true
     */
    public boolean submitHam() {
        return false;
    }

    /**
     * 提交漏判的垃圾评论
     *
     * @return 提交成功则返回 true
     */
    public boolean submitSpam() {
        return false;
    }
}
