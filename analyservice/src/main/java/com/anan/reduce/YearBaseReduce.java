package com.anan.reduce;

import com.anan.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author wuguozhu
 * @date 2019/9/8
 */

public class YearBaseReduce implements ReduceFunction<YearBase>  {
    @Override
    public YearBase reduce(YearBase yearBase, YearBase t1) throws Exception {

        String yeartype = yearBase.getYeartype();
        Long count1 = yearBase.getCount();

        Long count2 = t1.getCount();

        YearBase finalyearBase = new YearBase();
        finalyearBase.setYeartype(yeartype);
        finalyearBase.setCount(count1+count2);
        return finalyearBase;
    }
}
