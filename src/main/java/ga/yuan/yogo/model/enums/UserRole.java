package ga.yuan.yogo.model.enums;

/**
 * 用户角色：目前并没有做好多用户权限控制，仅仅使用了 SUPER_ADMIN，并且角色之间并没有实质区别
 */
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
