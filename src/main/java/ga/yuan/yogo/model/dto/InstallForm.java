package ga.yuan.yogo.model.dto;

import lombok.Data;

/**
 * 博客安装页面的表单
 */
@Data
public class InstallForm {
    private String siteTitle;
    private String username;
    private String password;
    private String email;

}
