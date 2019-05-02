package ga.yuan.yogo.controller;

import ga.yuan.yogo.consts.YogoConst;
import ga.yuan.yogo.model.vo.InstallVO;
import ga.yuan.yogo.service.YogoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class InstallController {

    private final YogoService yogoService;

    public InstallController(YogoService yogoService) {
        this.yogoService = yogoService;
    }

    @GetMapping("/yg-install")
    public String installPage(InstallVO installVO) {
        return YogoConst.isInstall() ? "redirect:/yg-login" : "admin/install";
    }

    @PostMapping("/yg-install")
    public String installForm(@Valid InstallVO installVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/install";
        }
        yogoService.install(installVO);
        return "redirect:/yg-login";
    }
}
