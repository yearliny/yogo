package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.UserDO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public interface UserService {

    Optional<UserDO> find(long id);

    UserDO findByNameOrEmail(String nameOrEmail);

    /**
     * 保存用户，密码使用 {@link BCryptPasswordEncoder} 进行加密
     */
    UserDO save(UserDO user);

    boolean existsById(long id);

}
