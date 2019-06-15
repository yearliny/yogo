package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.*;
import ga.yuan.yogo.model.enums.*;
import ga.yuan.yogo.model.vo.InstallVO;
import ga.yuan.yogo.service.*;
import ga.yuan.yogo.util.RegexUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class YogoServiceImpl implements YogoService {

    private final OptionService optionService;
    private final UserService userService;
    private final ContentService contentService;
    private final MetaService metaService;
    private final CommentService commentService;

    public YogoServiceImpl(OptionService optionService, UserService userService, ContentService contentService, MetaService metaService, CommentService commentService) {
        this.optionService = optionService;
        this.userService = userService;
        this.contentService = contentService;
        this.metaService = metaService;
        this.commentService = commentService;
    }

    @Transactional
    @Override
    public void install(InstallVO installVO) {
        // 保存选项
        Map<String, String> options = new HashMap<>(3);
        options.put(OptionEnum.SITE_TITLE.getName(), installVO.getSiteTitle());
        options.put(OptionEnum.SITE_DESCRIPTION.getName(), installVO.getSiteDescription());
        options.put(OptionEnum.SITE_URL.getName(), installVO.getSiteUrl());
        optionService.saveAll(options);

        // 创建用户
        UserDO user = new UserDO();
        user.setEmail(installVO.getEmail());
        user.setName(installVO.getUsername());
        user.setDisplayName(installVO.getDisplayName());
        user.setPassword(installVO.getPassword());
        user.setRole(UserRoleEnum.SUPER_ADMIN);
        user = userService.save(user);

        // 创建默认分类
        MetaDO category = new MetaDO();
        category.setType(MetaTypeEnum.CATEGORY);
        category.setName("默认分类");
        category.setSlug("default-category");
        category = metaService.save(category);

        // 创建默认标签
        MetaDO tag = new MetaDO();
        tag.setType(MetaTypeEnum.TAG);
        tag.setName("默认标签");
        tag.setSlug("default-tag");
        tag = metaService.save(tag);

        // 保存测试文章
        ContentDO content = new ContentDO();
        content.addMeta(category);
        content.addMeta(tag);
        content.setAuthor(user);
        content.setType(ContentTypeEnum.POST);
        content.setStatus(ContentStatusEnum.PUBLISH);
        content.setTitle("你好，Yogo！");
        content.setSlug("hello-yogo");
        content.setBody("<p>这是一篇测试文章，用来演示效果。删掉此文章后开始你的写作旅程吧！</p>");
        content = contentService.save(content);

        // 保存测试评论
        CommentDO comment = new CommentDO();
        comment.setContent(content);
        comment.setAuthor("yearliny");
        comment.setEmail("yearliny@outlook.com");
        comment.setStatus(CommentStatusEnum.APPROVE);
        comment.setBody("<p>这是一条测试评论。</p>");
        commentService.save(comment);
    }
}
