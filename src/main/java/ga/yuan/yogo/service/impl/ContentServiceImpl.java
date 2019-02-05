package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.OptionRepository;
import ga.yuan.yogo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Page<Content> list(Integer pageNum, Integer size) {
        return contentRepository.findAllByOrderByCreatedDesc(PageRequest.of(pageNum, size));
    }

    @Override
    public Page<Content> list(Integer pageNum) {
        return contentRepository.findAllByOrderByCreatedDesc(PageRequest.of(pageNum, 5));
    }
}
