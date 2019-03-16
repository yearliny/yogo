package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.repository.OptionRepository;
import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final UserRepository userRepository;

    public OptionServiceImpl(OptionRepository optionRepository, UserRepository userRepository) {
        this.optionRepository = optionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> optionMap = new HashMap<>();
        List<Option> optionList = optionRepository.findAll();
        for (Option o : optionList) {
            optionMap.put(o.getName(), o.getValue());
        }
        return optionMap;
    }

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public List<Option> saveAll(Map<String, String> optionMap) {
//        把 map 遍历填入到 option bean 中
        Set<Option> optionSet = new HashSet<>();
        for (String key : optionMap.keySet()) {
            Option option = new Option();
            option.setName(key);
            option.setValue(optionMap.get(key));
            optionSet.add(option);
        }
        return optionRepository.saveAll(optionSet);
    }

    @Override
    public void delete(Option option) {
        optionRepository.delete(option);
    }

    @Override
    public void delete(String name) {
        optionRepository.deleteByName(name);
    }

    @Override
    public long count() {
        return optionRepository.count();
    }

}
