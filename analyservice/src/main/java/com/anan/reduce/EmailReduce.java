package com.anan.reduce;

import com.anan.entity.EmailInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author wuguozhu
 * @date 2019/9/8 20:13
 */
public class EmailReduce implements ReduceFunction<EmailInfo> {
    @Override
    public EmailInfo reduce(EmailInfo emailInfo, EmailInfo t1) throws Exception {
        String emailtype = emailInfo.getEmailType();
        Long count1 = emailInfo.getCount();

        Long count2 = t1.getCount();

        EmailInfo emaiInfofinal = new EmailInfo();
        emaiInfofinal.setEmailType(emailtype);
        emaiInfofinal.setCount(count1+count2);

        return emaiInfofinal;
    }
}
