package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Long> {

    UserDO findByEmail(String email);

    UserDO findByName(String name);
}
