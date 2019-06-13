package ga.yuan.yogo.config;

import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 用于表单提交中，将String类型的id转换为 MetaDO 类型
 */
@Component
public class StringToMetaConverter implements Converter<String, MetaDO> {

    private MetaService metaService;

    @Autowired
    public void setMetaService(MetaService metaService) {
        this.metaService = metaService;
    }

    @Override
    public MetaDO convert(String source) {
        return metaService.findById(Long.valueOf(source));
    }
}
