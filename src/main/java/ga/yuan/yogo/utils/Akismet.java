package ga.yuan.yogo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ga.yuan.yogo.model.enums.CommentStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Anti-Spam tool @see <a href="https://akismet.com">Akismet</a>
 */
@Slf4j
public class Akismet {
    private final String API_URL = "https://rest.akismet.com/1.1/";
    private Map<String, String> param = new HashMap<>();
    private HttpClient client = HttpClient.newHttpClient();

    public Akismet() {
    }

    /**
     * param.put("key", "f9e206045640");
     * param.put("blog", "https://yuan.ga");
     *
     * @param key  The API key being verified for use with our API endpoint.
     * @param blog The front page or home URL of the instance making the request. For a blog, site, or wiki this would be the front page. Note: Must be a full URI, including http://.
     */
    public Akismet(String key, String blog) {
        param.put("key", key);
        param.put("blog", blog);
    }

    /**
     * 把 Map 转化为 Json
     *
     * @param map 需要转换的 Map<String, String>
     * @return Json格式的String字符串
     */
    private static String toJson(Map<String, String> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = "";
        try {
            data = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return data;
    }

    private static String postJSON(String url,
                                   Map<String, String> map)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(toJson(map)))
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
            String blog = URLEncoder.encode("https://yuan.ga", StandardCharsets.UTF_8);
            String param = String.format("key=%s&blog=%s", this.param.get("key"), blog);
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://rest.akismet.com/1.1/verify-key"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(param))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
            if (result.equals("invalid")) {
                log.error("X-akismet-debug-help: " + response.headers().map().get("X-akismet-debug-help").toString());
            } else if (result.equals("valid")) {
                log.info("Akismet is valid!");
            }
            System.out.println(result);
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
