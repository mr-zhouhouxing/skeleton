package io.pandora.mall.oss.util;

import io.pandora.mall.util.DateUtils;
import io.pandora.mall.oss.config.PandoraProperties;

import java.util.Date;

/**
 * <pre>
 *  文件名 工具类
 * </pre>
 * <small> 2018/7/17 19:02 | Aron</small>
 */
public class FileNameUtils {
    /**
     * 获取文件名
     * @param originalFileName
     * @param properties
     * @return
     */
    public static String getFileName(String originalFileName, PandoraProperties properties) {
        if (originalFileName == null) {
            originalFileName = "";
        } else {
            int unixSep = originalFileName.lastIndexOf("/");
            int winSep = originalFileName.lastIndexOf("\\");
            int pos = winSep > unixSep ? winSep : unixSep;
            originalFileName = pos != -1 ? originalFileName.substring(pos + 1) : originalFileName;
        }
        originalFileName = originalFileName.substring(0, originalFileName.indexOf(".")) + "-" + System.currentTimeMillis() + originalFileName.substring(originalFileName.indexOf("."));
        originalFileName = properties.getProjectName() + "/" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_8)
                + "/" + originalFileName;
        return originalFileName;
    }
}
