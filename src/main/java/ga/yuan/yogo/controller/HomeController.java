package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public String index() {
        return index(1);
    }

    @GetMapping("/page/{pageNum}")
    public String index(@PathVariable(value = "pageNum") Integer pageNum) {
        Page<Content> contents = contentService.list(pageNum);
        return "index";
    }

}
