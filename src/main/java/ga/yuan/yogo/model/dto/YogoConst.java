package ga.yuan.yogo.model.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 博客经常用到的常量
 */
public class YogoConst {
    /**
     * yogo_options 表的常量，由 ConstSyncAspect 维护
     */
    public static Map<String, String> OPTIONS = new HashMap<>();

    /**
     * yogo 的数据目录。用户上传的文件在此存放
     */
    public static final String contentDir = System.getProperty("user.home") + "/yogo/";
}
