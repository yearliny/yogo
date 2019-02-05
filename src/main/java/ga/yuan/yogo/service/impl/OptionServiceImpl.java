package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.Option;
import ga.yuan.yogo.repository.OptionRepository;
import ga.yuan.yogo.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Map<String, String> findAll() {
        Map<String, String> optionMap = new HashMap<>();
        List<Option> optionList = optionRepository.findAll();
        for (Option o : optionList ) {
            optionMap.put(o.getName(), o.getValue());
        }
        return optionMap;
    }
}
