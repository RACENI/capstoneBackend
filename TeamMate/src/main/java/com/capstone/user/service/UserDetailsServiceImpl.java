/***
 *최초 작성자: 심건보
 *최초 작성일: 2024.11.03
 *목적: JWT 발급시 사용자 정보 전달
***/
package com.capstone.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.dao.UsersInfoMapper;
import com.capstone.vo.CustomUserDetails;
import com.capstone.vo.UsersInfo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersInfoMapper userDao;
	
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UsersInfo user = userDao.findByUserId(userId);  // 사용자 조회 쿼리
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        return new CustomUserDetails(new ArrayList<>(), user.getPassword(), user.getName(), user.getUserId(), user.getId());
    }
}