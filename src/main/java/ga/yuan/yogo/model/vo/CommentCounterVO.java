package ga.yuan.yogo.model.vo;

import lombok.Value;

/**
 * 博客后台展示的评论数量按类型统计，依次是全部评论、我的评论、待审评论、通过评论、垃圾评论、回收站
 */
@Value
public class CommentCounterVO {
    long hold;
    long approved;
    long spam;
    long trash;

    public long getAll() {
        return hold + approved + spam + trash;
    }
}
