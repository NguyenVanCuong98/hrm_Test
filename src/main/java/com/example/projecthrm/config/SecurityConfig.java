package com.example.projecthrm.config;

import com.example.projecthrm.jwt.JwtFilter;
import com.example.projecthrm.service.imp.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountServiceImp accountServiceImp;
    @Autowired
    private JwtFilter jwtFilter;
    // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(10);}
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable(); // Ngăn chặn request từ một domain khác
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/users/**").hasRole("hr")
                .antMatchers("/api/status/**","/test1","/api/users/**").hasRole("admin")
                .antMatchers("/api/login","/api/**","/api/users/**","/api/status/**","/api/permission/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountServiceImp).passwordEncoder(passwordEncoder());// Cung cáp userservice cho spring security
        // cung cấp password encoder
    }

}

