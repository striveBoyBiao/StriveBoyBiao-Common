package com.zizhuling.common.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * * @description: 键值对DO
 * @author: wangaogao
 * @date: Created in 2019/10/7 19:44
 * @version: 1.0
 */
public class PairDO implements Serializable {
    private static final long serialVersionUID = 1765144674034459857L;

    /**
     * 键值
     */
    private String name;
    /**
     * 数量
     */
    private BigDecimal value;
    /**
     * 下钻数据
     */
    private List<Item> children;

    public PairDO() {
    }

    public PairDO(String name) {
        this.name = name;
    }

    public PairDO(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
