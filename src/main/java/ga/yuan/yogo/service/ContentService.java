package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.Content;
import org.springframework.data.domain.Page;

public interface ContentService {
    Page<Content> listPosts(Integer pageNum, Integer size);

    Page<Content> listPosts(Integer pageNum);
}
