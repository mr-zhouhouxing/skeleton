package io.pandora.mall.config.security;

import io.pandora.mall.module.security.MyAuthenticationEntryPoint;
import io.pandora.mall.module.security.MyAuthenticationFilter;
import io.pandora.mall.module.security.MyLoginFiler;
import io.pandora.mall.module.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 读取忽略的配置文件
     */
    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    /**
     * 异常处理
     */
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    /**
     * 验证用户名密码的业务类
     */
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    MyAuthenticationFilter myAuthenticationFilter;

    @Bean
    MyLoginFiler myLoginFiler() throws Exception {
        MyLoginFiler myLoginFiler = new MyLoginFiler();
        myLoginFiler.setAuthenticationManager(authenticationManagerBean());
        return myLoginFiler;
    }

    @Bean
    public FilterRegistrationBean myAuthenticationFilterRegistration(MyAuthenticationFilter myAuthenticationFilter) {
        FilterRegistrationBean<MyAuthenticationFilter> registration = new FilterRegistrationBean<>(myAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public FilterRegistrationBean myLoginFilerRegistration(MyLoginFiler myLoginFiler) {
        FilterRegistrationBean<MyLoginFiler> registration = new FilterRegistrationBean<>(myLoginFiler);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * 构建AuthenticationManager权限管理对象
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
                /*
                可添加自定义授权认证提供器authenticationProvider
                * */
                /*.and()
                .authenticationProvider("Your authenticationProvider");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 Spring Security 自带的跨域处理
                .cors()
                .and()
                // 不创建会话(不使用session)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // csrf安全
                .csrf()
                .disable()
                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                //设置过滤器
                .addFilterAt(myLoginFiler(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //允许不登录访问的接口
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        //跨域
        registry.antMatchers(HttpMethod.OPTIONS, "/**").anonymous();
        //不需要authenticate的接口
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        //需要authenticate才允许访问
        filterIgnorePropertiesConfig.getAuthenticates().forEach(url -> registry.antMatchers(url).authenticated());
        //其他接口需要authenticate
        registry.anyRequest().authenticated();
    }

    /**
     * swagger等静态文件授权忽略
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/**/*.html", "/**/*.css", "/**/*.js", "/favicon.ico");
    }

}
