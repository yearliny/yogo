package ga.yuan.yogo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Utils class, use for generate gravatar url
 * <p>
 * String email = "someone@somewhere.com";
 * String hash = CommonUtil.md5Hex(email);
 */
@Slf4j
public class CommonUtil {
    /**
     * InputStream 转换为 String
     *
     * @param is 需要转换的 InputStream
     * @return 转换好的 String
     */
    public static String strFromInputStream(InputStream is) {
        var textBuilder = new StringBuilder();
        var inputStreamReader = new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name()));
        try (Reader reader = new BufferedReader(inputStreamReader)) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return textBuilder.toString();
    }

    private static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b
                    & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    private static String md5Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 生成 Gravatar 头像地址，默认使用七牛云 CDN
     *
     * @param email 根据 email 生成头像地址
     * @param size  头像的尺寸（px）range(1, 2048)
     * @return url 头像的地址
     */
    public static String getGravatar(String email, int size) {
        return getGravatar(email, size, "https://dn-qiniu-avatar.qbox.me/avatar/");
    }

    /**
     * 生成 Gravatar 头像地址
     *
     * @param email 根据 email 生成头像地址
     * @param size  头像的尺寸（px）range(1, 2048)
     * @param cdn   使用哪家 Gravatar CDN 镜像，必须含有 http(s):// 以及网址结尾的 /，
     *              如"https://dn-qiniu-avatar.qbox.me/avatar/"
     * @return url 头像的地址
     */
    public static String getGravatar(String email, int size, String cdn) {
        if (cdn.endsWith("/") && cdn.startsWith("https://") || cdn.startsWith("http://")) {
            throw new IllegalArgumentException("不合法的网址！");
        }
        if (size < 1 || size > 2048) {
            throw new IllegalArgumentException("尺寸区间为 1~2048 ！");
        }
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(String.format("%s{md5hex}", cdn))
                .queryParam("s", size)
                .queryParam("r", "g")
                .queryParam("d", "identicon")
                .encode()
                .buildAndExpand(md5Hex(email));
        return uriComponents.toString();
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
}
