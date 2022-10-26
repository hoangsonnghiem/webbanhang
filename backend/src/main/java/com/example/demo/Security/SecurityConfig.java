package com.example.demo.Security;

import com.example.demo.Customer.CustomerServiceImplement;
import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomerServiceImplement customerService;

    @Autowired
    public SecurityConfig(CustomerServiceImplement customerService)
    {
        this.customerService = customerService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll();
//                .antMatchers("/user/**", "/product/**")
//                .permitAll()
//                .antMatchers("/product/change/", "product/active")
//                .hasRole("1")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(customPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customerService);
        return daoAuthenticationProvider;
    }

    @Bean
    public DemoApplication.CustomPasswordEncoder customPasswordEncoder(){
        return new DemoApplication.CustomPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService()
    {
        return super.userDetailsService();
    }
}
