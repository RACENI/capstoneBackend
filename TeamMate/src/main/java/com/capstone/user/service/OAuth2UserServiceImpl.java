package com.capstone.user.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Configuration
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // OAuth2 인증 후 사용자 정보를 처리하는 로직
        // 예시로, userRequest를 기반으로 OAuth2User 객체를 반환하는 간단한 처리
        // 실제 구현에서는 OAuth2 프로바이더에 맞는 사용자 정보를 가져오는 로직을 작성할 수 있습니다.
        System.out.println("OAuth2UserRequest: " + userRequest);

        // 사용자 정보를 반환하는 예시
        return (OAuth2User) userRequest.getAdditionalParameters(); // 실제로는 OAuth2User 객체 반환
    }
}