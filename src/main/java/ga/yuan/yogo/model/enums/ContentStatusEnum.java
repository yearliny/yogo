package ga.yuan.yogo.model.enums;

/**
 * 文章状态
 * 0：已发表（publish）
 * 1：定时发表（future）
 * 2：草稿箱（draft）
 * 3：回收站（trash）
 */
public enum ContentStatusEnum {
    PUBLISH, FUTURE, DRAFT, TRASH;

    /**
     * 通过字符串的状态反查枚举类型
     *
     * @param s
     * @return ContentStatusEnum
     */
    public static ContentStatusEnum of(String s) {
        return Enum.valueOf(ContentStatusEnum.class, s);
    }
}
