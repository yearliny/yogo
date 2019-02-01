package ga.yuan.yogo.model.enums;

/**
 * 评论状态：
 * 0：已批准(approve)
 * 1：待审核(hold)
 * 2：垃圾评论(spam)
 * 3：回收站(trash)
 */
public enum CommentStatus {
    APPROVE, HOLD, SPAM, TRASH;

    public static CommentStatus of(String s) {
//        如果传入字符为 null，直接返回 null
        if (s == null) {
            return null;
        }
//        遍历查找是否有相符的枚举
        for (CommentStatus commentStatus : CommentStatus.values()) {
            if (commentStatus == CommentStatus.valueOf(s.toUpperCase())) {
                return commentStatus;
            }
        }
//        不存在的枚举，返回 null
        return null;
    }
}
