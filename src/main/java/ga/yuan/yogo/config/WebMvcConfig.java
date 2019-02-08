package ga.yuan.yogo.config;

import ga.yuan.yogo.model.dto.YogoConst;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 静态资源映射，themes 主题和我们上传的文件
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/themes/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file://" + YogoConst.contentDir + "uploads/");
    }

    /**
     * 获取我们定义的 messages
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource getMessageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 使用 LocalValidatorFactoryBean 注册我们的 messageSource
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        var localValidator = new LocalValidatorFactoryBean();
        localValidator.setValidationMessageSource(getMessageSource());
        return localValidator;
    }
}
