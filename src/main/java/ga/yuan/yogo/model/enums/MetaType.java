package ga.yuan.yogo.model.enums;

public enum MetaType {
    CATEGORY, TAG, LINK_CATEGORY;

    public static MetaType of(String s) {
        return Enum.valueOf(MetaType.class, s);
    }
}
