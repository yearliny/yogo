package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.OptionDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<OptionDO, Long> {
    /**
     * 通过选项名称查询 option
     * @param name 选项的名称
     * @return Optional<OptionDO>
     */
    Optional<OptionDO> findByName(String name);

    /**
     * 通过选项名称删除 option
     * @param name 选项的名称
     */
    void deleteByName(String name);
}
