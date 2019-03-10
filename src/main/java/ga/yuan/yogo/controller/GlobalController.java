package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    /**
     * 404 错误处理
     *
     * @param model model
     * @return view name
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handelNotFound(Model model) {
        model.addAttribute("options", YogoConst.OPTIONS);
        return "error/404";
    }

}
