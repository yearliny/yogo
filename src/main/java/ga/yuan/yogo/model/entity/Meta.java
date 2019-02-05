package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.MetaType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目表：类型有 tag、category、link_category（链接分类）
 */
@Data
@Table(name = "yogo_metas", indexes = {@Index(name = "ix_m_slug", columnList = "slug")})
@Entity
public class Meta implements Serializable {

    private static final long serialVersionUID = -8417367331279302043L;

    @Id
    @GeneratedValue
    private Long mid;
    private String name;
    private String slug;
    private MetaType type;
    private String description;
    private Integer count;
    private Integer order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "relations", joinColumns = {@JoinColumn(name = "mid")},
            inverseJoinColumns = {@JoinColumn(name = "cid")})
    private Set<Content> contents = new HashSet<>();

    public Meta() {
    }

}
