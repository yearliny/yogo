package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Table(name = "yogo_users")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -2644430559456254306L;

    @Id
    @GeneratedValue
    private Long uid;
    private String name;
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
