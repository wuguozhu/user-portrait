package com.anan.map;

import com.anan.entity.Buyinfo;
import com.anan.utils.OrderInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuguozhu
 * @date 2019/9/9 1:01
 */
public class BuyMap implements MapFunction<String, Buyinfo> {
    @Override
    public Buyinfo map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }

        OrderInfoUtils orderInfoUtils = new OrderInfoUtils();
        orderInfoUtils.orderInfo(s);

        Buyinfo buyinfo = new Buyinfo();
        buyinfo.setUserId(orderInfoUtils.getUserId());
        buyinfo.setCreateTime(orderInfoUtils.getCreateTime());
        buyinfo.setAmount(orderInfoUtils.getAmount());
        buyinfo.setPayType(orderInfoUtils.getPayType());
        buyinfo.setPayTime(orderInfoUtils.getPayTime());
        buyinfo.setPayStatus(orderInfoUtils.getPayStatus());
        buyinfo.setCouponAmount(orderInfoUtils.getCouponAmount());
        buyinfo.setTotalAmount(orderInfoUtils.getTotalAmount());
        buyinfo.setRefundAmount(orderInfoUtils.getRefundAmount());
        String groupField = "buy==" + orderInfoUtils.getUserId();
        buyinfo.setGroupField(groupField);
        List<Buyinfo> list = new ArrayList<Buyinfo>();
        list.add(buyinfo);
        return buyinfo;

    }
}
