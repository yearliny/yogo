package ga.yuan.yogo.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义错误页面解析器
 * 当存在错误页面，首先去主题目录搜索，否则使用 resource/templates/error 下的错误页面
 */
public class MyErrorViewResolver implements ErrorViewResolver {
    @Bean
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        return null;
    }
}
