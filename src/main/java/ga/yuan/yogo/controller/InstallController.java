package ga.yuan.yogo.controller;

import ga.yuan.yogo.service.YogoService;
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

    private final YogoService yogoService;

    @Autowired
    public InstallController(YogoService yogoService) {
        this.yogoService = yogoService;
    }


    @GetMapping("/yg-install")
    public String installPage(Model model) {
        return "common/install";
    }

    @PostMapping("/yg-install")
    public String installForm(@RequestParam Map<String, String> params) {
        yogoService.install(params);
        return "redirect:/yg-login";
    }
}
