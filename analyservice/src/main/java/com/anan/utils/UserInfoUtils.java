package com.anan.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wuguozhu
 * @date 2019/9/8 18:12
 */
public class UserInfoUtils {

    private String userId;
    private String userName;
    private String sex;
    private String telephone;
    private String email;
    private String age;
    private String registerTime;
    private String useType;

    public void userInfo(String data){
        String[] userInfos = data.split(",");
        userId = userInfos[0];
        userName = userInfos[1];
        sex= userInfos[2];
        telephone= userInfos[3];
        email = userInfos[4];
        age= userInfos[5];
        registerTime = userInfos[6];
        useType= userInfos[7];//'终端类型：0、pc端；1、移动端；2、小程序端'

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }
}
