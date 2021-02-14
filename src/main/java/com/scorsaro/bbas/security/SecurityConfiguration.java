package com.scorsaro.bbas.security;

import com.scorsaro.bbas.service.impl.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserServices userDetailsServices;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServices).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.httpBasic();
        http.csrf().ignoringAntMatchers("/account-holder");
        http.csrf().ignoringAntMatchers("/admin");
        http.csrf().ignoringAntMatchers("/admin/create/customer");
        http.csrf().ignoringAntMatchers("/admin/modify");
        http.csrf().ignoringAntMatchers("/balance/{id}");
        http.csrf().ignoringAntMatchers("/balance/all");
        http.csrf().ignoringAntMatchers("/checking/new");
        http.csrf().ignoringAntMatchers("/saving/new");
        http.csrf().ignoringAntMatchers("/credit-card/new");
        http.csrf().ignoringAntMatchers("/transaction/new");


        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/account-holder").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/account-holder").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin/create/customer").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/admin/modify").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/balance/{id}").hasRole("ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.GET, "/balance/all").hasRole("ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.GET, "/checking/all").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/checking/new").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/credit-card/all").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/credit-card/new").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/saving/all").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/saving/new").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/student/all").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/transaction/all").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/transaction/new").hasRole("ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.POST, "/transaction/tpu/to").hasRole("ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.POST, "/transaction/tpu/from").hasRole("THIRD_PARTY")
                .mvcMatchers(HttpMethod.POST, "/admin/create/third-party").hasRole("ACCOUNT_HOLDER")
                .anyRequest().permitAll();

    }
}