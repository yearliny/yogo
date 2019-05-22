package ga.yuan.yogo.util;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import org.junit.Assert;
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
        boolean verify = akismet.verify();
        Assert.assertTrue("Akismet 验证失败！", verify);
    }

    @Test
    public void checkAsSpam() {
        CommentDO comment = new CommentDO();
        // Test param, either value will always trigger a “SPAM” response.
        comment.setAuthor("viagra-test-123");
        comment.setEmail("akismet-guaranteed-spam@example.com");
        // required param
        comment.setIp("8.8.8.8");
        comment.setAgent("test");

        CommentStatusEnum check = akismet.check(comment);
        Assert.assertEquals(CommentStatusEnum.SPAM, check);
    }
}
