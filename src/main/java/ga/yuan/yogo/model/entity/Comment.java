package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.CommentStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "yogo_comments")
@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = -8982234765169639848L;

    @Id
    @GeneratedValue
    private Long coid;
    private Date created;
    //    评论填写的名称
    @NotBlank(message = "{validation.common.username-empty}")
    private String author;
    @Email(message = "{validation.common.email-error}")
    private String mail;
    private String url;
    private String ip;
    private String agent;
    @Lob
    private String body;

    /**
     * 评论状态：
     * 0：已批准(approve)
     * 1：待审核(hold)
     * 2：垃圾评论(spam)
     * 3：回收站(trash)
     */
    @Enumerated
    private CommentStatus status;

//    网站已注册用户的评论
    @ManyToOne
    private User owner;

//    评论的正文
    @ManyToOne
    private Content content;

//    评论的从属关系：谁回复谁
    @OneToOne
    @JoinColumn(name = "parent")
    private Comment parent;

    public Comment() {
    }
}
