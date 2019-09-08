package com.anan.map;

import com.anan.entity.YearBase;
import com.anan.utils.DateUtils;
import com.anan.utils.HbaseUtils;
import com.anan.utils.UserInfoUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;


/**
 * @author wuguozhu
 * @date 2019/9/7
 */

public class YearBaseMap implements MapFunction<String, YearBase> {

    @Override
    public YearBase map(String s) throws Exception {

        if (StringUtils.isBlank(s)) {
            return null;
        }

//        String[] userinfos = s.split(",");
//
//        String userid = userinfos[0];
//        String username = userinfos[1];
//        String sex = userinfos[2];
//        String telphone = userinfos[3];
//        String email = userinfos[4];
//        String age = userinfos[5];
//        String registerTime = userinfos[6];
//
//        //终端类型，0：PC端；1：移动端；2：小程序端
//        String useType = userinfos[7];

        UserInfoUtils userInfo = new UserInfoUtils();
        userInfo.userInfo(s);

        String age = userInfo.getAge();
        String userId = userInfo.getUserId();

        String yearBseType = DateUtils.getYearBaseByAge(age);


        String tablename = "userflaginfo";
        String rowkey = userId;
        String famliyname = "baseinfo";
        String colum = "yearbase";


        HbaseUtils.putdata(tablename, rowkey, famliyname, colum, yearBseType);

        YearBase yearBase = new YearBase();
        String groupfield = "yearbase==" + yearBseType;

        yearBase.setYeartype(yearBseType);
        yearBase.setCount(1L);
        yearBase.setGroupfield(groupfield);

        return yearBase;
    }
}

