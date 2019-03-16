package ga.yuan.yogo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ga.yuan.yogo.model.enums.MetaType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 项目表：类型有 tag、category、link_category（链接分类）
 */
@Getter
@Setter
@Table(name = "yg_metas", indexes = {@Index(name = "ix_m_slug", columnList = "slug")})
@Entity
public class Meta implements Serializable {

    private static final long serialVersionUID = -8417367331279302043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String name;
    @JsonIgnore
    private String slug;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private MetaType type;
    private String description;
    private Integer count;
    private Integer order;

    @JsonIgnore
    @ManyToMany(mappedBy = "metas")
    private Set<Content> contents = new HashSet<>();

    public Meta() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meta meta = (Meta) o;
        return mid.equals(meta.mid) &&
                name.equals(meta.name) &&
                slug.equals(meta.slug) &&
                type == meta.type &&
                Objects.equals(description, meta.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, name, slug, type);
    }
}
