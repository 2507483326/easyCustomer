package blog.service.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Epat on 2017/2/10.
 */


public class User implements Serializable{

    private Long id ;
    private Integer state ;
    private String avatar ;
    private String email ;
    private String loginName ;
    private String mobile ;
    private String nickName ;
    private String password ;
    //用户注册城市
    private String regCity ;
    private String regIp ;
    //用户注册省份
    private String regProvince ;
    //用户支付密码的盐
    private String salt ;
    // 用户注册时间
    private Date regTime ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegProvince() {
        return regProvince;
    }

    public void setRegProvince(String regProvince) {
        this.regProvince = regProvince;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", state=" + state +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", regCity='" + regCity + '\'' +
                ", regIp='" + regIp + '\'' +
                ", regProvince='" + regProvince + '\'' +
                ", salt='" + salt + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}