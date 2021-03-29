package com.netcracker.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //anyRequest().authenticated()
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/address.html","/deleteProduct.html","/editProduct.html","/newProduct.html",
                        "/supplier.html","/withEditSupplier.html","/withNewSupplier.html","/warehouse.html","/warehouse.html").hasRole("ADMIN")
                .antMatchers("/history.html","/product.html","/profile.html","/mainMenu.html").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin().loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/mainMenu.html")
                .and()
                .logout()
                .logoutSuccessUrl("/auth/login");

        /*
        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
         */
    }
    //

}
