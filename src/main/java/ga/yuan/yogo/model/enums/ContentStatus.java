package ga.yuan.yogo.model.enums;

/**
 * 文章状态
 * 0：已发表（publish）
 * 1：定时发表（future）
 * 2：草稿箱（draft）
 * 3：回收站（trash）
 */
public enum ContentStatus {
    PUBLISH, FUTURE, DRAFT, TRASH;

    /**
     * 通过字符串的状态反查枚举类型
     *
     * @param s
     * @return ContentStatus
     */
    public static ContentStatus of(String s) {
//        如果传入字符为 null，直接返回 null
        if (s == null) {
            return null;
        }
//        遍历查找是否有相符的枚举
        for (ContentStatus contentStatus : ContentStatus.values()) {
            if (contentStatus == ContentStatus.valueOf(s.toUpperCase())) {
                return contentStatus;
            }
        }
//        不存在的枚举，返回 null
        return null;
    }
}
