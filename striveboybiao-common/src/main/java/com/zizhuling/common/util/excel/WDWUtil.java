package com.zizhuling.common.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * 工具类验证Excel文档
 */
public class WDWUtil {
    /**
     * @描述 是否是2003的excel，返回true是2003
     * @param filePath 文件路径
     * @return 2003版：true 否则：fasle
     */
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @描述 是否是2007的excel，返回true是2007
     * @param filePath 文件路径
     * @return 2003版：true 否则：fasle
     */
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证是否是EXCEL文件
     * @param filePath 文件路径
     * @return excel文件：true 否则：fasle
     */
    public static boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
            return false;
        }
        return true;
    }
}
