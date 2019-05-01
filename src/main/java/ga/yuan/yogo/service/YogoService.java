package ga.yuan.yogo.service;

import ga.yuan.yogo.model.vo.InstallVO;

/**
 * 负责博客整体的与数据库的交互
 */
public interface YogoService {
    /**
     * 安装博客
     *
     * @param installVO 安装页面的表单
     */
    void install(InstallVO installVO);
}
