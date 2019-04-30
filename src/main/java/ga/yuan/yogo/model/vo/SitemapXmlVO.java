package ga.yuan.yogo.model.vo;

import ga.yuan.yogo.consts.YogoConst;
import lombok.Value;

import java.util.Date;

/**
 * 用于生成网站地图，使用 Spring Data JPA {@code projection} 特性
 */
@Value
public class SitemapXmlVO {
    String slug;
    Date created;

    public String getUrl() {
        return YogoConst.OPTIONS.get("site_url") + getSlug();
    }
}
