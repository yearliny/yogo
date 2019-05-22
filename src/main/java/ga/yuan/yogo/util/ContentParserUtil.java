package ga.yuan.yogo.util;

import ga.yuan.yogo.model.dto.FrontMatterBodyDTO;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import org.yaml.snakeyaml.Yaml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * 工具类用于 {@link ContentDO} 和 {@link ContentDO#getBodyRaw} 的相互转换
 */
public class ContentParserUtil {
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ContentParserUtil() {
    }

    /**
     * 从原始输入转换为 FrontMatterBodyDTO 对象
     *
     * @param rawInput 用户输入
     * @return FrontMatterBodyDTO
     */
    public static FrontMatterBodyDTO loadAsFrontMatter(String rawInput) {
        Yaml yaml = new Yaml();
        String[] rc = rawInput.split("---\r\n");
        String frontMatterText = rc[1];
        String body = rc[2];
        FrontMatterBodyDTO frontMatter = yaml.loadAs(frontMatterText, FrontMatterBodyDTO.class);
        frontMatter.setBody(body);
        return frontMatter;
    }

    /**
     * 将 {@link FrontMatterBodyDTO} 转换为 {@link ContentDO}
     *
     * @return 转换完成的 ContentDO
     * @throws ParseException 解析错误
     */
    public static ContentDO load(String rawInput) throws ParseException {
        ContentDO content = new ContentDO();
        FrontMatterBodyDTO frontMatter = loadAsFrontMatter(rawInput);

        content.setTitle(frontMatter.getTitle());
        content.setBodyRaw(frontMatter.getBody());
        if (frontMatter.getDate() != null && !frontMatter.getDate().isBlank()) {
            content.setCreated(format.parse(frontMatter.getDate()));
        }
        if (frontMatter.getUpdated() != null && !frontMatter.getUpdated().isBlank()) {
            content.setModified(format.parse(frontMatter.getUpdated()));
        }
        content.setAllowComment(frontMatter.getComments());
        content.setSlug(frontMatter.getPermalink());
        if (frontMatter.getCategories() != null) {
            for (String category : frontMatter.getCategories()) {
                MetaDO m = new MetaDO();
                m.setName(category);
                m.setType(MetaTypeEnum.CATEGORY);
                content.getMetas().add(m);
            }
        }
        if (frontMatter.getTags() != null) {
            for (String tag : frontMatter.getTags()) {
                MetaDO m = new MetaDO();
                m.setName(tag);
                m.setType(MetaTypeEnum.TAG);
                content.getMetas().add(m);
            }
        }

        return content;
    }

    /**
     * 通过 ContentDO 导出其文章属性为 FrontMatterBodyDTO
     *
     * @return FrontMatterBodyDTO
     */
    public static FrontMatterBodyDTO dump(ContentDO content) {
        FrontMatterBodyDTO frontMatterBodyDTO = new FrontMatterBodyDTO();
        frontMatterBodyDTO.setTitle(content.getTitle());
        if (content.getCreated() != null) {
            frontMatterBodyDTO.setDate(format.format(content.getCreated()));
        }
        if (content.getModified() != null) {
            frontMatterBodyDTO.setUpdated(format.format(content.getModified()));
        }
        frontMatterBodyDTO.setComments(content.getAllowComment());
        frontMatterBodyDTO.setCategories(content.getCategory().stream().map(MetaDO::getName).collect(Collectors.toList()));
        frontMatterBodyDTO.setTags(content.getTag().stream().map(MetaDO::getName).collect(Collectors.toList()));
        frontMatterBodyDTO.setPermalink(content.getSlug());
        frontMatterBodyDTO.setBody(content.getBodyRaw());
        return frontMatterBodyDTO;
    }
}
