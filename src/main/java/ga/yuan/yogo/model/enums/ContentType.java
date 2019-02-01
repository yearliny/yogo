package ga.yuan.yogo.model.enums;

import java.util.stream.Stream;

/**
 * content 类型
 * 文章：post
 * 页面：page
 * 附件：attachment
 * 版本：revision
 */
public enum ContentType {
    POST, PAGE, ATTACHMENT, REVISION;

    public static ContentType of(String s) {
        return Stream.of(values())
                .filter(contentType -> contentType.equals(ContentType.valueOf(s.toUpperCase())))
                .findFirst()
                .orElse(null);
    }
}