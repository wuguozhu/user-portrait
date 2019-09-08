package com.anan.entity;

/**
 * @author wuguozhu
 * @date 2019/9/8 19:53
 */
public class EmailInfo {


    //邮箱类型
    private String emailType;

    //数量
    private Long count;

    //分组字段
    private String groupField;

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}
