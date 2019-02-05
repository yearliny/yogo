package ga.yuan.yogo.model.enums;

public enum MetaType {
    CATEGORY, TAG, LINK_CATEGORY;

    public static MetaType of(String s) {
        if (s == null) {
            return null;
        }
        for (MetaType metaType : MetaType.values()) {
            if (metaType == MetaType.valueOf(s.toUpperCase())) {
                return metaType;
            }
        }
        return null;
    }
}
