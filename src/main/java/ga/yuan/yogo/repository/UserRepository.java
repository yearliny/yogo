package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Long> {

    /**
     * 通过 Email 地址查询用户
     * @param email Email 地址
     * @return UserDO
     */
    UserDO findByEmail(String email);

    /**
     * 通过 username 查询用户
     * @param name 用户名
     * @return UserDO
     */
    UserDO findByName(String name);
}
