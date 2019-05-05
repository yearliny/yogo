package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.OptionDO;

import java.util.List;
import java.util.Map;

public interface OptionService {
    /**
     * 获取所有选项
     *
     * @return Map
     */
    Map<String, String> findAll();

    OptionDO save(OptionDO option);

    /**
     * 从 Map 中保存 OptionDO
     *
     * @param options 传进的 map 参数
     */
    List<OptionDO> saveAll(Map<String, String> options);


    void delete(OptionDO option);

    void delete(String name);

}
