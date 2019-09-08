package com.anan.map;

import com.anan.entity.CarrierInfo;
import com.anan.utils.CarrierUtils;
import com.anan.utils.HbaseUtils;
import com.anan.utils.UserInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author wuguozhu
 * @date 2019/9/8 17:40
 */


public class CarrierMap implements MapFunction<String, CarrierInfo> {
    @Override
    public CarrierInfo map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        UserInfoUtils userInfoUtils = new UserInfoUtils();
        userInfoUtils.userInfo(s);

        String telephone = userInfoUtils.getTelephone();
        String userId =userInfoUtils.getUserId();


        int carrierType = CarrierUtils.getCarrierByTel(telephone);
        String carrierTypeString = carrierType == 0 ? "未知运营商" : carrierType == 1 ? "移动用户" : carrierType == 2 ? "联通用户" : "电信用户";

        String tableName = "userflaginfo";
        String rowKey = userId;
        String famliyName = "baseinfo";
        String column = "carrierinfo";//运营商
        HbaseUtils.putdata(tableName, rowKey, famliyName, column, carrierTypeString);
        CarrierInfo carrierInfo = new CarrierInfo();
        String groupField = "carrierInfo==" + carrierType;
        carrierInfo.setCount(1L);
        carrierInfo.setCarrier(carrierTypeString);
        carrierInfo.setGroupfield(groupField);
        return carrierInfo;
    }
}

