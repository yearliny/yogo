package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.consts.YogoConst;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.MetaRepository;
import ga.yuan.yogo.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final MetaRepository metaRepository;

    public ContentServiceImpl(ContentRepository contentRepository, MetaRepository metaRepository) {
        this.contentRepository = contentRepository;
        this.metaRepository = metaRepository;
    }

    //    返回分页已发表的文章
    @Override
    public Page<ContentDO> listPosts(int pageNum, int size) {
        return contentRepository
                .findByTypeAndStatusOrderByCreatedDesc(
                        ContentTypeEnum.POST,
                        ContentStatusEnum.PUBLISH,
                        PageRequest.of(pageNum, size));
    }

    @Override
    public Page<ContentDO> listPosts(int pageNum) {
        String key = "posts_per_page";
//        默认值：每页5篇文章
        String size = "5";
        if (YogoConst.OPTIONS.containsKey(key)) {
            size = YogoConst.OPTIONS.get(key);
        }
        return listPosts(pageNum, Integer.valueOf(size));
    }

    /**
     * 保存 content 实体类，这里需要着重处理<strong>瞬时态content保存仅带有id信息的瞬时态meta</strong>。
     *
     * @param content 需要保存的内容
     * @return 已经保存的 content 实体类
     */
    @Override
    public ContentDO save(ContentDO content) {
        for (Long mid : content.getMetasMid()) {
            metaRepository.findById(mid)
                    .map(m -> content.getMetas().add(m));
        }
        return contentRepository.save(content);
    }

    @Override
    public Page<ContentDO> listContent(ContentTypeEnum type, Set<ContentStatusEnum> status, int page) {
        return contentRepository.findByTypeAndStatusOrderByCreatedDesc(type, status, PageRequest.of(page, 30));
    }
}
