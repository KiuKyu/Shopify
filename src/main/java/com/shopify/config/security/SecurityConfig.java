package com.shopify.config.security;

import com.shopify.config.CustomAccessDeniedHandler;
import com.shopify.config.JwtAuthenticationFilter;
import com.shopify.config.RestAuthenticationEntryPoint;
import com.shopify.model.persistence.user.Role;
import com.shopify.model.persistence.user.User;
import com.shopify.repository.IRoleRepository;
import com.shopify.repository.IUserRepository;
import com.shopify.service.user.IRoleService;
import com.shopify.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restServiceEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @PostConstruct
    public void init() {
        Iterable<User> users = userService.findAll();
        Iterable<Role> roles = roleService.findAll();
        if (!roles.iterator().hasNext()) {
            long currentTime = System.currentTimeMillis();
            Role roleAdmin = new Role("ROLE_ADMIN", currentTime);
            roleService.save(roleAdmin);
            Role roleUser = new Role("ROLE_USER", currentTime);
            roleService.save(roleUser);
        }
        if (!users.iterator().hasNext()) {
            User admin = new User("admin", "KiuKyu94");
            userService.saveUser(admin, "ROLE_ADMIN");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServiceEntryPoint());
        http.authorizeRequests()
                .antMatchers("/login", "/register", "/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
