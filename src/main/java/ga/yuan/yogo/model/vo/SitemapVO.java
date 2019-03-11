package ga.yuan.yogo.model.vo;

import ga.yuan.yogo.model.dto.YogoConst;
import lombok.Data;

import java.util.Date;

/**
 * 用于生成网站地图
 */
@Data
public class SitemapVO {

    private String slug;
    private Date created;

    public String getUrl() {
        return YogoConst.OPTIONS.get("site_url") + slug;
    }

}
