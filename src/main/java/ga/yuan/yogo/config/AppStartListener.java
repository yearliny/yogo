package ga.yuan.yogo.config;

import ga.yuan.yogo.model.dto.YogoConst;
import ga.yuan.yogo.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Application 运行时的事件，如加载常量
 */
@Slf4j
@Configuration
public class AppStartListener implements ApplicationListener<ApplicationStartedEvent> {

    private final OptionService optionService;

    @Autowired
    public AppStartListener(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        loadOptions();
    }

    /**
     * 应用运行后，保存设置到常量中
     */
    void loadOptions() {
        YogoConst.OPTIONS = optionService.findAll();
        log.info("load options to const.");
    }
}
