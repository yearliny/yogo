package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = {"/", "/page"})
public class IndexController {

    private final ContentService contentService;
    private final OptionService optionService;

    @Autowired
    public IndexController(ContentService contentService, OptionService optionService) {
        this.contentService = contentService;
        this.optionService = optionService;
    }

    @ModelAttribute("options")
    public Map<String, String> getOptions() {
        return optionService.findAll();
    }

    @GetMapping
    public String index(Model model) {
        return index(model, 0);
    }

    @GetMapping("/page/{pageNum}")
    public String index(Model model, @PathVariable(value = "pageNum") Integer pageNum) {
        Page<Content> posts = contentService.listPosts(pageNum);
        model.addAttribute("posts", posts);
        return "index";
    }

}
