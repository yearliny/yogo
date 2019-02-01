package ga.yuan.yogo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Table(name = "contents", indexes = {
        @Index(name = "ix_c_slug", columnList = "slug"),
        @Index(name = "ix_c_created", columnList = "created")
})
@Entity
public class Content implements Serializable {

    private static final long serialVersionUID = 5043846654481385966L;

    @Id
    @GeneratedValue
    private Long cid;
    private String title;
    private String slug;
    private Date created;
    private Date modified;

    @Lob
    @Column
    private String body;
    private Integer order;

    /**
     * content 类型
     * 文章：post
     * 页面：page
     * 附件：attachment
     * 版本：revision
     */
    @Enumerated
    private ContentType type;

    /**
     * 文章状态
     * 0：已发表（publish）
     * 1：定时发表（future）
     * 2：草稿箱（draft）
     * 3：回收站（trash）
     */
    @Enumerated
    private ContentStatus status;

    @Column(length = 32)
    private String password;
    private Long commentsNum;
    private Character allowComment;

    @ManyToMany(mappedBy = "contents")
    private Set<Meta> metas = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "content")
    private List<Comment> comments;

    public Content() {
    }

    @Transient
    public Set<Meta> getTag() {
        return metas.stream()
                .filter((Meta m) -> m.getType().equals("tag"))
                .collect(Collectors.toSet());
    }

    @Transient
    public Set<Meta> getCategory() {
        return metas.stream()
                .filter((Meta m) -> m.getType().equals("category"))
                .collect(Collectors.toSet());
    }

}
