package ga.yuan.yogo.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test
    public void strFromInputStream() {
        String expected = "strFromInputStream";

        InputStream is = new ByteArrayInputStream(expected.getBytes(StandardCharsets.UTF_8));
        String actual = CommonUtil.strFromInputStream(is);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getGravatar() {
        String expected = "https://dn-qiniu-avatar.qbox.me/avatar/be2682fbb092f57ce6b068e88eba03c7?s=50&r=g&d=identicon";
        String actual = CommonUtil.getGravatar("yearliny@outlook.com", 50);
        assertEquals(expected, actual);
    }
}