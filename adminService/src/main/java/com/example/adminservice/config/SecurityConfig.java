package com.example.adminservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //cross site request forgery attack disable
                .cors().disable() //http zaproslarni ochish
                .authorizeRequests()
                // .antMatchers(HttpMethod.GET,"/api/group").permitAll()
                .antMatchers("/**")
                .authenticated()
                .and().httpBasic(); // har bir zapros kelgan ichida albattga username va parol bo'lishi kerak

//        http.addFilterBefore().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    //role based
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //bazadan emas vaqtinchalik malumotni qo'shib turish
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("111")).roles("MANAGER");
    }

    //parolni kodlash
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //defaultni
    }

}
