package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void setOptions(Model model) {
        model.addAttribute("options", YogoConst.OPTIONS);
    }

    /**
     * 登录页面
     *
     * @param model Model
     * @return template_path
     */
    @GetMapping("/yg-login")
    public String loginIndex(Model model, @RequestParam(required = false) String action) {
        model.addAttribute("user", new User());
        return "admin/login";
    }

//    @PostMapping("/yg-login")
//    public String loginForm(Model model, User user) {
//        userService.verifyUser(user.getName(), user.getPassword());
//        return "common/login";
//    }

}
