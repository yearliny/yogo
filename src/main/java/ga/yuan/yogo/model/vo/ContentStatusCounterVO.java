package ga.yuan.yogo.model.vo;

import lombok.Data;
import lombok.Value;

/**
 * 文章按状态计数
 */
@Value
public class ContentStatusCounterVO {
    long publish;
    long future;
    long draft;
    long trash;

    public long getAll() {
        return publish + future + draft + trash;
    }
}
