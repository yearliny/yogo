package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.InstallForm;
import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.service.OptionService;
import ga.yuan.yogo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public InstallController(OptionService optionService, UserService userService) {
        this.optionService = optionService;
        this.userService = userService;
    }


    @GetMapping("/yg-admin/install")
    public String installPage(Model model) {
        if (optionService.isInstalled()) {
            return "redirect:/";
        }
        model.addAttribute("installForm", new InstallForm());
        return "common/install";
    }

    @PostMapping("/yg-admin/install")
    public String installForm(@RequestParam Map<String, String> params) {
        Option option = new Option();
        option.setName("siteTitle");
        option.setValue(params.get("siteTitle"));
        optionService.save(option);

        User user = new User();
        user.setEmail(params.get("email"));
        user.setName(params.get("name"));
        user.setPassword(params.get("password"));
        userService.save(user);

        return "common/install";
    }
}
