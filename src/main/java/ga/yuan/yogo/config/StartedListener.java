package ga.yuan.yogo.config;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.service.OptionService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 应用启动后执行操作：加载常量
 */
@Configuration
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final OptionService optionService;

    public StartedListener(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        YogoConst.OPTIONS = optionService.findAll();
    }
}
