package com.capstone.vo;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String name;  // 닉네임
    
    private String userId; //이메일
    private int id; // PK값

    // Constructor
    public CustomUserDetails(Collection<? extends GrantedAuthority> authorities, String password,
                             String name, String userid, int id) {
        this.authorities = authorities;
        this.password = password;
        this.name = name;
        this.userId = userId;
        this.id = id;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}
	
	/**
	 * 이하부터 개발자가 생성한 코드
	 */
	public String getUserId() {
		return userId;
	}
	public int getId() {
		return id;
	}
	
}
