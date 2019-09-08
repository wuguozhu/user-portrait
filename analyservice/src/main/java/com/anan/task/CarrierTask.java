package com.anan.task;

import com.anan.entity.CarrierInfo;
import com.anan.entity.YearBase;
import com.anan.map.CarrierMap;
import com.anan.reduce.CarrierReduce;
import com.anan.utils.MongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @author wuguozhu
 * @date 2019/9/8 17:39
 */
public class CarrierTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<CarrierInfo> resuleMap = text.map(new CarrierMap());

        DataSet<CarrierInfo> reduceResult = resuleMap.groupBy("groupfield").reduce(new CarrierReduce());
        try {
            List<CarrierInfo> resultList = reduceResult.collect();

            for (CarrierInfo carrierInfo : resultList) {
                String carrier = carrierInfo.getCarrier();
                Long count = carrierInfo.getCount();
                //String tablename, String database, String yearbasetype
                Document doc = MongoUtils.findByOne("carrierstatics", "portait", carrier);
                if (doc == null) {
                    doc = new Document();
                    doc.put("info", carrier);
                    doc.put("count", count);
                } else {
                    Long resultCount = doc.getLong("count");
                    Long total = resultCount + count;

                    doc.put("count",total);

                }
                MongoUtils.saveOrUpdateMongo("carrierstatics","portait",doc);

            }
            env.execute("carrier analy");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
