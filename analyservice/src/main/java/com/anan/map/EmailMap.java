package com.anan.map;

import com.anan.entity.CarrierInfo;
import com.anan.entity.EmailInfo;
import com.anan.utils.CarrierUtils;
import com.anan.utils.EmailUtils;
import com.anan.utils.HbaseUtils;
import com.anan.utils.UserInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author wuguozhu
 * @date 2019/9/8 19:55
 */
public class EmailMap implements MapFunction<String,EmailInfo> {
    @Override
    public EmailInfo map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }

        //获取邮箱和用户id
        UserInfoUtils userInfoUtils = new UserInfoUtils();
        userInfoUtils.userInfo(s);
        String email = userInfoUtils.getEmail();
        String userId =userInfoUtils.getUserId();

        String emailType =EmailUtils.getEmailType(email);

        String tableName = "userflaginfo";
        String rowKey = userId;
        String famliyName = "baseinfo";
        String column = "emailinfo";//运营商

        //插入数据到Hbase
        HbaseUtils.putdata(tableName, rowKey, famliyName, column, emailType);

        EmailInfo emailInfo = new EmailInfo();
        String groupField = "emailInfo=="+emailType;

        emailInfo.setEmailType(emailType);
        emailInfo.setCount(1L);
        emailInfo.setGroupField(groupField);

        return emailInfo;
    }
}
