package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaRepository extends JpaRepository<MetaDO, Long> {
    List<MetaDO> findAllByType(MetaTypeEnum metaType);
}
