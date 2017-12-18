package com.mmfisher.shirotest.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * @author  yu
 */
@Entity
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue
    private Integer uid;
    /** 帐号 */
    @Column(unique =true)
    private String username;
    /** 名称（昵称或者真实姓名，不同系统不同定义）*/
    private String name;
    /** 密码 */
    private String password;
    /** 加密密码的盐 */
    private String salt;
    /** 状态 */
    private byte state;

    //急加载
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    /**
     * 密码盐.
     * 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

}
