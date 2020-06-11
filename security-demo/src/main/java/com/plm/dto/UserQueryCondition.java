package com.plm.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author : cwh
 * 2019/2/20 0020
 * description ：
 */
public class UserQueryCondition {

    private String userName;

    @ApiModelProperty(value = "用户年龄起始值")
    private Integer age;

    @ApiModelProperty(value = "用户年龄终止值")
    private Integer ageTo;

    private Date time;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}