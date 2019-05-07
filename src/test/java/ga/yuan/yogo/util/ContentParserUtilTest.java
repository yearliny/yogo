package ga.yuan.yogo.util;

import ga.yuan.yogo.model.dto.FrontMatterBodyDTO;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

public class ContentParserUtilTest {
    @Test
    public void test() {
        String text = "---\r\ntitle: Hello\r\nauthor: yearliny\r\n---\r\n今天我要演讲的题目是如何分割字符串";
        int end = text.indexOf("---", 5);
        String rawMetas = text.substring(5, end);
        String content = text.substring(end + 5).trim();
        Map<String, Object> frontMatter = new HashMap<>();
        for (String meta : rawMetas.split("\r\n")) {
            String[] m = meta.split(":");
            frontMatter.put(m[0].strip(), m[1].strip());
        }
        System.out.println(rawMetas);
    }

    @Test
    public void testYaml() {
        String text = "---\r\ntitle: Hello\r\ncomments: true\r\n---\r\n今天我要演讲的题目是如何分割字符串";
        String[] t = text.split("---\r\n");
        String frontMatterText = t[1];
        String body = t[2];

        Yaml yaml = new Yaml();
        FrontMatterBodyDTO frontMatterBodyDTO = yaml.loadAs(frontMatterText, FrontMatterBodyDTO.class);
        System.out.println(frontMatterBodyDTO);
    }
}
