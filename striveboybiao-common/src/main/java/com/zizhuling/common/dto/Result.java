package com.zizhuling.common.dto;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/18 17:59
 * @version 1.0
 */
public class Result extends JSONObject {
    public static final Result EMPTY = new Result.EmptyResult();

    private Result() {
    }

    public static Result of() {
        return new Result();
    }

    public Result append(KeyValuePair keyValuePairs) {
        if (null != keyValuePairs) {
            this.put(keyValuePairs.getKey(), keyValuePairs.getValue());
        }

        return this;
    }

    public Result append(String key, Object value) {
        if (StringUtils.isNotEmpty(key) && null != value) {
            this.put(key, value);
        }

        return this;
    }

    public Result appendSingleObject(Object value) {
        this.put("data", value);
        return this;
    }

    public <T> Result appendList(List<T> target) {
        this.put("list", target);
        return this;
    }

    public Result appendMsgDetail(String msgDetail) {
        this.put("msgDetail", msgDetail);
        return this;
    }

    public Result appendPage(Page page) {
        if (null != page) {
            this.put("pageSize", page.getPageSize());
            this.put("total", page.getTotal());
            this.put("currentPage", page.getCurrentPage());
            this.put("list", page.getResult());
        }

        return this;
    }

    public Result appendPage(com.github.pagehelper.Page page) {
        if (null != page) {
            this.put("pageSize", page.getPageSize());
            this.put("total", page.getTotal());
            this.put("currentPage", page.getPageNum());
            this.appendList(page.getResult());
        }

        return this;
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }

    private static class EmptyResult extends Result {
        private EmptyResult() {
            super();
        }
        @Override
        public Result append(KeyValuePair keyValuePairs) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Result append(String key, Object value) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Result appendSingleObject(Object value) {
            throw new UnsupportedOperationException();
        }
        @Override
        public <T> Result appendList(List<T> target) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Result appendMsgDetail(String msgDetail) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Result appendPage(Page page) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Result appendPage(com.github.pagehelper.Page page) {
            throw new UnsupportedOperationException();
        }
    }
}

