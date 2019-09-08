package com.anan.task;

import com.anan.entity.Buyinfo;
import com.anan.entity.CarrierInfo;
import com.anan.map.BuyMap;
import com.anan.map.CarrierMap;
import com.anan.reduce.CarrierReduce;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

/**
 * <b><code>BuyTask</code></b>
 * <p>
 * description
 * </p>
 *
 * @author wuguozhu
 * @date 2019-09-09 00:34
 * @since user-portrait 0.1.0
 */


public class BuyTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<Buyinfo> resuleMap = text.map(new BuyMap());

       // DataSet<CarrierInfo> reduceResult = resuleMap.groupBy("groupfield").reduce();

    }
}
