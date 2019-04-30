package ga.yuan.yogo.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 博客常量，用于减轻数据库负担并改善性能
 */
public class YogoConst {
    /**
     * yogo_options 表的常量，由 {@link ga.yuan.yogo.aop.ConstSyncAspect} 维护
     */
    public static Map<String, String> OPTIONS = new HashMap<>();

    public static boolean isInstall() {
        return !OPTIONS.isEmpty();
    }

    /**
     * yogo 的数据目录。用户上传的文件在此存放
     */
    public static final String contentDir = System.getProperty("user.home") + "/yogo/";
}
