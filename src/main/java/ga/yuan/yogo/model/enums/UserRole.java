package ga.yuan.yogo.model.enums;

public enum UserRole {

    SUPER_ADMIN, ADMINISTRATOR, EDITOR, AUTHOR, CONTRIBUTOR, SUBSCRIBER;

    public static UserRole of(String s) {
//        如果传入字符为 null，直接返回 null
        if (s == null) {
            return null;
        }
//        遍历查找是否有相符的枚举
        for (UserRole userRole : UserRole.values()) {
            if (userRole == UserRole.valueOf(s.toUpperCase())) {
                return userRole;
            }
        }
//        不存在的枚举，返回 null
        return null;
    }

}