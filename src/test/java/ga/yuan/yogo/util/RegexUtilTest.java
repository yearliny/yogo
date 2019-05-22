package ga.yuan.yogo.util;

import org.junit.Assert;
import org.junit.Test;

public class RegexUtilTest {

    @Test
    public void isEmail() {
        String validEmail = "example@gmail.com";
        String invalidEmail = "example.gmail.com";
        Assert.assertTrue(String.format("%s 是有效的电子邮箱", validEmail), RegexUtil.isEmail(validEmail));
        Assert.assertFalse(String.format("%s 不是有效的电子邮箱", invalidEmail), RegexUtil.isEmail(invalidEmail));
    }
}