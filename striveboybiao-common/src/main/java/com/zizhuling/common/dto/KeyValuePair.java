package com.zizhuling.common.dto;

import java.io.Serializable;

/**
 * <p>
 *     key,value值
 * </P>
 *
 * @author hebiao Create on 2020/7/18 18:01
 * @version 1.0
 */
public class KeyValuePair implements Serializable {
    private static final long serialVersionUID = 2437010671447729724L;
    private String key;
    private Object value;

    public KeyValuePair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
