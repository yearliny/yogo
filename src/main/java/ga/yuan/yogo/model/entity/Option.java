package ga.yuan.yogo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 配置实体类，包含博客所有设置
 *
 */
@Data
@Table(name = "yogo_options")
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
