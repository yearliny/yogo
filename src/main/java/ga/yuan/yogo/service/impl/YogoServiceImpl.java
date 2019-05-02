package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.OptionDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.OptionEnum;
import ga.yuan.yogo.model.enums.UserRoleEnum;
import ga.yuan.yogo.model.vo.InstallVO;
import ga.yuan.yogo.service.OptionService;
import ga.yuan.yogo.service.UserService;
import ga.yuan.yogo.service.YogoService;
import org.springframework.stereotype.Service;

@Service
public class YogoServiceImpl implements YogoService {

    private final OptionService optionService;
    private final UserService userService;

    public YogoServiceImpl(OptionService optionService, UserService userService) {
        this.optionService = optionService;
        this.userService = userService;
    }

    @Override
    public void install(InstallVO installVO) {
        OptionDO option = new OptionDO();
        option.setName(OptionEnum.SITE_TITLE.getKey());
        option.setValue(installVO.getSiteTitle());
        optionService.save(option);

        UserDO user = new UserDO();
        user.setEmail(installVO.getEmail());
        user.setName(installVO.getUsername());
        user.setDisplayName(installVO.getDisplayName());
        user.setPassword(installVO.getPassword());
        user.setRole(UserRoleEnum.SUPER_ADMIN);
        userService.save(user);
    }
}
