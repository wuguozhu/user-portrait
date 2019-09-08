package com.anan.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wuguozhu
 * @date 2019/9/8 18:33
 */
public class UserInfoUtilsTest {

    @Test
    public void userInfo() {
        UserInfoUtils userInfo = new UserInfoUtils();
        userInfo.userInfo("1,zhangsan,男,13212323212,zhangsan@anan.com,34,20190908,0");

        String age = userInfo.getAge();
        System.out.println("年龄：" + age);
    }
}

/**
 * private String userId;
 * private String userName;
 * private String sex;
 * private String telephone;
 * private String email;
 * private String age;
 * private String registerTime;
 * private String useType;
 */