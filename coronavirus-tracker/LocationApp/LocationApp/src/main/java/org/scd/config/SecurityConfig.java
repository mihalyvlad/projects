package org.scd.config;



import org.scd.service.security.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String BASIC_USER_ROLE = "BASIC_USER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(createUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,  "/users/login","/user/register").permitAll()
                .antMatchers("/users/me","/locations/createLocation","/locations/getById","/locations/updateById","/locations/deleteById").hasAnyRole(ADMIN_ROLE, BASIC_USER_ROLE)
                .antMatchers( "/users/all").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/locations/interval/*").hasAnyRole(ADMIN_ROLE)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        /* To allow Pre-flight [OPTIONS] request from browser */
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    /**
     * Create password encoder bean used for encrypting the password
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Create user service bean used for find the user by email
     * @return
     */
    @Bean
    public UserDetailsService createUserDetailsService() {
        return new CustomUserDetailsServiceImpl();
    }
}
