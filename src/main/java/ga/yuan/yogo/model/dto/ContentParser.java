package ga.yuan.yogo.model.dto;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import org.yaml.snakeyaml.Yaml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * 用于 {@link Content} 和 @{@link FrontMatter} 的相互转换
 */
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

    /**
     * 将 {@link FrontMatter} 转换为 {@link Content}
     *
     * @return 转换完成的 Content
     * @throws ParseException 解析错误
     */
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
        if (frontMatter.getUpdated() != null && !frontMatter.getUpdated().isBlank()) {
            content.setModified(format.parse(frontMatter.getUpdated()));
        }
        content.setAllowComment(frontMatter.getComments());
        content.setSlug(frontMatter.getPermalink());
        for (String category : frontMatter.getCategories()) {
            Meta m = new Meta();
            m.setName(category);
            m.setType(MetaType.CATEGORY);
            content.getMetas().add(m);
        }
        for (String tag : frontMatter.getTags()) {
            Meta m = new Meta();
            m.setName(tag);
            m.setType(MetaType.TAG);
            content.getMetas().add(m);
        }
        return content;
    }

    public FrontMatter dump() {
        FrontMatter frontMatter = new FrontMatter();
        frontMatter.setTitle(content.getTitle());
        frontMatter.setDate(format.format(content.getCreated()));
        frontMatter.setUpdated(format.format(content.getModified()));
        frontMatter.setComments(content.getAllowComment());
        frontMatter.setCategories(content.getCategory().stream().map(Meta::getName).collect(Collectors.toList()));
        frontMatter.setTags(content.getTag().stream().map(Meta::getName).collect(Collectors.toList()));
        frontMatter.setPermalink(content.getSlug());
        return frontMatter;
    }
}
