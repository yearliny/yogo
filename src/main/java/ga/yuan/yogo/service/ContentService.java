package ga.yuan.yogo.service;

import ga.yuan.yogo.model.dto.FrontMatterBodyDTO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.vo.ContentStatusCounterVO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface ContentService {

    Optional<ContentDO> getContent(Long id);

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
     * 从编辑器的原始字符保存 ContentDO 对象
     * @param bodyRaw 编辑器字符
     * @return ContentDO
     */
    ContentDO saveFromBodyRaw(String bodyRaw);

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

}
