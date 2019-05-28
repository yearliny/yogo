package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.consts.YogoConst;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.enums.OptionEnum;
import ga.yuan.yogo.model.vo.ContentStatusCounterVO;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.MetaRepository;
import ga.yuan.yogo.service.ContentService;
import ga.yuan.yogo.util.ContentParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<ContentDO> getContent(Long id) {
        return contentRepository.findById(id);
    }

    //    返回分页已发表的文章
    @Override
    public Page<ContentDO> listPosts(int pageNum, int size) {
        Set<ContentStatusEnum> contentStatusEnums = Set.of(ContentStatusEnum.PUBLISH);
        return contentRepository
                .findByTypeAndStatusInOrderByCreatedDesc(
                        ContentTypeEnum.POST,
                        contentStatusEnums,
                        PageRequest.of(pageNum, size));
    }

    @Override
    public Page<ContentDO> listPosts(int pageNum) {
        return listPosts(pageNum, YogoConst.getOptionInt(OptionEnum.POST_PER_PAGE));
    }

    /**
     * 保存 content 实体类，这里需要着重处理<strong>瞬时态content保存仅带有id信息的瞬时态meta</strong>。
     *
     * @param content 需要保存的内容
     * @return 已经保存的 content 实体类
     */
    @Override
    public ContentDO save(ContentDO content) {
        return contentRepository.save(content);
    }

    @Override
    public ContentDO saveFromBodyRaw(String bodyRaw) {
        ContentDO content = new ContentDO();
        try {
            content = ContentParserUtil.load(bodyRaw);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return contentRepository.save(content);
    }

    @Override
    public Page<ContentDO> listContent(ContentTypeEnum type, Set<ContentStatusEnum> status, int page) {
        return contentRepository.findByTypeAndStatusInOrderByCreatedDesc(type, status, PageRequest.of(page, 30));
    }

    @Override
    public ContentStatusCounterVO countStatus() {
        ContentStatusCounterVO counter = new ContentStatusCounterVO();
        counter.setPublish(contentRepository.countByStatus(ContentStatusEnum.PUBLISH));
        counter.setFuture(contentRepository.countByStatus(ContentStatusEnum.FUTURE));
        counter.setDraft(contentRepository.countByStatus(ContentStatusEnum.DRAFT));
        counter.setTrash(contentRepository.countByStatus(ContentStatusEnum.TRASH));
        return counter;
    }
}
