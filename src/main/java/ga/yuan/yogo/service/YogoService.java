package ga.yuan.yogo.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 对 Service 层的聚合操作，比如博客的安装
 */
public interface YogoService {

    /**
     * 博客是否已经安装，判断 options、users 表是否为空
     *
     * @return Boolean
     */
//    boolean isInstalled();

    @Transactional
    void install(Map<String, String> installForm);
}
