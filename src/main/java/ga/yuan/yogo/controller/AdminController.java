package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.EditContentDTO;
import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import ga.yuan.yogo.model.vo.CommentCounterVO;
import ga.yuan.yogo.model.vo.ContentStatusCounterVO;
import ga.yuan.yogo.service.CommentService;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.MetaService;
import ga.yuan.yogo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import static ga.yuan.yogo.model.enums.ContentStatusEnum.*;

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
        Page<CommentDO> commentPage = commentService.listRecentComment(
                Set.of(CommentStatusEnum.APPROVE, CommentStatusEnum.HOLD),
                PageRequest.of(0, 5));

        model.addAttribute("postPage", postPage);
        model.addAttribute("commentCounter", commentCounter);
        model.addAttribute("commentPage", commentPage);
        return "admin/index";
    }

    @GetMapping("/post")
    public String post(Model model,
                       @RequestParam(value = "status", defaultValue = "all") String status,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "15") int size) {
        ContentStatusCounterVO contentStatusCounterVO = contentService.countStatus();
        Set<ContentStatusEnum> statusSet;
        if (status.toLowerCase().equals("all")) {
            statusSet = Set.of(
                    PUBLISH,
                    FUTURE,
                    DRAFT,
                    TRASH);
        } else {
            statusSet = Set.of(ContentStatusEnum.valueOf(status));
        }
        Page<ContentDO> postPage = contentService.listContent(ContentTypeEnum.POST, statusSet, page);
        model.addAttribute("postPage",postPage);
        model.addAttribute("contentStatusCounter",contentStatusCounterVO);
        return"admin/post";
    }

    /**
     * 文章、页面的编辑器
     *
     * @param postId 编辑文章的 id
     * @return viewName
     */
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id", defaultValue = "0") Long postId) {
        Optional<EditContentDTO> editContentDTO = contentService.getEditContentDTO(postId);
        model.addAttribute("editContentDTO", editContentDTO.orElse(new EditContentDTO()));
        model.addAttribute("allStatus", values());
        model.addAttribute("allCate", metaService.listMeta(MetaTypeEnum.CATEGORY));
        model.addAttribute("allTag", metaService.listMeta(MetaTypeEnum.TAG));
        return "admin/edit";
    }

    /**
     * 文章、页面的编辑器
     *
     * @param editContentDTO EditContentDTO
     * @return viewName
     */
    @PostMapping("/edit")
    public String editSave(EditContentDTO editContentDTO,
                           @RequestParam(value = "type", defaultValue = "POST") ContentTypeEnum type) {
        contentService.save(editContentDTO, type);
        return "admin/edit";
    }

    /**
     * 添加meta
     *
     * @return view name
     */
    @GetMapping(value = "/meta")
    public String meta(Model model, @RequestParam("type") MetaTypeEnum type) {
        model.addAttribute("meta", new MetaDO());
        model.addAttribute("type", type.toString());
        model.addAttribute("all", metaService.listMeta(type));
        System.out.println(metaService.listMeta(type));
        return "admin/meta";
    }

    @PostMapping(value = "/meta")
    public String metaNew(Model model, MetaDO meta) {
        metaService.save(meta);
        model.addAttribute("meta", meta);
        model.addAttribute("type", meta.getType().toString());
        model.addAttribute("all", metaService.listMeta(meta.getType()));
        return "admin/meta";
    }
}
