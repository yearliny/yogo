package ga.yuan.yogo.model.enums;

public enum MetaTypeEnum {
    CATEGORY, TAG, LINK_CATEGORY;

    public static MetaTypeEnum of(String s) {
        return Enum.valueOf(MetaTypeEnum.class, s);
    }
}
