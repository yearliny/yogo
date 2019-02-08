package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    //    Spring 4.3 or later,implicit constructor inject dependency
    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    //    返回已发表的文章
    @Override
    public Page<Content> listPosts(Integer pageNum, Integer size) {
        return contentRepository
                .findByTypeAndStatusOrderByCreatedDesc(
                        ContentType.POST,
                        ContentStatus.PUBLISH,
                        PageRequest.of(pageNum, size));
    }

    @Override
    public Page<Content> listPosts(Integer pageNum) {
        String key = "posts_per_page";
//        默认值：每页5篇文章
        String size = "5";
        if (YogoConst.OPTIONS.containsKey(key)) {
            size = YogoConst.OPTIONS.get(key);
        }
        return listPosts(pageNum, Integer.valueOf(size));
    }
}
