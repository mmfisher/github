package com.mmfisher.shirotest.comm.config;

import com.mmfisher.shirotest.domain.SysPermission;
import com.mmfisher.shirotest.domain.SysRole;
import com.mmfisher.shirotest.domain.UserInfo;
import com.mmfisher.shirotest.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 这种方式的好处是当只需要身份验证时只需要获取身份验证信息而不需要获取授权信息
 * @author yu
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();
        for(SysRole role : userInfo.getRoles()){
            for(SysPermission permission : role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token中包含用户输入的用户名和密码
        //1.从token中取出用户名
        String username = (String) authenticationToken.getPrincipal();
        //2.根据用户名从数据库查到用户信息
        UserInfo DBUser = userService.getUserInfoByUsername(username);
        //如果查询不到返回null
        if(DBUser == null){
            return null;
        }

        //AuthenticationInfo有两个作用：
        //1、如果Realm是AuthenticatingRealm子类，则提供给AuthenticatingRealm内部使用的CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
        // 2、提供给SecurityManager来创建Subject（提供身份信息）；
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                DBUser,
                //用户正确的密码
                DBUser.getPassword(),
                //TODO 加盐???
                ByteSource.Util.bytes(DBUser.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
