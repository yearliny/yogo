package ga.yuan.yogo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 让 Spring Validation 使用 message.properties
 */
@Configuration
public class ValidatorConfig {
    /**
     * 获取我们定义的 messages
     *
     * @return MessageSource
     */
    @Bean
    private MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

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
        LocalValidatorFactoryBean localValidator = new LocalValidatorFactoryBean();
        localValidator.setValidationMessageSource(getMessageSource());
        return localValidator;
    }
}
