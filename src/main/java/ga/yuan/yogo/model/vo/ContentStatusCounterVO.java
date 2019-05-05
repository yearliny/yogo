package ga.yuan.yogo.model.vo;

import lombok.Data;

/**
 * 文章按状态计数
 */
@Data
public class ContentStatusCounterVO {
    private Long all;
    private Long publish;
    private Long future;
    private Long draft;
    private Long trash;

    public Long getAll() {
        return publish + future + draft + trash;
    }
}
