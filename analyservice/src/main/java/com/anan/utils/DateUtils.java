package com.anan.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author wuguozhu
 * @date 2019/9/7
 */

public class DateUtils {

    public static String getYearBaseByAge(String age) {
        //获取calendar实例
        Calendar calendar = Calendar.getInstance();

        //设置当前时间
        calendar.setTime(new Date());

        //当前时间减年龄获取出生年代
        calendar.add(Calendar.YEAR, -Integer.valueOf(age));

        //获取出生年代
        Date newdate = calendar.getTime();

        //格式化年代
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String stringDate = dateFormat.format(dateFormat);

        //将年代转换为数字
        Integer intDate = Integer.valueOf(stringDate);

        //判断返回
        String yearBaseType = "未知";
        if (intDate >= 1940 && intDate < 1950) {
            yearBaseType ="40后";
        } else if (intDate >= 1950 && intDate < 1960) {
            yearBaseType ="50后";
        } else if (intDate >= 1960 && intDate < 1970) {
            yearBaseType ="60后";
        } else if (intDate >= 1970 && intDate < 1980) {
            yearBaseType ="70后";
        } else if (intDate >= 1980 && intDate < 1990) {
            yearBaseType ="80后";
        } else if (intDate >= 1990 && intDate < 2000) {
            yearBaseType ="90后";
        } else if (intDate >= 2000 && intDate < 2010) {
            yearBaseType ="00后";
        } else if (intDate >= 2010) {
            yearBaseType ="10后";
        }

        return yearBaseType;
    }
}
