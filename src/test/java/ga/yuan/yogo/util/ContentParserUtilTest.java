package ga.yuan.yogo.util;

import ga.yuan.yogo.model.dto.FrontMatterBodyDTO;
import ga.yuan.yogo.model.entity.ContentDO;
import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import javax.swing.text.AbstractDocument;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ContentParserUtilTest {

    @Test
    public void loadAsFrontMatter() {
        // expected
        FrontMatterBodyDTO expected = new FrontMatterBodyDTO();
        expected.setBody("今天我要演讲的题目是如何分割字符串");
        expected.setTitle("Hello");
        expected.setComments(true);

        String text = "---\r\ntitle: Hello\r\ncomments: true\r\n---\r\n今天我要演讲的题目是如何分割字符串";
        FrontMatterBodyDTO actual = ContentParserUtil.loadAsFrontMatter(text);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void load() throws ParseException {
        String text = "---\r\ntitle: Hello\r\ncomments: true\r\n---\r\n今天我要演讲的题目是如何分割字符串";

        String title = "Hello";
        String body = "今天我要演讲的题目是如何分割字符串";
        boolean allowComment = true;

        ContentDO expected = new ContentDO();
        expected.setAllowComment(allowComment);
        expected.setTitle(title);
        // ContentParserUtil.load() 当没有设定 slug，默认为标题，所以需要进行设定
        expected.setSlug(title);
        expected.setBodyRaw(body);

        ContentDO actual = ContentParserUtil.load(text);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dump() {
        String title = "Hello";
        String body = "今天我要演讲的题目是如何分割字符串";
        boolean allowComment = true;

        FrontMatterBodyDTO expected = new FrontMatterBodyDTO();
        expected.setBody(body);
        expected.setTitle(title);
        expected.setComments(allowComment);

        ContentDO content = new ContentDO();
        content.setAllowComment(allowComment);
        content.setTitle(title);
        content.setBodyRaw(body);
        FrontMatterBodyDTO actual = ContentParserUtil.dump(content);

        Assert.assertEquals(expected, actual);
    }
}
