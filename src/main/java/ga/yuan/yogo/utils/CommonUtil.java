package ga.yuan.yogo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
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
    private static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b
                    & 0xFF) | 0x100).substring(1, 3));
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
     * @param email  根据 email 生成头像地址
     * @param size   头像的尺寸（px）range(1, 2048)
     * @param orElse 如果此 email 下没有设定头像，随机生成的策略。@see <a href="https://cn.gravatar.com/site/implement/images/#default-image">default-image</a>
     * @return url 头像的地址
     */
    public static String getGravatar(String email, int size, String orElse) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("https://dn-qiniu-avatar.qbox.me/avatar/{md5hex}")
                .queryParam("s", size)
                .queryParam("r", "g").queryParam("d", orElse)
                .encode()
                .buildAndExpand(md5Hex(email));
        return uriComponents.toString();
    }

    public static String getGravatar(String email, int size) {
        return getGravatar(email, size, "identicon");
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
