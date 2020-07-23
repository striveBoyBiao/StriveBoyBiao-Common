package com.zizhuling.common.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * * @description: 饼图DO
 * @author: wangaogao
 * @date: Created in 2019/10/7 18:43
 * @version: 1.0
 */
public class ChartCommonDO implements Serializable {
    private static final long serialVersionUID = 8349921429795590974L;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private List<String> legend;

    /**
     * 数据
     */
    private List<List<PairDO>> series = new ArrayList<>();

    public ChartCommonDO() {
    }

    /**
     * 一个图表的构造方法
     * @param title
     * @param legend
     * @param seriesValueList
     */
    public ChartCommonDO(String title, List<String> legend, List<PairDO> seriesValueList) {
        this.title = title;
        this.legend = legend;
        this.series=new ArrayList<>(1);
        this.series.add(seriesValueList);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<List<PairDO>> getSeries() {
        return series;
    }

    public void setSeries(List<List<PairDO>> series) {
        this.series = series;
    }

    /**
     * @description: 多图表元素添加
     * @param: [pairDOList] 单个图表元素
     * @return: void
     * @author: wangaogao
     * @date: 2019/10/12 19:33
     */
    public void addSeries(List<PairDO> pairDOList) {
        series.add(pairDOList);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
