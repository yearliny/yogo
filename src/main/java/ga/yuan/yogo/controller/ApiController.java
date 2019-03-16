package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.MetaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Yogo 的 Admin API 接口，参考 Ghost Blog API 设计： <a href="https://docs.ghost.org/api/admin/">Admin API</a>
 */
@RestController
@RequestMapping("/yg-json/")
public class ApiController {

    private final MetaService metaService;
    private final ContentService contentService;

    public ApiController(MetaService metaService, ContentService contentService) {
        this.metaService = metaService;
        this.contentService = contentService;
    }

    @GetMapping("/categories/")
    List<Meta> getCategories() {
        return metaService.listCategory();
    }

    @GetMapping("/post_tag/")
    List<Meta> getPostTag() {
        return metaService.listTag();
    }

    @PostMapping("/posts/")
    Content postContent(Content content) {
        return contentService.save(content);
    }

}
