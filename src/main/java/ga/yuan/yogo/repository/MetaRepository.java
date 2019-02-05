package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    Optional<Meta> findByMid(Long id);

    List<Meta> findByType(MetaType metaType);
}
