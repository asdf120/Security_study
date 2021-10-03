package com.security.security.Service.oauth;

import com.security.security.mapper.UserMapper;
import com.security.security.oauth.OAuthAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("service Principal 14line userRequest : " + userRequest);
        System.out.println("service Principal 14line userRequest : " + userRequest.getAccessToken());
        System.out.println("service Principal 14line userRequest : " + super.loadUser(userRequest).getAttributes());
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        System.out.println("service Principal 14line oAuth2User.getName() : " + oAuth2User.getName());

        // 현재 진행중인 서비스를 구분하기 위해 문자열로 받음. oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다. {registrationId='naver'} 이런식으로
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("service Oauth2 41line : " + registrationId);

        // OAuth2 로그인 시 키 값이 된다. 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("service Oauth2 45line : " + userNameAttributeName);

        // OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        System.out.println("service Oauth2 49line email: " + attributes.getEmail());
        System.out.println("service Oauth2 49line name: " + attributes.getName());
        System.out.println("service Oauth2 49line attributeKey: " + attributes.getNameAttributeKey());
        System.out.println("service Oauth2 49line sub: " + attributes.getAttributes().get("sub"));
        System.out.println("service Oauth2 49line id: " + attributes.getAttributes().get("id"));
        System.out.println("service Oauth2 49line phone: " + attributes.getAttributes().get("mobile"));

//        try {
//            SocialUserVO user = userMapper.login_socialId(registrationId + "_" + attributes.getEmail());
//            UserVO userVo = new UserVO();
//
//            if(user == null) {
//                userVo.setUser_email(registrationId + "_" + attributes.getEmail());
//                userVo.setUser_name(attributes.getName());
//                if(registrationId.equals("google")) {
//                    userVo.setUser_social_key((String)attributes.getAttributes().get("sub"));
//                    userVo.setUser_pw(passwordEncoder.encode((String)attributes.getAttributes().get("sub")));
//                }else {
//                    userVo.setUser_social_key(String.valueOf(attributes.getAttributes().get("id")));
//                    userVo.setUser_pw(passwordEncoder.encode(String.valueOf(attributes.getAttributes().get("id"))));
//                }
//                userMapper.signup_socialUser(userVo);
//                pointMapper.signup_userPoint(userVo);
//                userMapper.update_loginDate(userVo);
//            }else {
//                userVo.setUser_email(user.getUser_email());
//                userMapper.update_loginDate(userVo);
//            }
//
//            user = userMapper.login_socialId(userVo.getUser_email());
//
//            httpSession.setAttribute("session_user", new SessionUser(user));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        System.out.println(attributes.getAttributes());
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                , attributes.getAttributes()
                , attributes.getNameAttributeKey());
    }
}
