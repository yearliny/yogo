package ga.yuan.yogo.service;

import ga.yuan.yogo.model.dto.EditContentDTO;
import ga.yuan.yogo.model.dto.FrontMatterBodyDTO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.vo.ContentStatusCounterVO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface ContentService {

    Optional<ContentDO> findById(long id);

    Page<ContentDO> listPosts(int pageNum, int size, Set<ContentStatusEnum> status, ContentTypeEnum type);

    Page<ContentDO> listPosts(int pageNum, int size);

    Page<ContentDO> listPosts(int pageNum);

    /**
     * 保存内容
     *
     * @param content 需要保存的内容
     * @return 已经保存的内容，带有 id
     */
    ContentDO save(ContentDO content);

    /**
     * 保存内容, 使用 ContentEditDTO
     *
     * @param editContentDTO 需要保存的内容
     * @return 已经保存的内容，带有 id
     */
    ContentDO save(EditContentDTO editContentDTO, ContentTypeEnum type);

    /**
     * 用于 admin 内容管理列表
     *
     * @param type   ContentTypeEnum
     * @param status ContentStatusEnum
     * @return Page<ContentDO>
     */
    Page<ContentDO> listContent(ContentTypeEnum type, Set<ContentStatusEnum> status, int page);

    /**
     * 按内容的状态统计其数量
     * @return vo ContentStatusCounterVO
     */
    ContentStatusCounterVO countStatus();

    /**
     * 通过 id 查询 EditContentDTO，用于文章编辑
     * @param id id
     * @return EditContentDTO
     */
    Optional<EditContentDTO> getEditContentDTO(long id);
}
