package com.security.security.config.oauth;

import com.security.security.config.auth.OAuthAttributes;
import com.security.security.config.auth.PrincipalDetails;
import com.security.security.entity.User;
import com.security.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserMapper userMapper;

    // 소셜로그인 처리 함수
    // userRequest 정보 = 소셜로그인 버튼 -> 구글로그인창 -> 로그인 완료 -> Code를 리턴(Oauth2-client 라이브러리가 리턴) -> Access Token 요청
    // userRequest 정보 -> loadUser 함수 -> 해당 웹으로부터 회원프로필 정보를 받음
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 소셜 로그인 정보
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 회원가입
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(provider, userNameAttributeName, oAuth2User.getAttributes());

        String providerId = "";
        String userName = attributes.getName();
        String userEmail = provider+"_"+attributes.getEmail();
        String userPassword = passwordEncoder.encode("social");
        String userRole = "ROLE_USER";

        if(provider.equals("google")){
            providerId  = (String)attributes.getAttributes().get("sub");
        }else{
            providerId = String.valueOf(attributes.getAttributes().get("id"));
        }

        User user = new User();
        try {
            user = userMapper.checkUserId(userEmail);
            if (user == null) {
                user = User.builder()
                        .userName(userName)
                        .userEmail(userEmail)
                        .userPw(userPassword)
                        .userRole(userRole)
                        .provider(provider)
                        .userSocialKey(providerId)
                        .build();
                userMapper.signUpUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
