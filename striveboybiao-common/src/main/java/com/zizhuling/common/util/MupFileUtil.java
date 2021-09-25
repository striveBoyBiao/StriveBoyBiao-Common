package com.zizhuling.common.util;

import com.zizhuling.common.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 *     文件上传下载工具类
 * </P>
 *
 * @author hebiao Create on 2020/7/11 18:42
 * @version 1.0
 */
@Configuration
public class MupFileUtil {

    private static String basePath;

    /**
     * @param directory   文件夹路径
     * @param fileName    文件名
     * @param inputStream 输入流
     */
    public static void upload(String directory, String fileName, InputStream inputStream) {
        upload(directory + File.separator + fileName, inputStream);
    }

    /**
     * @param fullPath    文件夹+文件名
     * @param inputStream 输入流
     */
    public static void upload(String fullPath, InputStream inputStream) {
        try {
            FileUtils.writeByteArrayToFile(new File(basePath + File.separator + fullPath)
                    , IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            throw new BusinessException("文件上传失败");
        }
    }

    public static void download(String directory, String fileName, OutputStream outputStream) {
        download(directory + File.separator + fileName, outputStream);
    }

    public static void download(String fullPath, OutputStream outputStream) {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(basePath + File.separator + fullPath));
            IOUtils.write(bytes, outputStream);
        } catch (IOException e) {
            throw new BusinessException("文件下载失败");
        }
    }

    public static byte[] download(String fullPath) {
        try {
            return FileUtils.readFileToByteArray(new File(basePath + File.separator + fullPath));
        } catch (IOException e) {
            throw new BusinessException("文件下载失败");
        }
    }

    public static byte[] download(String directory, String fileName) {
        return download(directory + File.separator + fileName);
    }

}
