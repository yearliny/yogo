package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.model.vo.CommentCounterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CommentService {
    CommentDO save(CommentDO comment);

    /**
     * 统计评论
     *
     * @return CommentCounterVO
     */
    CommentCounterVO countComment();

    /**
     * 获取最近的评论供后台使用，包括多种评论状态
     *
     * @return Page<CommentDO>
     */
    Page<CommentDO> listRecentComment(Set<CommentStatusEnum> status, Pageable pageable);
}
