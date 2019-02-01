package ga.yuan.yogo.config;

import freemarker.template.TemplateModelException;
import ga.yuan.yogo.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * FreeMarker 配置常量，如 Option 等
 */
@Slf4j
@Configuration
public class FreeMarkerAutoConfiguration {
    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private OptionService optionService;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("options", optionService.findAllOptions());
        } catch (TemplateModelException e) {
            log.error(e.getMessage());
        }
    }
}
