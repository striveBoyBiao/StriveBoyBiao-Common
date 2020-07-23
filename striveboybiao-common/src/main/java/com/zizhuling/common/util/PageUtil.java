package com.zizhuling.common.util;

import com.zizhuling.common.dto.Page;
import com.zizhuling.common.dto.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  根据全量数据，内存中分页
 * </p>
 *
 * @author lishang Created on 2019/10/28 16:29
 * @version 1.0
 */
public class PageUtil {
    /**
     * 在内存中分页
     * @param currentPage
     * @param pageSize
     * @param datas
     * @return
     */
    public static Result page(int currentPage, int pageSize, List<?> datas){
        return Result.of().appendPage(pagination(currentPage, pageSize, datas));
    }

    /**
     * @description: 在内存中分页
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     * @param datas 数据
     * @return: cn.hsa.winning.common.component.Page
     * @author: wangaogao
     * @date: 2020/4/23 10:12
     */
    public static Page pagination(int currentPage, int pageSize, List<?> datas){
        // 1.计算请求的页数
        int total = 0;
        Page page = new Page();
        List<Object> listData = new ArrayList<>();
        if (datas!=null) {
            total=datas.size();
            int startRow = 0;
            int endRow = 0;

            if (total != 0) {
                startRow = (currentPage - 1) * pageSize + 1;
                endRow = currentPage * pageSize;
                if (endRow > total) {
                    endRow = total;
                }
                for (int i = startRow - 1; i < endRow; i++) {
                    listData.add(datas.get(i));
                }
            }
        }
        page.setResult(listData);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(Long.valueOf(total));

        return page;
    }


}
