package com.zizhuling.common.util;

import com.zizhuling.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.MethodInvoker;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SqlUtils {

    private SqlUtils() {

    }

    private static final int BATCH_INSERT_PER_PAGE_SIZE = 1000;

    /**
     * @deprecated  // insertBatch(engineMapper, "insertInvoiceBatch", invoiceDtoList, encounterId)
     * @param mapperBean mapper
     * @param sqlMethod  sql method name
     * @param paramList  list parameter
     * @param params     other parameters
     */
    public static void insertBatch(Object mapperBean, String sqlMethod, List<?> paramList, Object... params) {
        if (CollectionUtils.isEmpty(paramList)) {
            return;
        }
        int pages = (paramList.size() - 1) / BATCH_INSERT_PER_PAGE_SIZE + 1;
        for (int i = 0; i < pages; i++) {
            int fromIndex = i * BATCH_INSERT_PER_PAGE_SIZE;
            int toIndex = fromIndex + BATCH_INSERT_PER_PAGE_SIZE;
            List<?> subList = new ArrayList<>(paramList.subList(fromIndex, paramList.size() > toIndex ? toIndex : paramList.size()));
            Object[] arguments;
            if (params != null) {
                arguments = new Object[params.length + 1];
                arguments[0] = subList;
                System.arraycopy(params, 0, arguments, 1, params.length);
            } else {
                arguments = new Object[1];
                arguments[0] = subList;
            }
            invokeMethod(mapperBean, sqlMethod, arguments);
        }
    }

    private static void invokeMethod(Object mapperBean, String sqlMethod, Object... arguments) {
        MethodInvoker beanMethod = new MethodInvoker();
        beanMethod.setTargetObject(mapperBean);
        beanMethod.setTargetMethod(sqlMethod);
        beanMethod.setArguments(arguments);
        try {
            beanMethod.prepare();
            beanMethod.invoke();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new BusinessException("执行sql出错");
        }
    }

}
