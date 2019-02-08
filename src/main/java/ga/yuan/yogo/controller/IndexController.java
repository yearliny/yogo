package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/page"})
public class IndexController extends BaseController {

    private final ContentService contentService;

    @Autowired
    public IndexController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public String index(Model model) {
        return index(model, 0);
    }

    @GetMapping("/page/{pageNum}")
    public String index(Model model, @PathVariable(value = "pageNum") Integer pageNum) {
        Page<Content> posts = contentService.listPosts(pageNum);
        model.addAttribute("posts", posts);
        return render("index");
    }

}
