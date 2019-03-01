package ga.yuan.yogo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * 安全控制：/yg-admin/** 页面受保护，未验证用户跳转到登录页面 /yg-login，其他页面不受限制
 * <p>
 * CSRF防护：Spring Security 默认是开启的，Thymeleaf 作了额外的兼容，表单默认开启 csrf
 */
@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public ApplicationSecurity(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置权限控制，即我能做什么
     *
     * <p>具体的规则在前，不具体的规则在后，否则可能会被覆盖</p>
     *
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/yg-admin/**").authenticated()
                .and()
                .formLogin().loginPage("/yg-login").successForwardUrl("/yg-admin/")
                .and()
                .logout()
                .logoutUrl("/yg-login?action=logout")
                .logoutSuccessUrl("/yg-login")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me")
                .and()
                .rememberMe().rememberMeParameter("remember-me");
    }

    /**
     * 配置用户角色 role，即我是谁
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String QUERY_USER = "SELECT name, password, true FROM yg_users WHERE name=?";
        String QUERY_USER_ROLE = "SELECT name, role FROM yg_users WHERE name=?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(QUERY_USER)
                .authoritiesByUsernameQuery(QUERY_USER_ROLE)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}