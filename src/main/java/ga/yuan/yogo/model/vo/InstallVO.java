package ga.yuan.yogo.model.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 博客安装页面的表单
 */
@Data
public class InstallVO {
    private String siteTitle;
    @Email
    private String email;
    @NotNull
    @Size(max = 24)
    private String username;
    private String displayName;
    @NotNull
    @Size(min = 8, max = 24)
    private String password;
}
