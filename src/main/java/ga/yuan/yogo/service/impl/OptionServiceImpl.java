package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.OptionDO;
import ga.yuan.yogo.repository.OptionRepository;
import ga.yuan.yogo.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> optionMap = new HashMap<>();
        List<OptionDO> optionList = optionRepository.findAll();
        for (OptionDO o : optionList) {
            optionMap.put(o.getName(), o.getValue());
        }
        return optionMap;
    }

    @Override
    public OptionDO save(OptionDO option) {
        return optionRepository.save(option);
    }

    @Override
    public List<OptionDO> saveAll(Map<String, String> optionMap) {
//        把 map 遍历填入到 option bean 中
        Set<OptionDO> optionSet = new HashSet<>();
        for (String key : optionMap.keySet()) {
            OptionDO option = new OptionDO();
            option.setName(key);
            option.setValue(optionMap.get(key));
            optionSet.add(option);
        }
        return optionRepository.saveAll(optionSet);
    }

    @Override
    public void delete(OptionDO option) {
        optionRepository.delete(option);
    }

    @Override
    public void delete(String name) {
        optionRepository.deleteByName(name);
    }

}
