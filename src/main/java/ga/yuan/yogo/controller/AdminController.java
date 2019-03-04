package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("options")
    public void setOptions(Model model) {
        model.addAttribute("options", YogoConst.OPTIONS);
    }

    /**
     * 登录页面
     *
     * @return view
     */
    @GetMapping("/yg-login")
    public String loginIndex(Model model) {
        return "admin/login";
    }

    @GetMapping("/yg-admin/")
    public String index(HttpServletRequest request, Model model) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByNameOrEmail(username);

        model.addAttribute("user", user);
        return "admin/index";
    }

}
