package ga.yuan.yogo.model.dto;

import ga.yuan.yogo.model.entity.Content;
import org.yaml.snakeyaml.Yaml;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ContentParser {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String rawBody;
    private Content content;

    public ContentParser() {
    }

    public ContentParser(String rawBody) {
        this.rawBody = rawBody;
    }

    public ContentParser(Content content) {
        this.content = content;
    }

    public Content load() throws ParseException {
        Yaml yaml = new Yaml();
        String[] rc = rawBody.split("---\r\n");
        String frontMatterText = rc[1];
        String body = rc[2];
        FrontMatter frontMatter = yaml.loadAs(frontMatterText, FrontMatter.class);

        content.setTitle(frontMatter.getTitle());
        content.setBodyRaw(rawBody);
        if (frontMatter.getDate() != null && !frontMatter.getDate().isBlank()) {
            content.setCreated(format.parse(frontMatter.getDate()));
        }
        return content;
    }
}
