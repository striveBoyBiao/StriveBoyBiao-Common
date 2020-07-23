package com.zizhuling.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 * read  stacktrace  of exception
 *</p>
 *
 * @author JerrySi Create on 2020/4/16
 * @version 1.0
 */
public class ExceptionWriter {

    /**
     * print stacktrace of exception
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String s = sw.toString();
            sw.close();
            pw.close();
            return "\r\n" + s + "\r\n";
        } catch (IOException ex) {
            return "获得Exception信息的工具类异常";
        }
    }

}
