package ga.yuan.yogo.model.vo;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 博客安装页面的表单
 */
@Value
public class InstallVO {
    String siteTitle;
    String siteUrl;
    String siteDescription;
    @Email(message = "{valida.emailError}")
    @NotBlank(message = "{valida.usernameEmpty}")
    String email;
    @NotNull
    @Size(min = 1, max = 24, message = "valida.username")
    String username;
    @Size(max = 255)
    String displayName;
    @NotNull(message = "{valida.passwordNotNull}")
    @Size(min = 8, max = 32, message = "{valida.passwordSize}")
    String password;
}
