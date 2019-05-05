package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Table(name = "yg_content", indexes = {
        @Index(name = "idx_c_slug", columnList = "slug"),
        @Index(name = "idx_c_created", columnList = "created")
})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ContentDO implements Serializable {

    private static final long serialVersionUID = 5043846654481385966L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String title;
    @Column(length = 200)
    private String slug;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    @Lob
    private String bodyRaw;
    @Lob
    private String bodyRender;

    /**
     * content 类型
     * 文章：post
     * 页面：page
     * 附件：attachment
     * 版本：revision
     */
    @Enumerated(EnumType.STRING)
    private ContentTypeEnum type;
    /**
     * 文章状态
     * 0：已发表（publish）
     * 1：定时发表（future）
     * 2：草稿箱（draft）
     * 3：回收站（trash）
     */
    @Enumerated(EnumType.STRING)
    private ContentStatusEnum status;
    @Column(length = 32)
    private String password;
    private Long commentsNum;
    /**
     * boolean 在 Mysql 中会被映射为 TINYINT，true 为 1，false 为 0
     */
    private Boolean allowComment;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "yg_relation",
            joinColumns = @JoinColumn(name = "cid"),
            inverseJoinColumns = @JoinColumn(name = "mid"))
    private Set<MetaDO> metas = new HashSet<>();
    @ManyToOne
    private UserDO author;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "content")
    private List<CommentDO> comments = new ArrayList<>();

    /**
     * 从 metas 中筛选出 tag
     *
     * @return Set<MetaDO>
     */
    @Transient
    public Set<MetaDO> getTag() {
        return metas.stream()
                .filter(m -> MetaTypeEnum.TAG.equals(m.getType()))
                .collect(Collectors.toSet());
    }

    /**
     * 从 metas 中筛选出 category
     *
     * @return Set<MetaDO>
     */
    @Transient
    public Set<MetaDO> getCategory() {
        return metas.stream()
                .filter(m -> MetaTypeEnum.CATEGORY.equals(m.getType()))
                .collect(Collectors.toSet());
    }

    /**
     * 获取已经通过的评论
     * @return List<CommentDO>
     */
    @Transient
    public List<CommentDO> getApproveComments() {
        return comments.stream()
                .filter(m -> CommentStatusEnum.APPROVE.equals(m.getStatus()))
                .collect(Collectors.toList());
    }

    @Transient
    public void addMeta(MetaDO meta) {
        metas.add(meta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentDO content = (ContentDO) o;
        return cid.equals(content.cid) &&
                title.equals(content.title) &&
                slug.equals(content.slug) &&
                Objects.equals(created, content.created) &&
                Objects.equals(modified, content.modified) &&
                Objects.equals(bodyRaw, content.bodyRaw) &&
                type == content.type &&
                status == content.status &&
                Objects.equals(password, content.password) &&
                Objects.equals(commentsNum, content.commentsNum) &&
                Objects.equals(allowComment, content.allowComment) &&
                Objects.equals(author, content.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, title, slug, created, modified, bodyRaw, type, status, author);
    }
}
