package com.groupwork.pharmacy.management.system.configurations;

import com.groupwork.pharmacy.management.system.service.CustomAdminDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomClerkDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomCustomerDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@Order(4)
@EnableWebSecurity
public class CustomerWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;



    @Bean
    public UserDetailsService customerDetailsService(){
        return new CustomCustomerDetailsService();
    }




    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public DaoAuthenticationProvider authProvider1(){
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider1());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/customer/cart")
                .authenticated()
                .anyRequest()
                .permitAll()

                .and()
                .formLogin()
                .loginPage("/LoginCustomer")
                .usernameParameter("email")
                .loginProcessingUrl("/customer/login")
                .defaultSuccessUrl("/customer/cart") //direct users after successful login
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
        ;


    }


}
