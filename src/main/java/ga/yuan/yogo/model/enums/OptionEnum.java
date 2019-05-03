package ga.yuan.yogo.model.enums;

/**
 * OptionDO 枚举类，key 对应在数据库中表示的字段名 name
 */
public enum OptionEnum {
    SITE_TITLE("site_title"),
    SITE_URL("site_url"),
    SITE_DESCRIPTION("site_description"),
    POST_PER_PAGE("post_per_page"),
    POST_PER_RSS("post_per_rss");

    private String key;

    OptionEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
