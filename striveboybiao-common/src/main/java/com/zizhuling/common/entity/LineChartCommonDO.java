package com.zizhuling.common.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 折线图表公用类
 * </p>
 *
 * @author: wangaogao Created on 2020/4/1519:43
 * @version: 1.0
 */
public class LineChartCommonDO implements Serializable {
    private static final long serialVersionUID = -4305776089721290311L;

    /**
     * 图表名称
     */
    private String title;
    /**
     * y轴名称
     */
    private List<String> yAxisName;
    /**
     * 数值维度
     */
    private List<Long> pieces;
    /**
     * 展示维度
     */
    private List<String> legend;
    /**
     * x轴数据集合
     */
    private List<String> xAxis;
    /**
     * x轴、y轴对象集合
     */
    private List<List<PairDO>> series;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getyAxisName() {
        return yAxisName;
    }

    public void setyAxisName(List<String> yAxisName) {
        this.yAxisName = yAxisName;
    }

    public List<Long> getPieces() {
        return pieces;
    }

    public void setPieces(List<Long> pieces) {
        this.pieces = pieces;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<List<PairDO>> getSeries() {
        return series;
    }

    public void setSeries(List<List<PairDO>> series) {
        this.series = series;
    }

    public LineChartCommonDO(){}

    public LineChartCommonDO(String title, List<String> yAxisName, List<String> legend, List<String> xAxis
            , List<List<PairDO>> series) {
        this.title = title;
        this.yAxisName = yAxisName;
        this.legend = legend;
        this.xAxis = xAxis;
        this.series = series;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
