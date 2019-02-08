package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.dto.YogoConst;

/**
 * 基础 Controller，用于支持主题切换功能
 */
public abstract class BaseController {

    /**
     * 拼接主题路径。
     * Java 8 中的字符串拼接默认使用 StringBuilder 拼接，所以可以不显式的使用 StringBuilder 拼接
     * @param viewName 我们需要的模版路径，如 index
     * @return 拼接后的路径
     */
    String render(String viewName){
        return "themes/" + YogoConst.OPTIONS.getOrDefault("template", "yogo") + "/" + viewName;
    }
}
