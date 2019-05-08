package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.service.ContentProviderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 博客关联 Webdav，即可同步 Webdav 中的内容到博客中
 */
@Service
public class WebdavContentProvider implements ContentProviderService {

    @Override
    public Date lastModify() {
        return null;
    }

    @Override
    public List<ContentDO> getContent() {
        return null;
    }

    @Override
    public List<ContentDO> getContent(Date start) {
        return null;
    }
}
