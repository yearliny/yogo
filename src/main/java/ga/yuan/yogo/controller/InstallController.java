package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.model.enums.UserRole;
import ga.yuan.yogo.service.OptionService;
import ga.yuan.yogo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
public class InstallController {

    private final OptionService optionService;
    private final UserService userService;

    public InstallController(OptionService optionService, UserService userService) {
        this.optionService = optionService;
        this.userService = userService;
    }


    @GetMapping("/yg-install")
    public String installPage(Model model) {
        return YogoConst.isInstall() ? "redirect:/yg-login" : "admin/install";
    }

    @PostMapping("/yg-install")
    public String installForm(@RequestParam Map<String, String> params) {
        Option option = new Option();
        option.setName("site_title");
        option.setValue(params.get("site_title"));
        optionService.save(option);

        User user = new User();
        user.setEmail(params.get("email"));
        user.setName(params.get("username"));
        user.setDisplayName(params.get("display_name"));
        user.setPassword(params.get("password"));
        user.setRole(UserRole.SUPER_ADMIN);
        userService.save(user);

        return "redirect:/yg-login";
    }
}
