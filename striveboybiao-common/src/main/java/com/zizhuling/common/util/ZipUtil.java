package com.zizhuling.common.util;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <P>
 * 描述：zip文件工具类，压缩，解压
 * </p>
 *
 * @author hebiao Created on 2020/4/2101:57
 * @version 1.0
 */
public class ZipUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger( ZipUtil.class);

    /**
     * 创建ZipOutPutStream
     *
     * @param outputStream
     * @return
     */
    public static ZipOutputStream createZipOutputStream(OutputStream outputStream) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        return zipOutputStream;
    }

    /**
     * 将字符串写入到zip流
     *
     * @param content
     * @param storeFullFileName
     * @param zipOutputStream
     * @throws IOException
     */
    public static void doCompress(String content, String storeFullFileName, ZipOutputStream zipOutputStream) {
        if (content == null) {
            return;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(storeFullFileName));
            IOUtils.write(content, zipOutputStream, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            LOGGER.error("doCompress error:{}", e.getMessage());
        } finally {
            try {
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                LOGGER.error("doCompress closeEntry error :{}", e.getMessage());
            }
        }
    }

    /**
     * 将输出流写入到zip流
     *
     * @param inputStream
     * @param storeFullFileName
     * @param zipOutputStream
     * @throws IOException
     */
    public static void doCompress(InputStream inputStream, String storeFullFileName, ZipOutputStream zipOutputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(storeFullFileName));
            byte[] bytes = IOUtils.toByteArray(inputStream);
            zipOutputStream.write(bytes);
        } catch (IOException e) {
            LOGGER.error("doCompress error:{}", e.getMessage());
        } finally {
            try {
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                LOGGER.error("doCompress closeEntry error :{}", e.getMessage());
            }
        }
    }


    /**
     * 解压zip文件
     *
     * @param zipInputStream
     * @param zipRootName
     * @return 解压后的文件夹路径
     * @throws IOException
     */
    public static String doDeCompressZipFile(ZipInputStream zipInputStream, String zipRootName) {
        ZipEntry nextEntry;
        String tempPath = System.getProperty("java.io.tmpdir");
        try {
            while ((nextEntry = zipInputStream.getNextEntry()) != null && !nextEntry.isDirectory()) {
                // 如果entry不为空，并不在同一目录下， 获取文件目录
                String fileFullName = tempPath +zipRootName  + File.separator + nextEntry.getName();
                File file = FileUtil.newFile(fileFullName);
                // 如果该文件不存在
                if (!file.exists() || file.isDirectory() || file.getName().contains(".")) {
                    File fileParent = file.getParentFile();
                    if (!fileParent.exists()) {
                        boolean mkdirs = fileParent.mkdirs();
                        if(!mkdirs){
                            LOGGER.error("创建文件失败");
                            throw new IOException("创建文件失败");
                        }
                    }
                    // 创建文件
                    boolean newFile = file.createNewFile();
                    if (!newFile){
                        LOGGER.error("创建文件失败");
                    }
                }
                byte[] bytes = IOUtils.toByteArray(zipInputStream);
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    IOUtils.write(bytes, fileOutputStream);
                } catch (IOException e) {
                    LOGGER.error("输入流初始化异常：", e.getMessage());
                }
                // 关闭当前entry
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            LOGGER.error("doDeCompressZipFile. error :{}", e.getMessage());
        }
        StringBuilder sb = new StringBuilder(tempPath).append(zipRootName);
        return sb.toString();
    }

    /**
     * 限制文件在许可的目录内
     *
     * @param fileName
     * @param permitDirectory
     * @return
     * @throws IOException
     */
    private static void validateFileDir(String fileName, String permitDirectory) throws IOException {
        File checkFile = FileUtil.newFile(fileName);
        String canonicalFilePath = checkFile.getCanonicalPath();
        File permitDir = FileUtil.newFile(permitDirectory);
        String canonicalPermitDir = permitDir.getCanonicalPath();
        if (!canonicalFilePath.startsWith(canonicalPermitDir) || fileName.contains("../")) {
            throw new IllegalStateException("文件不在许可的目录内");
        }
    }

}
