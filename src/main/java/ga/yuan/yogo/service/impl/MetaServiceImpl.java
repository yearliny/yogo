package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import ga.yuan.yogo.repository.MetaRepository;
import ga.yuan.yogo.service.MetaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaServiceImpl implements MetaService {

    private final MetaRepository metaRepository;

    public MetaServiceImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public List<Meta> listCategory() {
        return metaRepository.findAllByType(MetaType.CATEGORY);
    }

    @Override
    public List<Meta> listTag() {
        return metaRepository.findAllByType(MetaType.TAG);
    }
}
