package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.ContentDO;

import java.util.Date;
import java.util.List;

/**
 * ContentDo 获取服务
 */
public interface ContentProviderService {

    /**
     * 获取最后一次更新的时间
     * @return 最后一次更新的时间 Date
     */
    Date lastModify();

    /**
     * 获取所有 content
     * @return List<ContentDO>
     */
    List<ContentDO> getContent();

    /**
     * 获取 start 之后更新的 content
     * @return List<ContentDO>
     */
    List<ContentDO> getContent(Date start);

}
