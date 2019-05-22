package ga.yuan.yogo.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * Front-matter 是一个用于表现文章属性的块，以 YAML 格式进行表达
 *
 * @see <a href="https://hexo.io/docs/front-matter.html">Front-matter | Hexo</a>
 */
@Data
@Slf4j
public class FrontMatterBodyDTO {

    private String title;
    private String date;
    private String updated;
    private Boolean comments = true;
    private List<String> categories;
    private List<String> tags;
    private String permalink;
    private String slug;
    // 内容主体
    private String body;

    /**
     * 链接的选择优先级 permalink > slug > title
     *
     * @return 返回处理过的 Permalink
     */
    public String getPermalink() {
        String link;
        if (permalink != null && !permalink.isBlank()) {
            link = permalink;
        } else if (slug != null  && !slug.isBlank()) {
            link = slug;
        } else {
            link = title;
        }
        return link.strip().replaceAll(" ", "-");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrontMatterBodyDTO that = (FrontMatterBodyDTO) o;
        return title.equals(that.title) &&
                Objects.equals(date, that.date) &&
                Objects.equals(comments, that.comments) &&
                getPermalink().equals(that.getPermalink()) &&
                body.equals(that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, comments, getPermalink(), body);
    }
}
