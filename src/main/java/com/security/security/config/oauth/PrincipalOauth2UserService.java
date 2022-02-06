package com.security.security.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // 소셜로그인 처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("oauth2 service 14line : " + userRequest.getClientRegistration());
        System.out.println("oauth2 service 14line : " + userRequest.getAccessToken().getTokenValue());
        System.out.println("oauth2 service 14line : " + super.loadUser(userRequest).getAttributes());
        System.out.println("oauth2 service 14line : " + super.loadUser(userRequest).getName());

        return super.loadUser(userRequest);
    }
}
