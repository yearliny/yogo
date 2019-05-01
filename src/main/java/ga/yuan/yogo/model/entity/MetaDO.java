package ga.yuan.yogo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 项目表：类型有 tag、category、link_category（链接分类）
 */
@Data
@Table(name = "yg_meta", indexes = {@Index(name = "idx_m_slug", columnList = "slug")})
@Entity
public class MetaDO implements Serializable {

    private static final long serialVersionUID = -8417367331279302043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String name;
    private String slug;
    @Enumerated(EnumType.STRING)
    private MetaTypeEnum type;
    private String description;
    private Integer count;

    @JsonIgnore
    @ManyToMany(mappedBy = "metas")
    private Set<ContentDO> contents = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaDO meta = (MetaDO) o;
        return name.equals(meta.name) &&
                type == meta.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "MetaDO{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
