package ga.yuan.yogo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final InstallInterceptor installInterceptor;

    public WebMvcConfig(InstallInterceptor installInterceptor) {
        this.installInterceptor = installInterceptor;
    }

    /**
     * 拦截器：检查博客是否已经安装，否则进行跳转。排除安装页面和静态资源
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(installInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/yg-install")
                .excludePathPatterns("/yg-content/**");
    }

    /**
     * 静态资源映射
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/yg-content/**")
                .addResourceLocations("classpath:/static/");
    }

}
