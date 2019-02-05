package ga.yuan.yogo.service;

import java.util.Map;

public interface OptionService {
    /**
     * 获取所有选项
     * @return Map
     */
    Map<String, String> findAll();
}
