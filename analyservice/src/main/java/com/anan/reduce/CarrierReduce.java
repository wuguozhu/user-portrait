package com.anan.reduce;

import com.anan.entity.CarrierInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author wuguozhu
 * @date 2019/9/8 17:40
 */
public class CarrierReduce implements ReduceFunction<CarrierInfo> {
    @Override
    public CarrierInfo reduce(CarrierInfo carrierInfo, CarrierInfo t1) throws Exception {
        String carrier = carrierInfo.getCarrier();
        Long count1 = carrierInfo.getCount();
        Long count2 = t1.getCount();

        CarrierInfo finalCarrierInfo = new CarrierInfo();
        finalCarrierInfo.setCarrier(carrier);
        finalCarrierInfo.setCount(count1+count2);

        return finalCarrierInfo;
    }
}
