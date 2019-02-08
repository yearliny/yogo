package ga.yuan.yogo.config;

import freemarker.template.TemplateModelException;
import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * FreeMarker 配置常量，如 Option 等
 */
@Slf4j
@Configuration
public class FreemarkerConfig implements WebMvcConfigurer {
    private final freemarker.template.Configuration configuration;
    private final OptionService optionService;

    @Autowired
    public FreemarkerConfig(freemarker.template.Configuration configuration, OptionService optionService) {
        this.configuration = configuration;
        this.optionService = optionService;
    }

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("options", YogoConst.OPTIONS);
            log.info("FreemarkerConfig load SharedVariable.");
        } catch (TemplateModelException e) {
            log.error(e.getMessage());
        }
    }
}
