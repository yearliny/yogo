package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.Meta;

import java.util.List;

public interface MetaService {
    /**
     * 列出所有目录
     *
     * @return 目录类型的 List<Meta>
     */
    List<Meta> listCategory();

    /**
     * 列出所有标签
     *
     * @return 标签类型的 List<Meta>
     */
    List<Meta> listTag();
}
