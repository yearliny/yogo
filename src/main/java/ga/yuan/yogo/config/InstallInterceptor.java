package ga.yuan.yogo.config;

import ga.yuan.yogo.consts.YogoConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 博客（首次）运行时，需要安装后才可访问其他页面，否则跳转到 /yg-install 页面
 */
@Component
public class InstallInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (YogoConst.isInstall()) {
            return true;
        }
        // 否则重定向至安装页面
        response.sendRedirect("/yg-install");
        return false;
    }
}
