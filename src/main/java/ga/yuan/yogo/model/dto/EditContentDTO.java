package ga.yuan.yogo.model.dto;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import lombok.Data;

import java.util.Set;

/**
 * 发表文章 DTO
 */
@Data
public class EditContentDTO {
    private Long cid;
    private Long uid;
    private String title;
    private String body;
    private ContentTypeEnum type;
    private ContentStatusEnum status;
    private MetaDO category;
    private Set<MetaDO> tags;
    private String slug;
    private String password;
    private Boolean allowComment;
}
