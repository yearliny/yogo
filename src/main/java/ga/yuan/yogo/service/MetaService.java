package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;

import java.util.List;

public interface MetaService {

    /**
     * 根据id查询meta
     * @param id meta id
     * @return MetaDO
     */
    MetaDO get(Long id);

    /**
     * 根据 meta 的名称和类型返回相对应的meta，用于根据名字查询 category 或 tag
     *
     * @param name 名称
     * @param type 类型，category 或 tag
     * @return 查询到的 MetaDO
     */
    MetaDO get(String name, MetaTypeEnum type);

    /**
     * 保存 MetaDO
     *
     * @param meta 需要保存的 MetaDO
     * @return 返回保存的 MetaDO
     */
    MetaDO save(MetaDO meta);

    /**
     * 按类型列出所有 Meta
     *
     * @return Meta
     */
    List<MetaDO> listMeta(MetaTypeEnum type);
}
