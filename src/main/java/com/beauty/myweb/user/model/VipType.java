package com.beauty.myweb.user.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author 
 */
@TableName("sys_vip_type")
public class VipType {
    /**
     * id
     */
    private Long id;

    /**
     * vip类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型描述
     */
    private String description;

    /**
     * 天数(若是永久类型没有值)
     */
    private Integer days;

    /**
     * 价格(元)
     */
    private Long price;

    /**
     * 购买地址
     */
    private String buyAddress;

    /**
     * 状态1-正常2-失效
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}