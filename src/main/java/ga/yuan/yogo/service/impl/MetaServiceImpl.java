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
    public MetaDO findById(long id) {
        return metaRepository.getOne(id);
    }

    @Override
    public MetaDO get(String name, MetaTypeEnum type) {
        return metaRepository.findByNameAndType(name, type);
    }

    @Override
    public MetaDO save(MetaDO meta) {
        return metaRepository.saveAndFlush(meta);
    }

    @Override
    public List<MetaDO> listMeta(MetaTypeEnum type) {
        return metaRepository.findAllByType(type);
    }
}
