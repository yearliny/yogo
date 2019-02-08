package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {
    Optional<Option> findByName(String name);

    void deleteByName(String name);
}
