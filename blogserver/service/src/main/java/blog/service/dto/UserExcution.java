package blog.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Epat on 2017/2/16.
 */
public class UserExcution implements Serializable{
    private Long id ;
    private Integer state ;
    private String avatar ;
    private String loginName ;
    private String nickName ;
    //用户注册城市
    private String regCity ;
    private String regIp ;
    //用户注册省份
    private String regProvince ;
    // 用户注册时间
    private Date regTime ;
    // 是否是记住我登录
    private Boolean isRemember;

    public UserExcution() {}

    public UserExcution(Long id, Integer state, String avatar, String loginName, String nickName, String regCity, String regIp, String regProvince, Date regTime, Boolean isRemember) {
        this.id = id;
        this.state = state;
        this.avatar = avatar;
        this.loginName = loginName;
        this.nickName = nickName;
        this.regCity = regCity;
        this.regIp = regIp;
        this.regProvince = regProvince;
        this.regTime = regTime;
        this.isRemember = isRemember;
    }

    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "UserExcution{" +
                "id=" + id +
                ", state=" + state +
                ", avatar='" + avatar + '\'' +
                ", loginName='" + loginName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regCity='" + regCity + '\'' +
                ", regIp='" + regIp + '\'' +
                ", regProvince='" + regProvince + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
