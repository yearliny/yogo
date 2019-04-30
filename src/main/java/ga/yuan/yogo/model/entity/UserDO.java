package ga.yuan.yogo.model.entity;

import ga.yuan.yogo.model.enums.UserRoleEnum;
import ga.yuan.yogo.util.CommonUtil;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UserDO 实体类，{@link EntityListeners} 注解用于支持 Spring Data JPA 的 {@link CreatedDate} 注解的功能
 */
@Data
@Table(name = "yg_users")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserDO implements Serializable {

    private static final long serialVersionUID = -2644430559456254306L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Email(message = "{validation.email-error}")
    @Column(unique = true, nullable = false)
    private String email;

    private String url;

    private String displayName;

    @CreatedDate
    private Date created;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<ContentDO> contents = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<CommentDO> comments = new ArrayList<>();

    @Transient
    public String getGravatar(int size) {
        return CommonUtil.getGravatar(email, size);
    }

}
