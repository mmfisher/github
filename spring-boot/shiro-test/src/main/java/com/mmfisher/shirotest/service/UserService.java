package com.mmfisher.shirotest.service;

import com.mmfisher.shirotest.domain.UserInfo;

public interface UserService {

    UserInfo getUserInfoByUsername(String username);
}
