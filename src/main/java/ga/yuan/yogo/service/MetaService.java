package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.MetaDO;

import java.util.List;

public interface MetaService {
    /**
     * 保存 MetaDO
     * @param meta 需要保存的 MetaDO
     * @return 返回保存的 MetaDO
     */
    MetaDO save(MetaDO meta);

    /**
     * 列出所有目录
     *
     * @return 目录类型的 List<MetaDO>
     */
    List<MetaDO> listCategory();

    /**
     * 列出所有标签
     *
     * @return 标签类型的 List<MetaDO>
     */
    List<MetaDO> listTag();
}
