package ga.yuan.yogo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录、注册操作：负责与未登录的用户进行交互
 */
@Controller
public class LoginController {
    /**
     * 登录页面
     *
     * @return view
     */
    @GetMapping("/yg-login")
    public String login(Model model) {
        return "admin/login";
    }
}
