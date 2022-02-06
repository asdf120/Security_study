package com.security.security.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // 소셜로그인 처리 함수
    // userRequest 정보 = 소셜로그인 버튼 -> 구글로그인창 -> 로그인 완료 -> Code를 리턴(Oauth2-client 라이브러리가 리턴) -> Access Token 요청
    // userRequest 정보 -> loadUser 함수 -> 해당 웹으로부터 회원프로필 정보를 받음
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("oauth2 service 14line : " + userRequest.getClientRegistration());

        System.out.println("oauth2 service 14line : " + super.loadUser(userRequest).getAttributes());
        System.out.println("oauth2 service 14line : " + super.loadUser(userRequest).getName());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        return super.loadUser(userRequest);
    }
}
