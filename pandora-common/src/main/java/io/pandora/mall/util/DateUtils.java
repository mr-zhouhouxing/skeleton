package io.pandora.mall.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Created by mr_zhou on 2020/12/15
 */
public class DateUtils {

    public static String YYYY = "yyyy";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_PATTERN_8 = "yyyyMMdd";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
    };

    /**
     * 示例：format(new Date(),YYYY_MM_DD_HH_MM_SS)
     * @param date      时间
     * @param pattern   格式
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * TODO: 当前时间小于过期时间
     * @param nowDate       当前时间
     * @param expireDate    过期时间
     * @return true
     */
    public static boolean belongCalendar(Date nowDate,Date expireDate){
        Calendar expire = Calendar.getInstance();
        expire.setTime(expireDate);
        Calendar now = Calendar.getInstance();
        now.setTime(nowDate);
        return now.before(expire);
    }

    /**
     * 返回前preDay天的日期
     *
     * @param preDay
     * @return yyyy-MM-dd
     */
    public static Date getDateStr4Pre(int preDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - preDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date t = c.getTime();
        return t;
    }

}
