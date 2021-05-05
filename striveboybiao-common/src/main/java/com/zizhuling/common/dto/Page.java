package com.zizhuling.common.dto;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     分页DTO
 * </P>
 *
 * @author hebiao Create on 2020/7/18 17:48
 * @version 1.0
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 5560076385888921157L;
    private Integer pageSize;
    private Long total;
    private Integer currentPage;
    private List<T> result;

    public Page() {
    }

    public Page(PageInfo pageInfo) {
        Objects.requireNonNull(pageInfo, "pageInfo must be not null");
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.currentPage = pageInfo.getPageNum();
        this.result = pageInfo.getList();
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

