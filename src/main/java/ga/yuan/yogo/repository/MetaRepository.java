package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    Optional<Meta> findByMid(Long id);

}
