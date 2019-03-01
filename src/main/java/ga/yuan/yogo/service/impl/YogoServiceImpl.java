package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.model.enums.UserRole;
import ga.yuan.yogo.service.OptionService;
import ga.yuan.yogo.service.UserService;
import ga.yuan.yogo.service.YogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class YogoServiceImpl implements YogoService {

    private final OptionService optionService;
    private final UserService userService;

    @Autowired
    public YogoServiceImpl(OptionService optionService, UserService userService) {
        this.optionService = optionService;
        this.userService = userService;
    }

    @Override
    public boolean isInstalled() {
        return optionService.count() > 0 && userService.existsById(1L);
    }

    @Override
    public void install(Map<String, String> installForm) {
        Option option = new Option();
        option.setName("site_title");
        option.setValue(installForm.get("site_title"));
        optionService.save(option);

        User user = new User();
        user.setEmail(installForm.get("email"));
        user.setName(installForm.get("name"));
        user.setDisplayName(installForm.getOrDefault("display_name", null));
        user.setPassword(installForm.get("password"));
        user.setRole(UserRole.SUPER_ADMIN);
        userService.save(user);
    }
}
