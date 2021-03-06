package ga.yuan.yogo.service;

import ga.yuan.yogo.model.vo.InstallVO;

/**
 * 协调多个 Service 完成任务，可以引用其他Bean但<b>不能被其他Bean引用<b/>，否则可能会出现循环引用。
 */
public interface YogoService {
    /**
     * 安装博客：创建一个新的超级用户，保存网站标题选项，并创建默认分类目录，测试文章和评论
     *
     * @param installVO 安装页面的表单
     */
    void install(InstallVO installVO);
}
