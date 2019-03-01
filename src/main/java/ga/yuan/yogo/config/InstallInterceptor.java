package ga.yuan.yogo.config;

import ga.yuan.yogo.service.YogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 博客首次运行时，需要安装后才可访问其他页面，否则跳转到 /yg-install 页面
 */
@Component
public class InstallInterceptor implements HandlerInterceptor {

    private final YogoService yogoService;

    @Autowired
    public InstallInterceptor(YogoService yogoService) {
        this.yogoService = yogoService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (yogoService.isInstalled()) {
            return true;
        }
        response.sendRedirect("/yg-install");
        return false;
    }
}
