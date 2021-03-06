package ga.yuan.yogo.model.enums;

/**
 * OptionDO 枚举类，name 对应在数据库中表示的字段名 name
 * SITE_URL: 不包含最后的斜杠，如 http://localhost:8080
 */
public enum OptionEnum {
    SITE_TITLE("site_title"),
    SITE_URL("site_url"),
    SITE_DESCRIPTION("site_description"),
    POST_PER_PAGE("post_per_page"),
    POST_PER_RSS("post_per_rss");

    private String name;

    OptionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
