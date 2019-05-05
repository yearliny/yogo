package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.vo.CommentCounterVO;
import ga.yuan.yogo.service.CommentService;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.MetaService;
import ga.yuan.yogo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final CommentService commentService;

    public AdminController(UserService userService, ContentService contentService, MetaService metaService, CommentService commentService) {
        this.userService = userService;
        this.contentService = contentService;
        this.metaService = metaService;
        this.commentService = commentService;
    }

    @ModelAttribute(name = "user")
    public UserDO getUser(Principal principal) {
        return userService.findByNameOrEmail(principal.getName());
    }

    /**
     * 后台首页
     *
     * @return view name
     */
    @GetMapping
    public String admin(Model model) {
        Page<ContentDO> postPage = contentService.listPosts(0, 5);
        CommentCounterVO commentCounter = commentService.countComment();
        Set<CommentStatusEnum> commentStatus = new HashSet<>();
        commentStatus.add(CommentStatusEnum.APPROVE);
        commentStatus.add(CommentStatusEnum.HOLD);
        Page<CommentDO> commentPage = commentService.listRecentComment(commentStatus, PageRequest.of(0, 5));

        model.addAttribute("postPage", postPage);
        model.addAttribute("commentCounter", commentCounter);
        model.addAttribute("commentPage", commentPage);
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

    /**
     * 添加媒体文件
     *
     * @return view name
     */
    @GetMapping("/media-new")
    public String mediaNew() {
        return "admin/media-new";
    }

}
