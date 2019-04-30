package ga.yuan.yogo.model.vo;

import lombok.Data;

/**
 * 博客安装页面的表单
 */
@Data
public class InstallVO {
    private String siteTitle;
    private String email;
    private String username;
    private String displayName;
    private String password;
}
