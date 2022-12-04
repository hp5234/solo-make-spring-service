package com.jeon.book.springboot.config.auth.dto;

import com.jeon.book.springboot.domain.user.User;
import lombok.Getter;

// SessionUser : 세션에 사용자 정보를 저장하기 위한 Dto 클래스
// 인증된 사용자 정보만 사용
@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
