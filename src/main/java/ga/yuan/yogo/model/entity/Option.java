package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 配置实体类，包含博客所有设置
 *  site_title 网站标题
 *  site_url 网站域名，需要包含协议和结尾的 /,如 https://yuan.ga/
 *  site_description 博客描述
 *
 *  users_can_register: 是否开放注册
 *
 *  posts_per_page: 每页多少篇文章
 *  posts_per_rss: rss 展示最近的多少篇文章
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
