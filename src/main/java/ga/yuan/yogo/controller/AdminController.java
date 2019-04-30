package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.MetaService;
import ga.yuan.yogo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 网站的后台功能
 */
@Controller
@RequestMapping("/yg-admin/")
public class AdminController {

    private final UserService userService;
    private final ContentService contentService;
    private final MetaService metaService;

    public AdminController(UserService userService, ContentService contentService, MetaService metaService) {
        this.userService = userService;
        this.contentService = contentService;
        this.metaService = metaService;
    }

    @ModelAttribute
    public UserDO getUser(Model model, Principal principal) {
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
        Set<ContentStatusEnum> contentStatus = new HashSet<>();
        contentStatus.add(ContentStatusEnum.FUTURE);
        contentStatus.add(ContentStatusEnum.DRAFT);
        contentStatus.add(ContentStatusEnum.PUBLISH);

        Page<ContentDO> contents = contentService.listContent(ContentTypeEnum.POST, contentStatus, page);
        model.addAttribute("contents", contents);
        return "admin/edit";
    }

    /**
     * 文章发表页面
     *
     * @return view name
     */
    @GetMapping("/post-new")
    public String postNew(Model model) {
        List<MetaDO> category = metaService.listCategory();
        List<MetaDO> tag = metaService.listTag();

        model.addAttribute("category", category);
        model.addAttribute("tag", tag);
        model.addAttribute("post", new ContentDO());
        return "admin/post-new";
    }

}
