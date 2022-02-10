package com.security.security.config.auth;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
	}

	  //of() : OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야만 합니다.
	  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
	    if("naver".equals(registrationId)) {
	      return ofNaver("id", attributes) ;
	    }else if("kakao".contentEquals(registrationId)) {
	    	return ofKakao(userNameAttributeName, attributes);
	    }

	    return ofGoogle(userNameAttributeName, attributes) ;
	  }

	  private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
	    return OAuthAttributes.builder()
	        .name((String) attributes.get("name"))
	        .email((String) attributes.get("email"))
	        .attributes(attributes)
	        .nameAttributeKey(userNameAttributeName)
	        .build() ;
	  }

	  private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
	    Map<String, Object> response = (Map<String, Object>) attributes.get("response") ;

	    return OAuthAttributes.builder()
	                .name((String) response.get("name"))
	                .email((String) response.get("email"))
	                .attributes(response)
	                .nameAttributeKey(userNameAttributeName)
	                .build() ;
	  }
	  
	  private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
	        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");  // 카카오로 받은 데이터에서 계정 정보가 담긴 kakao_account 값을 꺼낸다.
	        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");   // 마찬가지로 profile(nickname, image_url.. 등) 정보가 담긴 값을 꺼낸다.

	        return new OAuthAttributes(attributes,
	                userNameAttributeName,
	                (String) profile.get("nickname"),
	                (String) kakao_account.get("email"));
	  }

}
