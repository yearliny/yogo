package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.consts.YogoConst;
import ga.yuan.yogo.model.dto.EditContentDTO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.enums.OptionEnum;
import ga.yuan.yogo.model.vo.ContentStatusCounterVO;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public ContentServiceImpl(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<ContentDO> findById(Long id) {
        return contentRepository.findById(id);
    }

    @Override
    public Page<ContentDO> listPosts(int pageNum, int size, Set<ContentStatusEnum> status, ContentTypeEnum type) {
        return contentRepository.findByTypeAndStatusInOrderByCreatedDesc(ContentTypeEnum.POST,
                status,
                PageRequest.of(pageNum, size));
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
        return listPosts(pageNum, YogoConst.getOptionInt(OptionEnum.POST_PER_PAGE).orElse(5));
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
    public ContentDO save(EditContentDTO editContentDTO, ContentTypeEnum type) {
        ModelMapper modelMapper = new ModelMapper();
        ContentDO content = modelMapper.map(editContentDTO, ContentDO.class);
        content.setAuthor(userRepository.getOne(editContentDTO.getUid()));
        content.setType(type);
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

    @Override
    public Optional<EditContentDTO> getEditContentDTO(Long id) {
        Optional<ContentDO> contentDO = findById(id);
        if (contentDO.isEmpty()) {
            return Optional.empty();
        }
        ContentDO c = contentDO.get();
        EditContentDTO e = new EditContentDTO();
        e.setCid(c.getCid());
        e.setTitle(c.getTitle());
        e.setBody(c.getBody());
        e.setSlug(c.getSlug());
        e.setType(c.getType());
        e.setStatus(c.getStatus());
        e.setTags(c.getTag());
        e.setCategory(c.getCategory());
        e.setAllowComment(c.getAllowComment());
        e.setPassword(c.getPassword());
        return Optional.of(e);
    }
}
