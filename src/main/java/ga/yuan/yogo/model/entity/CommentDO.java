package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.util.CommonUtil;
import ga.yuan.yogo.util.RegexUtil;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Table(name = "yg_comment")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CommentDO implements Serializable {

    private static final long serialVersionUID = -8982234765169639848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coid;
    @CreatedDate
    private Date created;
    //    评论填写的名称
    @NotBlank(message = "{validation.username-empty}")
    private String author;
    @Email(message = "{validation.email-error}")
    private String email;
    private String url;
    @Column(length = 64)
    private String ip;
    private String agent;
    // 评论者客户端 headers 的 referrer 信息，用于验证垃圾评论
    private String referrer;
    @Lob
    private String body;

    /**
     * 评论状态：
     * 0：已批准(approve)
     * 1：待审核(hold)
     * 2：垃圾评论(spam)
     * 3：回收站(trash)
     */
    @Enumerated(EnumType.STRING)
    private CommentStatusEnum status;

    // 网站已注册用户的评论
    @ManyToOne
    private UserDO owner;

    // 评论的正文
    @ManyToOne
    private ContentDO content;

    // 评论的从属关系：谁回复谁
    @OneToOne
    @JoinColumn(name = "parent")
    private CommentDO parent;

    /**
     * 评论是否是文章作者发布
     *
     * @return boolean
     */
    @Transient
    public boolean isAdmin() {
        return owner.equals(content.getAuthor());
    }

    /**
     * 评论内容含有链接数量
     *
     * @return int
     */
    @Transient
    public int countLink() {
        Pattern p = Pattern.compile(RegexUtil.LINK);
        Matcher m = p.matcher(body);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    @Transient
    public String getGravatar(int size) {
        return CommonUtil.getGravatar(email, size);
    }

}
