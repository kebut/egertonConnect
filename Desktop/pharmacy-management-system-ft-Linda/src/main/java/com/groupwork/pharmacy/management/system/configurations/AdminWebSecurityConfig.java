package com.groupwork.pharmacy.management.system.configurations;

import com.groupwork.pharmacy.management.system.service.CustomAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@Order(1)
@EnableWebSecurity
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {

    public AdminWebSecurityConfig() {
        super();
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;




    @Autowired
    public AuthenticationManager authenticationManager;

    @Bean
    public UserDetailsService adminUserDetailsService(){
        return new CustomAdminDetailsService();
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(adminUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/admin/**")//any request to urls beginning with /admin/ use this config file
                .authorizeRequests()
                .antMatchers("/admin/list_medicines","/admin/list_users","/admin/list_suppliers","/admin/list_admins").authenticated()
                .anyRequest()
                .permitAll()

                .and()
                .formLogin()
                .loginPage("/LoginAdmin")
                .usernameParameter("email")
                .loginProcessingUrl("/admin/login") //should match with the http.antmatcher()
                .failureUrl("/LoginAdmin?error=loginError")
                .defaultSuccessUrl("/admin/list_medicines")

                .and()
                .logout()
                .logoutSuccessUrl("/")
                //.deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .csrf().disable();

    }


}

