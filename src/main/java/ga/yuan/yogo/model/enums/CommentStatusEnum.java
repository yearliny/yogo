package ga.yuan.yogo.model.enums;

/**
 * 评论状态：
 * 0：已批准(approve)
 * 1：待审核(hold)
 * 2：垃圾评论(spam)
 * 3：回收站(trash)
 */
public enum CommentStatusEnum {
    APPROVE, HOLD, SPAM, TRASH;
}
