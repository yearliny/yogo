package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 配置实体类，包含博客所有设置
 *  site_title 标题
 *  blog_description 描述
 *
 *  users_can_register: 是否开放注册
 *
 *  posts_per_page:（int）每页多少篇文章
 *
 */
@Data
@Table(name = "yg_options")
@Entity
public class Option implements Serializable {

    private static final long serialVersionUID = 9149588769802370048L;

    @Id
    @Column(length = 64)
    private String name;
    @Lob
    private String value;

    public Option() {
    }

}
