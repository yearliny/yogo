package ga.yuan.yogo.utils;

import org.junit.Before;
import org.junit.Test;

public class AkismetTest {
    private Akismet akismet;

    @Before
    public void before() {
        akismet = new Akismet("f9e206045640", "https://yuan.ga");
    }

    @Test
    public void testVerify() {
        System.out.println(akismet.verify());
    }
}
