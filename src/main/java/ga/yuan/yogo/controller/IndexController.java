package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.OptionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 网站首页
 */
@Controller
@RequestMapping(value = {"/", "/page"})
public class IndexController {

    private final ContentService contentService;

    public IndexController(ContentService contentService, OptionService optionService) {
        this.contentService = contentService;
    }

    @GetMapping
    public String index(Model model) {
        return index(model, 0);
    }

    @GetMapping("/page/{pageNum}")
    public String index(Model model, @PathVariable(value = "pageNum") Integer pageNum) {
        Page<ContentDO> posts = contentService.listPosts(pageNum);
        model.addAttribute("posts", posts);
        return "index";
    }

    /**
     * 配合 {@link ga.yuan.yogo.config.YogoAtomFeedView}生成网站的 Atom Feed
     *
     * @return view name
     */
    @GetMapping("/feed")
    public String rssView(Model model) {

        return "atomFeedView";
    }
}
