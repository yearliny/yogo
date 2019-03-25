package ga.yuan.yogo.model.enums;

/**
 * 用户角色参考了 WordPress 的设计，目前仅使用了 SUPER_ADMIN，其他角色待定
 */
public enum UserRole {

    SUPER_ADMIN, ADMINISTRATOR, EDITOR, AUTHOR, CONTRIBUTOR, SUBSCRIBER;

    public static UserRole of(String s) {
        return Enum.valueOf(UserRole.class, s);
    }

}
