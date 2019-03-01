package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.CommentStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Table(name = "yg_comments")
@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = -8982234765169639848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coid;
    private Date created;
    //    评论填写的名称
    @NotBlank(message = "{validation.username-empty}")
    private String author;
    @Email(message = "{validation.email-error}")
    private String mail;
    private String url;
    @Column(length = 64)
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
    @Enumerated(EnumType.STRING)
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

    /**
     * 评论是否是文章作者发布
     * @return boolean
     */
    @Transient
    public Boolean isAdmin() {
        return owner.equals(content.getAuthor());
    }

    /**
     * 评论内容含有链接数量
     * @return int
     */
    @Transient
    public int hasLinkNum() {
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(body);

        int num = 0;
        while (m.find()) {
            num ++;
        }
        return num;
    }
}
