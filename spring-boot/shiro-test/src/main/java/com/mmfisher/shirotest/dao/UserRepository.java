package com.mmfisher.shirotest.dao;

import com.mmfisher.shirotest.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yu
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo getUserInfoByName(String name);

    UserInfo getUserInfoByUsername(String userName);

}
