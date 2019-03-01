package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 注册页面
     *
     * @param model Model
     * @return template_path
     */
    @GetMapping("/yg-admin/register")
    public String registerIndex(Model model) {
        model.addAttribute("user", new User());
        return "common/register";
    }

    @PostMapping("/yg-admin/register")
    public String registerForm(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "common/register";
        }
        userService.save(user);
        return "common/login";
    }

}
