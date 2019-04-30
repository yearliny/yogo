package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.UserDO;

import java.util.Optional;

public interface UserService {

    Optional<UserDO> find(long id);

    UserDO findByNameOrEmail(String nameOrEmail);

    /**
     * 保存用户
     */
    UserDO save(UserDO user);

    boolean existsById(long id);

}
