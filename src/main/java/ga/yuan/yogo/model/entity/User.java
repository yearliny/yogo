package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
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
    private Set<Content> contents = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Comment> comments = new HashSet<>();

    public User() {
    }

}
