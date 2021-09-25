package com.zizhuling.common.util.pdf;

import com.zizhuling.common.constant.SystemConstants;
import com.zizhuling.common.dto.PdfMulPageDTO;
import com.zizhuling.common.util.SystemStringUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zizhuling.common.util.io.FileUtil;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <p>
 * pdf多页导出工具类
 * </p>
 *
 * @author hebiao Created on 2019/8/2714:27
 * @version 1.0
 */
public class PdfMulPageExportUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger( PdfMulPageExportUtil.class);
    /**
     * 默认样式
     */
    private final static String DEFAULTCSS="/default.css";
    /**
     * 字符集
     */
    private final static String UTF_8="UTF-8";
    /**
     * 响应头
     */
    public final static String CONTENTTYPE="APPLICATION/OCTET-STREAM";
    public final static String HEADER_CONTENT="Content-Disposition";
    public final static String ATTACHMENT="attachment; filename=";
    public final static String PDF_SUFFIX=".pdf";
    public final static String ZIP_SUFFIX=".zip";

    /**
     * 需要用到的字体名称
     */
    private static final String[] FONT_STR_ARR={"ping_fang_bold.ttf","ping_fang_light.ttf","ping_fang_regular.ttf","SIMLI.ttf","simsun.ttf"};

    /**
     * @description: 生成PDF到输出流中（ServletOutputStream用于下载PDF）
     *
     * @author: Fang Kun
     * @param pdfMulPageDTO 输入到FTL中的数据
     * @param response HttpServletResponse
     * @date: 2020/3/18 13:29
     * @return: void
     */
    public static void exportToResponse(PdfMulPageDTO pdfMulPageDTO, HttpServletResponse response){
        LOGGER.debug( SystemConstants.ENTER_FUNCTION_SINGLE_PARAM,pdfMulPageDTO);
        String html= getContent(pdfMulPageDTO.getDataHtml(), pdfMulPageDTO.getTemplate());
        try{
            String filePath = FileUtil.getFilePath(pdfMulPageDTO.getPath(), FONT_STR_ARR, pdfMulPageDTO.getItemName());
            pdfMulPageDTO.setPath(filePath);
            // 下载格式设置
            responseHandler(response, pdfMulPageDTO.getOutFileName()+PDF_SUFFIX);
            OutputStream out = response.getOutputStream();
            //设置文档大小
            Document document = new Document(PageSize.A4,50,50,50,50);

            PdfWriter writer = PdfWriter.getInstance(document, out);
            //设置页眉页脚
            PDFBuilder builder = new PDFBuilder(pdfMulPageDTO);
            writer.setPageEvent(builder);
            //输出为PDF文件
            convertToPdf(writer,document,html, pdfMulPageDTO);
        }catch (Exception ex){
        }
    }

    /**
     * @description: PDF文件生成
     *
     * @author: Fang Kun
     * @param writer
     * @param document
     * @param html
     * @param pdfMulPageDTO
     * @date: 2020/3/18 13:29
     * @return: void
     */
    public static void convertToPdf(@NotNull PdfWriter writer, @NotNull Document document, @NotNull String html, @NotNull PdfMulPageDTO pdfMulPageDTO){
        LOGGER.debug( SystemConstants.ENTER_FUNCTION_SINGLE_PARAM,pdfMulPageDTO);
        String fontPath=pdfMulPageDTO.getPath().substring(0, pdfMulPageDTO.getPath().length()-1);
        LOGGER.debug("fontPath--> "+fontPath);
        document.open();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(html.getBytes(Charset.forName(UTF_8)));
        InputStream resourceAsStream = XMLWorkerHelper.class.getResourceAsStream(DEFAULTCSS);
        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer,document,
                    byteArrayInputStream,
                    resourceAsStream,
                    Charset.forName(UTF_8), new XMLWorkerFontProvider(fontPath));
        } catch (Exception e) {
        } finally {
            FileUtil.closeStreamOfIn(byteArrayInputStream);
            FileUtil.closeStreamOfIn(resourceAsStream);
        }
        LOGGER.debug( SystemConstants.EXIST_FUNCTION_PARAM, "convertToPDF");
        document.close();
    }

    /**
     * @description: 获取模板
     *
     * @author: Fang Kun
     * @param dataHtml
     * @param template
     * @date: 2020/3/18 13:30
     * @return: java.lang.String
     */
    public static String getContent(Map<String, Object> dataHtml, Template template) {
        try (StringWriter writer =new StringWriter()){
            template.process(dataHtml, writer);
            writer.flush();
            String s = writer.toString();
            writer.close();
            return s;
        } catch (Exception ex) {
            //报错
            return "";
        }
    }

    /**
     * @description: 设置响应头
     *
     * @author: Fang Kun
     * @param response
     * @param fileName
     * @date: 2020/3/18 13:30
     * @return: void
     */
    public static void responseHandler(HttpServletResponse response, String fileName ) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String agent = request.getHeader("User-Agent");
        //设置response的Header
        if (agent != null && agent.toLowerCase().indexOf("firefox") != -1) {
            response.setHeader(HEADER_CONTENT, String.format("attachment;filename*=utf-8'zh_cn'%s", URLEncoder.encode(fileName, UTF_8)));
        } else {
            response.setHeader(HEADER_CONTENT, ATTACHMENT + URLEncoder.encode(fileName, UTF_8));
        }
        response.setContentType(CONTENTTYPE);
    }

    /**
     * @description: 特殊符号的汉化
     *
     * @author: Fang Kun
     * @param str
     * @date: 2020/3/18 13:30
     * @return: java.lang.String
     */
    public static String strHandler(String str){
        if(SystemStringUtil.isEmpty(str)){
            return str;
        }
        // 转义特殊字符
        // >= &ge;
        if(str.contains(">=")){
            str=str.replace(">=","大于等于");
        }
        // <= &le;
        if(str.contains("<=")){
            str=str.replace("<=","小于等于");
        }
        // < &lt;
        if(str.contains("<")){
            str=str.replace("<","小于");
        }
        // > &gt;
        if(str.contains(">")){
            str=str.replace(">","大于");
        }
        return str;
    }

}