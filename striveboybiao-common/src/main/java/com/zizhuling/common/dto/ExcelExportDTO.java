package com.zizhuling.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Fang Kun Created on 2019/10/2210:19
 * @version 1.0
 */
public class ExcelExportDTO implements Serializable {
    private static final long serialVersionUID = 6125840735425241203L;

    /**
     * 表头
     */
    private List<String> tableHead;

    /**
     * sheet页名称
     */
    private String sheetName;

    /**
     * 导出文件名称
     */
    private String outFileName;

    /**
     * 导出数据
     */
    private List<List<String>> data;

    public List<String> getTableHead() {
        return tableHead;
    }

    public void setTableHead(List<String> tableHead) {
        this.tableHead = tableHead;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
