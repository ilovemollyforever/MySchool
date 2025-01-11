package cn.haxlab.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .headers( c -> c.cacheControl(Customizer.withDefaults()) )
                .sessionManagement( c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests( c -> c.requestMatchers(
                        "/login",
                        "/logout",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/webjars/**"
                ).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .build();
    }
}
