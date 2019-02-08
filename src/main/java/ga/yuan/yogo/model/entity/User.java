package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "yogo_users")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -2644430559456254306L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 32, unique = true)
    private String name;

    @Column(length = 32)
    private String password;

    private String mail;

    private String url;

    private String displayName;

    private Date created;

    @OneToMany(mappedBy = "author")
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Comment> comments = new ArrayList<>();

    public User() {
    }

}
