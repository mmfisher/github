package com.mmfisher.shirotest.service.impl;

import com.mmfisher.shirotest.dao.UserRepository;
import com.mmfisher.shirotest.domain.UserInfo;
import com.mmfisher.shirotest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo getUserInfoByUsername(String username){
        return userRepository.getUserInfoByUsername(username);
    }
}
