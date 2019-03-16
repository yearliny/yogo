package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ContentService {
    Page<Content> listPosts(int pageNum, int size);

    Page<Content> listPosts(int pageNum);

    /**
     * 保存内容
     *
     * @param content 需要保存的内容
     * @return 已经保存的内容，带有 id
     */
    Content save(Content content);

    /**
     * 用于 admin 内容管理列表
     *
     * @param type   ContentType
     * @param status ContentStatus
     * @return Page<Content>
     */
    Page<Content> listContent(ContentType type, Set<ContentStatus> status, int page);
}
