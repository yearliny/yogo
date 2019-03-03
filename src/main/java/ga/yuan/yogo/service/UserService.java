package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> find(long id);

    Optional<User> findByNameOrEmail(String nameOrEmail);

    /**
     * 保存用户
     *
     * @param user
     * @return user
     */
    User save(User user);

    boolean existsById(long id);

}
