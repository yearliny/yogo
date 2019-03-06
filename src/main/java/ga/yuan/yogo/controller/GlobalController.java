package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

/**
 * Controller 的全局设定，如全局变量、错误处理
 */
@ControllerAdvice
public class GlobalController {

    /**
     * 添加模版的全局变量，相当于 model.addAttribute("options", YogoConst.OPTIONS)
     *
     * @return options
     */
    @ModelAttribute("options")
    public Map<String, String> getOptions() {
        return YogoConst.OPTIONS;
    }

}
