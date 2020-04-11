package com.ggastudios.crupier;

import de.codecentric.boot.admin.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class WebSecuriryConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public WebSecuriryConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
//                .antMatchers(adminContextPath + "/assets/**").permitAll()
//                .antMatchers(adminContextPath + "/login.html").permitAll()
//                .antMatchers(adminContextPath + "/login.css").permitAll()
//                .antMatchers(adminContextPath + "/core.css").permitAll()
//                .antMatchers(adminContextPath + "/login/module.js").permitAll()
//                .antMatchers(adminContextPath + "/logout").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin().loginPage(adminContextPath + "/login.html").successForwardUrl("/admin").successHandler(successHandler).and()
//                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        adminContextPath + "/instances",
                        adminContextPath + "/actuator/**",
                        adminContextPath + "/api/applications"
                );
        // @formatter:on
    }

}
