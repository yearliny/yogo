package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> find(long id);

    /**
     * 是否存在用户，以此判断博客是否为第一次运行（是否安装）
     *
     * @return Boolean
     */
    boolean existsUser();

    /**
     * 保存用户
     * @param user
     * @return user
     */
    User save(User user);

    /**
     * 验证用户登录
     * @param nameOrEmail user name or email
     * @param password user password
     * @return bool
     */
    boolean verifyUser(String nameOrEmail, String password);

}
