package ga.yuan.yogo.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Front-matter 是一个用于表现文章属性的块，以 YAML 格式进行表达
 *
 * @see <a href="https://hexo.io/docs/front-matter.html">Front-matter | Hexo</a>
 */
@Data
@Slf4j
public class FrontMatter {

    private String title = "";
    private String date;
    private String updated;
    private Boolean comments = true;
    private List<String> categories;
    private List<String> tags;
    private String permalink = "";
    private String slug = "";

    /**
     * 链接的选择优先级 permalink > slug > title
     *
     * @return 返回处理过的 Permalink
     */
    public String getPermalink() {
        String link;
        if (!permalink.isEmpty() && !permalink.isBlank()) {
            link = permalink;
        } else if (!slug.isEmpty() && !slug.isBlank()) {
            link = slug;
        } else {
            link = title;
        }
        return permalink.strip().replaceAll(" ", "-");
    }
}
