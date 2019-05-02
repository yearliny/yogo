package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.UserDO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public interface UserService {

    Optional<UserDO> find(long id);

    /**
     * 通过用户名或邮箱查询用户
     * @param nameOrEmail 字符串，用户名或邮箱地址，使用正则表达式判断
     * @return 查询到的用户
     */
    UserDO findByNameOrEmail(String nameOrEmail);

    /**
     * 保存用户，密码使用 {@link BCryptPasswordEncoder} 进行加密
     */
    UserDO save(UserDO user);

    boolean existsById(long id);

}
