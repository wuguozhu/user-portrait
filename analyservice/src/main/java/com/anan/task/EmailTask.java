package com.anan.task;

import com.anan.entity.CarrierInfo;
import com.anan.entity.EmailInfo;
import com.anan.map.CarrierMap;
import com.anan.map.EmailMap;
import com.anan.reduce.EmailReduce;
import com.anan.utils.MongoUtils;
import com.anan.utils.SinkMongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * @author wuguozhu
 * @date 2019/9/8 19:52
 */
public class EmailTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<EmailInfo> resuleMap = text.map(new EmailMap());
        DataSet<EmailInfo> reduceResult = resuleMap.groupBy("groupField").reduce(new EmailReduce());

        try {
            List<EmailInfo> resultList = reduceResult.collect();

            for (EmailInfo emailInfo : resultList) {
                String carrier = emailInfo.getEmailType();
                Long count = emailInfo.getCount();
                SinkMongoUtils.SinkMongo("portait","emalistatics",carrier,count);

            }
            env.execute("email analy");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
