package ga.yuan.yogo.consts;

import ga.yuan.yogo.model.enums.OptionEnum;

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

    /**
     * 查询 OPTIONS 常量，返回 String 类型的结果
     * @param optionEnum 需要查询的选项
     * @return 返回 String 类型的结果
     */
    public static String getOption(OptionEnum optionEnum) {
        return YogoConst.OPTIONS.get(optionEnum.getKey());
    }

    /**
     * 查询 OPTIONS 常量，返回 int 类型的结果
     * @param optionEnum 需要查询的选项
     * @return 返回 int 类型的结果
     */
    public static int getOptionInt(OptionEnum optionEnum) {
        return Integer.valueOf(YogoConst.OPTIONS.get(optionEnum.getKey()));
    }

    /**
     * 当没有任何选项，判定网站未安装
     * @return 如果已经安装，返回 true
     */
    public static boolean isInstall() {
        return !OPTIONS.isEmpty();
    }

    /**
     * yogo 的数据目录。用户上传的文件在此存放
     */
    public static final String contentDir = System.getProperty("user.home") + "/yogo/";
}
