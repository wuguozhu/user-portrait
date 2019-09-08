package com.anan.utils;

/**
 * <b><code>orderInfoUtils</code></b>
 * <p>
 * description
 * </p>
 *
 * @author wuguozhu
 * @date 2019-09-09 00:38
 * @since user-portrait 0.1.0
 */


public class OrderInfoUtils {
    private String id;
    private String productId;
    private String productTypeId;
    private String createTime;
    private String amount;
    private String payType;
    private String payTime;
    private String payStatus;
    private String couponAmount;
    private String totalAmount;
    private String refundAmount;
    private String num;
    private String userId;


    /**
     *
     * @param s 数据
     */
    public void orderInfo(String s) {
        String[] orderInfos = s.split(",");
        String id = orderInfos[0];
        String productId = orderInfos[1];
        String productTyped = orderInfos[2];
        String createTime = orderInfos[3];
        String amount = orderInfos[4];
        String payType = orderInfos[5];
        String payTime = orderInfos[6];
        String payStatus = orderInfos[7];
        String couponAmount = orderInfos[8];
        String totalAmount = orderInfos[9];
        String refundAmount = orderInfos[10];
        String num = orderInfos[11];
        String userId = orderInfos[12];
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getAmount() {
        return amount;
    }

    public String getPayType() {
        return payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public String getNum() {
        return num;
    }

    public String getUserId() {
        return userId;
    }
}
