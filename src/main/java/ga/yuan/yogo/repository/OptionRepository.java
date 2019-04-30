package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.OptionDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<OptionDO, Long> {
    Optional<OptionDO> findByName(String name);

    void deleteByName(String name);
}
