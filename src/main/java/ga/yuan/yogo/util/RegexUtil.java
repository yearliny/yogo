package ga.yuan.yogo.util;

import javax.validation.constraints.Email;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final Pattern LINK = Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    public static final Pattern EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);

    /**
     * 用于判断字符串是否为 email
     *
     * @param input 传入的字符串
     * @return 如果是 email 则返回 true
     */
    public static boolean isEmail(String input) {
        return EMAIL.matcher(input).matches();
    }

    /**
     * 传入一个链接，截取协议和域名部分。
     * @param str 如 https://yuan.ga/about
     * @return 如 https://yuan.ga
     */
    public static String getBaseUrl(String str) {
        return str.substring(0, str.indexOf("/", 8));
    }
}
