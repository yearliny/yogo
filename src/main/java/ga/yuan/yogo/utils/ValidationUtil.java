package ga.yuan.yogo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Boolean isEmail(String input) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        Matcher m = p.matcher(input);
        return m.find();
    }
}
