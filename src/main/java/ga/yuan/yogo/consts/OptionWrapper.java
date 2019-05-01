package ga.yuan.yogo.consts;

import ga.yuan.yogo.model.enums.OptionEnum;

/**
 * OptionDO 对象常量形式的包装类，便于取用，并在此设定默认值
 */
public class OptionWrapper {

    public static int getPostsPerPage() {
        String postsPerPage = YogoConst.OPTIONS.getOrDefault(OptionEnum.POSTS_PER_PAGE.getKey(), "5");
        return Integer.valueOf(postsPerPage);
    }

    public static String getSiteTitle() {
        return YogoConst.OPTIONS.get(OptionEnum.SITE_TITLE.getKey());
    }
}
