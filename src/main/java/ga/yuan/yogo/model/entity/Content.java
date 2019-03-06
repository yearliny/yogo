package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.CommentStatus;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.model.enums.MetaType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Table(name = "yg_contents", indexes = {
        @Index(name = "ix_c_slug", columnList = "slug"),
        @Index(name = "ix_c_created", columnList = "created")
})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Content implements Serializable {

    private static final long serialVersionUID = 5043846654481385966L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String title;

    private String slug;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date modified;

    @Lob
    private String body;

    //    排序
    private Integer rank;

    /**
     * content 类型
     * 文章：post
     * 页面：page
     * 附件：attachment
     * 版本：revision
     */
    @Enumerated(EnumType.STRING)
    private ContentType type;

    /**
     * 文章状态
     * 0：已发表（publish）
     * 1：定时发表（future）
     * 2：草稿箱（draft）
     * 3：回收站（trash）
     */
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @Column(length = 32)
    private String password;

    private Long commentsNum;

    /**
     * boolean 在 Mysql 中会被映射为 TINYINT，true 为 1，false 为 0
     */
    private Boolean allowComment;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "yg_relations")
    private Set<Meta> metas = new HashSet<>();

    @ManyToOne
    private User author;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "content")
    private List<Comment> comments = new ArrayList<>();

    public Content() {
    }

    @Transient
    public Set<Meta> getTag() {
        return metas.stream()
                .filter(m -> MetaType.TAG.equals(m.getType()))
                .collect(Collectors.toSet());
    }

    @Transient
    public Set<Meta> getCategory() {
        return metas.stream()
                .filter(m -> MetaType.CATEGORY.equals(m.getType()))
                .collect(Collectors.toSet());
    }

    @Transient
    public List<Comment> getApproveComments() {
        return comments.stream()
                .filter(m -> CommentStatus.APPROVE.equals(m.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return cid.equals(content.cid) &&
                title.equals(content.title) &&
                slug.equals(content.slug) &&
                Objects.equals(created, content.created) &&
                Objects.equals(modified, content.modified) &&
                Objects.equals(body, content.body) &&
                Objects.equals(rank, content.rank) &&
                type == content.type &&
                status == content.status &&
                Objects.equals(password, content.password) &&
                Objects.equals(commentsNum, content.commentsNum) &&
                Objects.equals(allowComment, content.allowComment) &&
                Objects.equals(author, content.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, title, slug, created, modified, body, type, status, author);
    }
}
