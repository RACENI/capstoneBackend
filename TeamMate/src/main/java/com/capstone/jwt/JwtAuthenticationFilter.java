package com.capstone.jwt;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capstone.vo.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        // 인증이 필요 없는 URL 패턴을 설정
        if (!requestURI.contains("/api")) {
        	// requestURI.startsWith("/login-success") || requestURI.startsWith("/static") 
        	//|| requestURI.startsWith("/api/login") || requestURI.startsWith("/chlogin")
            chain.doFilter(request, response);  // 필터를 적용하지 않고 체인 계속 진행
            return;
        }
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        String userId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
            	userId = jwtUtil.extractKey(jwt);
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 상태 코드 설정
                return;
            }
        }

        if (userId != null && (SecurityContextHolder.getContext().getAuthentication() == null || !(SecurityContextHolder.getContext().getAuthentication() instanceof CustomUserDetails))) {
            if (jwtUtil.validateToken(jwt, userId)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);  // 사용자 객체 로드
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else if (userId != null && SecurityContextHolder.getContext().getAuthentication() != null) {
        	// 인증 정보가 이미 설정되어 있는 경우
            // 필요에 따라 추가 로직을 구현할 수 있습니다.
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 상태 코드 설정
            return;  // 필터 체인 중단
        }
        
        chain.doFilter(request, response);
    }
}