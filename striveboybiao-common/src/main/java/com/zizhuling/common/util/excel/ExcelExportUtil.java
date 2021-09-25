package com.zizhuling.common.util.excel;

import com.zizhuling.common.dto.ExcelExportDTO;
import com.zizhuling.common.util.pdf.PdfMulPageExportUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * <p>
 *  excel导出工具类, excel版本为2007以上
 * </p>
 *
 * @author hebiao Created on 2019/8/2714:27
 * @version 1.0
 */
public class ExcelExportUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtil.class);

    /**
     * @description excel的导出, 导出的格式是2007版本以上的
     *
     * @param response
     * @return void
     * @author Fang Kun
     * @date 2019/10/22 10:14
     */
    public static void exportExcelToXlsx(HttpServletResponse response, ExcelExportDTO excelExportDTO){
        //实例化HSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个Excel表单，参数为sheet的名字
        XSSFSheet sheet = workbook.createSheet(excelExportDTO.getSheetName());
        //设置表头
        setTitle(workbook, sheet, excelExportDTO.getTableHead());
        //设置单元格并赋值
        if(!CollectionUtils.isEmpty(excelExportDTO.getData())){
            setData(sheet, excelExportDTO.getData());
        }
        //设置浏览器下载
        setBrowser(response, workbook, excelExportDTO.getOutFileName());
    }

    /**
     * @description: 设置表头
     *
     * @author: Fang Kun
     * @param workbook
     * @param sheet
     * @param tableHead
     * @date: 2020/3/18 13:31
     * @return: void
     */
    private static void setTitle(XSSFWorkbook workbook, XSSFSheet sheet, List<String> tableHead) {
        try {
            XSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i < tableHead.size(); i++) {
                sheet.setColumnWidth(i, tableHead.get(i).getBytes("UTF-8").length*256);
            }
            //设置为居中加粗,格式化时间格式
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            //创建表头名称
            XSSFCell cell;
            CellStyle cellStyle = workbook.createCellStyle();
            XSSFDataFormat dataFormat = workbook.createDataFormat();
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            for (int j = 0; j < tableHead.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellType(STRING);
                cell.setCellValue(tableHead.get(j));
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            LOGGER.info(e.getMessage());
        }
    }

    /**
     * @description: 设置单元格数据
     *
     * @author: Fang Kun
     * @param sheet
     * @param data
     * @date: 2020/3/18 13:32
     * @return: void
     */
    private static void setData(XSSFSheet sheet, List<List<String>> data) {
        try{
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                XSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).size(); j++) {
                    row.createCell(j).setCellValue(data.get(i).get(j));
                }
                rowNum++;
            }
        }catch (Exception e){
            LOGGER.debug("表格赋值失败！"+e.getMessage());
            LOGGER.info("表格赋值失败！"+e.getMessage());
        }
    }

    /**
     * @description: 设置响应头
     *
     * @author: Fang Kun
     * @param response
     * @param workbook
     * @param fileName
     * @date: 2020/3/18 13:32
     * @return: void
     */
    private static void setBrowser(HttpServletResponse response, XSSFWorkbook workbook, String fileName) {
        try {
            PdfMulPageExportUtil.responseHandler(response, fileName);
            response.setContentType("application/vnd.ms -excel;charset=utf-8");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            LOGGER.debug("设置浏览器下载失败！"+e.getMessage());
            LOGGER.info("设置浏览器下载失败！"+e.getMessage());
        }
    }
}
