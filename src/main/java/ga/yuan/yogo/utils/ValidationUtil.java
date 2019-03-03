package ga.yuan.yogo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    /**
     * 用于判断字符串是否为 email
     *
     * @param input 传入的字符串
     * @return 如果是 email 则返回 true
     */
    public static Boolean isEmail(String input) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        Matcher m = p.matcher(input);
        return m.find();
    }
}
