package com.anan.task;

import com.anan.entity.YearBase;
import com.anan.map.YearBaseMap;
import com.anan.reduce.YearBaseReduce;
import com.anan.utils.MongoUtils;
import com.anan.utils.SinkMongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * @author wuguozhu
 * @date 2019/9/7
 */

public class YearBaseTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<YearBase> mapresult = text.map(new YearBaseMap());

        DataSet<YearBase> reduceresult = mapresult.groupBy("groupfield").reduce(new YearBaseReduce());
        try {
            List<YearBase> resultList = reduceresult.collect();

            for (YearBase yearBase : resultList) {
                String yearType = yearBase.getYeartype();
                Long count = yearBase.getCount();
                SinkMongoUtils.SinkMongo("portait","yearbasestatics",yearType,count);

            }
            env.execute("year base analy");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
