package ga.yuan.yogo.utils;

import javax.validation.constraints.Email;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final String LINK = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
    public static final String EMAIL = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    /**
     * 用于判断字符串是否为 email
     *
     * @param input 传入的字符串
     * @return 如果是 email 则返回 true
     */
    public static boolean isEmail(String input) {
        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(input);
        return m.find();
    }
}
