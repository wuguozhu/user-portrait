package com.anan.entity;


/**
 * @author wuguozhu
 * @date 2019/9/7
 */

public class YearBase {


    //年代类型
    private String yeartype;

    //用户群数量
    private Long count;

    private String groupfield;


    public String getYeartype() {
        return yeartype;
    }

    public void setYeartype(String yeartype) {
        this.yeartype = yeartype;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }
}
