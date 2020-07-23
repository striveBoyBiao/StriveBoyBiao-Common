package com.zizhuling.common.entity;

import java.io.Serializable;

/**
 * <p>
 * 描述：通用键值实体
 * </p>
 *
 * @author lishang Created on 2020/3/718:13
 * @version 1.0
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1638205593634210315L;

    /**
     * 名称
     */
    private String name;
    /**
     *  值
     */
    private String value;

    public Item() {
    }

    public Item(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
