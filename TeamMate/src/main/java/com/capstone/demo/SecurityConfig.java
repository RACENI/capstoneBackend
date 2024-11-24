package com.capstone.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capstone.jwt.JwtAuthenticationFilter;
import com.capstone.jwt.JwtUtil;
import com.capstone.user.service.OAuth2UserServiceImpl;
import com.capstone.user.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private JwtUtil jwtutil;
	 
	
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private OAuth2UserServiceImpl oAuth2UserService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/oauth2/**", "/api/**").authenticated() // OAuth2와 /api 경로에 대해 인증 요구
                .requestMatchers("/error", "/favicon.ico", "/logo192.png").permitAll() // /error, /favicon.ico, /logo192.png 경로는 허용
                .anyRequest().permitAll() // 나머지 요청은 모두 허용
                )
		.formLogin(formLogin -> formLogin
	            .loginPage("/form/login") // 커스텀 로그인 페이지 경로
	            .defaultSuccessUrl("/home") // 로그인 성공 시 리디렉션할 URL
	            .failureUrl("/login") // 로그인 실패 시 리디렉션 URL
	            .permitAll() // 로그인 페이지 접근은 인증 없이 허용
	        )
		.oauth2Login(oauth2Login -> oauth2Login
				.defaultSuccessUrl("/axios/login")
                .failureUrl("/login") // 실패 시 리디렉션 URL 추가
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                	    .userService(oAuth2UserService)
                	)
                )
		.csrf(csrf -> csrf.disable())
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.userDetailsService(userDetailsService);

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/", "/public/**", "/static/**", "/webjars/**");
	}
}