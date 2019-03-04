package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByName(String name);
}
