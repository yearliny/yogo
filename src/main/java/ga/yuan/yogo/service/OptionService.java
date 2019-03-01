package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.Option;

import java.util.List;
import java.util.Map;

public interface OptionService {
    /**
     * 获取所有选项
     *
     * @return Map
     */
    Map<String, String> findAll();

    Option save(Option option);

    /**
     * 从 Map 中保存 Option
     *
     * @param options 传进的 map 参数
     */
    List<Option> saveAll(Map<String, String> options);


    void delete(Option option);

    void delete(String name);

    /**
     * 博客是否已经安装，判断 options 表是否为空
     *
     * @return Boolean
     */
    boolean isInstalled();
}
