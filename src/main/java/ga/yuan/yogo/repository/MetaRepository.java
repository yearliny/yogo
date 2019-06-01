package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetaRepository extends JpaRepository<MetaDO, Long> {
    /**
     * 按 meta 类型查询，即查询所有的分类或标签
     * @param metaType MetaTypeEnum
     * @return List<MetaDO>
     */
    List<MetaDO> findAllByType(MetaTypeEnum metaType);

    /**
     * 按 meta 类型查询，即查询所有的分类或标签
     * @param metaType MetaTypeEnum
     * @param name meta 名称
     * @return Optional<MetaDO>
     */
    Optional<MetaDO> findByTypeAndName(MetaTypeEnum metaType, String name);

    /**
     * 按 Meta 的名称和类型进行查询，一个名字和类型仅能对应一个记录
     * @param name 名称
     * @param type 类型
     * @return 查询到的对象
     */
    MetaDO findByNameAndType(String name, MetaTypeEnum type);
}
