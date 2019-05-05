package ga.yuan.yogo.model.vo;

import lombok.Data;

/**
 * 博客后台展示的评论数量按类型统计，依次是全部评论、我的评论、待审评论、通过评论、垃圾评论、回收站
 *
 */
@Data
public class CommentCounterVO {
    private Long all;
    private Long hold;
    private Long approved;
    private Long spam;
    private Long trash;

    public Long getAll() {
        return hold + approved + spam + trash;
    }
}
