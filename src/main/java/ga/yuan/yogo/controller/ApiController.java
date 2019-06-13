package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.service.MetaService;
import org.springframework.web.bind.annotation.*;

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
    List<MetaDO> getCategories() {
        return metaService.listMeta(MetaTypeEnum.CATEGORY);
    }

    @GetMapping("/post_tag/")
    List<MetaDO> getPostTag() {
        return metaService.listMeta(MetaTypeEnum.TAG);
    }

    @PostMapping("/posts/")
    ContentDO postContent(@RequestBody ContentDO content) {
        return contentService.save(content);
    }

}
