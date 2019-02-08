package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/yogo-admin")
public class InstallController {
    private final OptionService optionService;

    @Autowired
    public InstallController(OptionService optionService) {
        this.optionService = optionService;
    }


    @GetMapping("/install")
    public String installPage(Model model) {
        if (optionService.isInstalled()) {
            model.addAttribute("isInstalled", true);
        } else {
            model.addAttribute("isInstalled", false);
        }
        return "common/install";
    }

    @PostMapping("/install")
    public String installForm(Model model, @RequestParam Map<String, String> params) {
        log.info("/install get params {}", params.toString());
        Option option = new Option();
        option.setName("name");
        option.setValue(params.get("name"));
        optionService.save(option);
        return "common/install";
    }
}
