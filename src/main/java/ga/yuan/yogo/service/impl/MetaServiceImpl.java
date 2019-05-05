package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
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
    public MetaDO save(MetaDO meta) {
        return metaRepository.save(meta);
    }

    @Override
    public List<MetaDO> listCategory() {
        return metaRepository.findAllByType(MetaTypeEnum.CATEGORY);
    }

    @Override
    public List<MetaDO> listTag() {
        return metaRepository.findAllByType(MetaTypeEnum.TAG);
    }
}
