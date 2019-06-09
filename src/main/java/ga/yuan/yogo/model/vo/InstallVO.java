package ga.yuan.yogo.model.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 博客安装页面的表单
 */
@Data
public class InstallVO {
    private String siteTitle;
    @Email(message = "{valida.emailError}")
    @NotBlank(message = "{valida.usernameEmpty}")
    private String email;
    @NotNull
    @Size(min = 1, max = 24, message = "valida.username")
    private String username;
    @Size(max = 255)
    private String displayName;
    @NotNull(message = "{valida.passwordNotNull}")
    @Size(min = 8, max = 32, message = "{valida.passwordSize}")
    private String password;
}
