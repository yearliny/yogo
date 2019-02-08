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

    /**
     * 博客是否已经安装，通过判断是否存在选项判断
     * @return Boolean
     */

     void delete(Option option);

     void delete(String name);

    Boolean isInstalled();
}
