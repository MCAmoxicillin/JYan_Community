package com.jyan.config;

import com.jyan.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;
    /**
     * swagger接口管理页面不被拦截
     */
    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    /**
     * 拦截权限配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/register","/login","/toLogin").permitAll()
                                .antMatchers("static/*").permitAll()
                                .antMatchers(AUTH_WHITELIST).permitAll()
                                .antMatchers("/*").authenticated();
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("userpwd")
                .loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index");//登录成功后跳转页面
        // 注销配置
        http.headers().contentTypeOptions().disable();
        http.headers().frameOptions().disable(); // 图片跨域
        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        http.logout().logoutSuccessUrl("/");

        // 记住我配置
        http.rememberMe().rememberMeParameter("remember");
    }
    /**
     * 用户授权验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    /**
     * 密码加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
