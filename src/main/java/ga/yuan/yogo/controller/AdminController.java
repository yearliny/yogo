package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * 网站的后台功能
 */
@Controller
@RequestMapping("/yg-admin/")
public class AdminController {

    private final UserService userService;
    private final ContentService contentService;

    @Autowired
    public AdminController(UserService userService, ContentService contentService) {
        this.userService = userService;
        this.contentService = contentService;
    }

    @ModelAttribute
    public User getUser(Model model, Principal principal) {
        return userService.findByNameOrEmail(principal.getName());
    }

    /**
     * 后台首页
     *
     * @return view name
     */
    @GetMapping
    public String admin() {
        return "admin/index";
    }

    /**
     * 列出文章、页面列表
     *
     * @return view name
     */
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        Set<ContentStatus> contentStatus = new HashSet<>();
        contentStatus.add(ContentStatus.FUTURE);
        contentStatus.add(ContentStatus.DRAFT);
        contentStatus.add(ContentStatus.PUBLISH);

        Page<Content> contents = contentService.listContent(ContentType.POST, contentStatus, page);
        model.addAttribute("contents", contents);
        return "admin/edit";
    }

    /**
     * 文章发表页面
     *
     * @return view name
     */
    @GetMapping("/post-new")
    public String postNew() {
        return "admin/post-new";
    }

}
