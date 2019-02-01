package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.Content;
import org.springframework.data.domain.Page;

public interface ContentService {
    Page<Content> list(Integer pageNum, Integer size);

    Page<Content> list(Integer pageNum);
}
