package ga.yuan.yogo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * 安全控制：/yg-admin/** 页面受保护，未验证用户跳转到登录页面 /yg-login，其他页面不受限制
 * <p>CSRF防护：Spring Security 默认是开启的，Thymeleaf 作了额外的兼容，表单默认开启 csrf</p>
 */
@Configuration
@EnableWebSecurity
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public WebApplicationSecurity(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 用户密码使用 BCrypt 加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置权限控制，即我能做什么
     *
     * <p>具体的规则在前，不具体的规则在后，否则可能会被覆盖
     *
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/yg-admin/**")
                .authenticated()
                .and()
// successForwardUrl() 方法指定的跳转链接，会导致 “request method 'post' not supported” 错误，有待研究
                .formLogin()
                .loginPage("/yg-login")
                .defaultSuccessUrl("/yg-admin/")
                .and()

                .logout()
                .deleteCookies("rememberMe")
                .and()

                .rememberMe()
                .rememberMeParameter("rememberMe");
    }

    /**
     * 配置用户角色 role，即我是谁
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception e
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 需要获取 (username, password, enable)，数据库没有设计 enable 字段，所以总是返回 true
        String QUERY_USER = "SELECT name, password, true FROM yg_user WHERE name=?";
        String QUERY_USER_ROLE = "SELECT name, role FROM yg_user WHERE name=?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(QUERY_USER)
                .authoritiesByUsernameQuery(QUERY_USER_ROLE)
                .passwordEncoder(passwordEncoder());
    }

}