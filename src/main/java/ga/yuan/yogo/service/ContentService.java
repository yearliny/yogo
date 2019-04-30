package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ContentService {
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
     * 用于 admin 内容管理列表
     *
     * @param type   ContentTypeEnum
     * @param status ContentStatusEnum
     * @return Page<ContentDO>
     */
    Page<ContentDO> listContent(ContentTypeEnum type, Set<ContentStatusEnum> status, int page);
}
