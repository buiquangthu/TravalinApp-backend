package com.bqt.flight_booking_server.configuration;

import com.bqt.flight_booking_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private UserRepository userRepository;

//    private final UserDetailsService userDetailsService;
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userDetailsService = userDetailsService;
//    }

    private final String[] PUBLIC_ENDPOINTS = {
        "/users/register", "/users/login", "/users/forgot-password", "/users/verify-otp", "/users/reset-password", "/users/ping"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public JwtFilter jwtFilter(){
//        return new JwtFilter(jwtTokenProvider, userDetailsService);
//    }


//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(){
//        return new ProviderManager(List.of(authenticationProvider()));
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return email -> (UserDetails) userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.getAllowCredentials();
//
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

}
