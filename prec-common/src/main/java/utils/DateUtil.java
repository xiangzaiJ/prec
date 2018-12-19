package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.yyyyMMddHHmmss);

    public static final SimpleDateFormat dateFormat2 = new SimpleDateFormat(DateUtil.yyyy_MM_dd_hh_mm_ss);

    public static final SimpleDateFormat dateFormat3 = new SimpleDateFormat(DateUtil.yyyy_MM_dd);

    private static SimpleDateFormat dateFormat4;

    /**
     * 按照指定格式返回 SimpleDateFormat 对象
     *
     * @param format 指定返回 SimpleDateFormat 对象的格式
     * @return 指定格式的 SimpleDateFormat 对象
     */
    public static SimpleDateFormat getDateFormat(String format) {

        // 如果参数指定的格式属于默认格式的一种，直接返回对应的 SimpleDateFormat 对象
        if (DateUtil.yyyyMMddHHmmss.equals(format)) {
            return dateFormat;
        } else if (DateUtil.yyyy_MM_dd_hh_mm_ss.equals(format)) {
            return dateFormat2;
        } else if (DateUtil.yyyy_MM_dd.equals(format)) {
            return dateFormat3;
        } else {

            // 不属于默认格式，此时先判断是否与现有对象格式相同，不相同才创建新对象
            if (dateFormat4 == null || !format.equals(dateFormat4.toPattern())) {
                dateFormat4 = new SimpleDateFormat(format);
            }
            return dateFormat4;
        }
    }

    /**
     * 将数据库抓取的时间long 转成String
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String longToString(long time, String format) throws ParseException {
        Date date = new Date(time);
        String dateStr = DateUtil.date2String(date, format);
        return dateStr;
    }

    public static Date string2date(String time, String format) throws ParseException {
        Date date = null;
        SimpleDateFormat formatter = getDateFormat(format);
        date = formatter.parse(time);
        return date;
    }

    public static String date2String(Date date, String format) {
        SimpleDateFormat formatter = getDateFormat(format);
        return formatter.format(date);
    }

    public static long string2long(String time, String format) throws ParseException {
        Date date = string2date(time, format);
        return date.getTime();
    }

    /**
     * 取得 Date 类型表示的今天的开始时间
     *
     * @return 当天开始时间
     */
    public static Date getDayFirsttime() {
        return getDayFirsttime(new Date());
    }

    /**
     * 取得参数 date 所在那天的开始时间
     *
     * @param date 需要取得开始时间的日期
     * @return date 所在那天的开始时间
     */
    public static Date getDayFirsttime(Date date) {
        if (null == date) {
            return date;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得今天开始时间，并按照指定格式转换成 String 类型
     *
     * @param format 转换的格式
     * @return 转换后的今天开始时间
     */
    public static String getDayFirsttime(String format) {
        return getDayFirsttime(new Date(), format);
    }

    /**
     * 取得 date 所在日期的开始时间，并按照指定格式转换成 String 类型
     *
     * @param date   需要取得开始时间的日期
     * @param format 转换的格式
     * @return date 所在那天的开始时间
     */
    public static String getDayFirsttime(Date date, String format) {
        date = getDayFirsttime(date);
        return date2String(date, format);
    }

    /**
     * 取得 Date 类型表示的这个月的开始时间
     *
     * @return 当月开始时间
     */
    public static Date getMonthFirsttime() {
        return getMonthFirsttime(new Date());
    }

    /**
     * 取得参数 date 所在那个月的开始时间
     *
     * @param date 需要取得开始时间的日期
     * @return date 所在那个月的开始时间
     */
    public static Date getMonthFirsttime(Date date) {
        if (null == date) {
            return date;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得这个月的开始时间，并按照指定格式转换成 String 类型
     *
     * @param format 转换的格式
     * @return 转换后的这个月开始时间
     */
    public static String getMonthFirsttime(String format) {
        return getMonthFirsttime(new Date(), format);
    }

    /**
     * 取得 date 所在月份的开始时间，并按照指定格式转换成 String 类型
     *
     * @param date   需要取得开始时间的日期
     * @param format 转换的格式
     * @return date 所在那个月的开始时间
     */
    public static String getMonthFirsttime(Date date, String format) {
        date = getMonthFirsttime(date);
        return date2String(date, format);
    }

    /**
     * 取得 Date 类型表示的今天的最晚时间
     *
     * @return 当天最晚时间
     */
    public static Date getLasttimeDay() {
        // String result = "";
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 取得参数 date 所在那天的开始时间
     *
     * @param date 需要取得开始时间的日期
     * @return date 所在那天的开始时间
     */
    public static Date getDayLasttime(Date date) {
        if (null == date) {
            return date;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 取得今天的最晚时间，并按照指定格式转换成 String 类型
     *
     * @param format 转换的格式
     * @return 转换后的今天最晚时间
     */
    public static String getDayLasttime(String format) {
        return getDayLasttime(new Date(), format);
    }

    /**
     * 取得 date 所在日期的最晚时间，并按照指定格式转换成 String 类型
     *
     * @param date   需要取得最晚时间的日期
     * @param format 转换的格式
     * @return date 所在那天的最晚时间
     */
    public static String getDayLasttime(Date date, String format) {
        date = getDayLasttime(date);
        return date2String(date, format);
    }

    /**
     * 取得 Date 类型表示的这个月的最晚时间
     *
     * @return 当月最晚时间
     */
    public static Date getMonthLasttime() {
        return getMonthLasttime(new Date());
    }

    /**
     * 取得参数 date 所在那个月的最晚时间
     *
     * @param date 需要取得最晚时间的日期
     * @return date 所在那个月的最晚时间
     */
    public static Date getMonthLasttime(Date date) {
        if (null == date) {
            return date;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMonthFirsttime(date));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 取得这个月的最晚时间，并按照指定格式转换成 String 类型
     *
     * @param format 转换的格式
     * @return 转换后的这个月最晚时间
     */
    public static String getMonthLasttime(String format) {
        return getMonthLasttime(new Date(), format);
    }

    /**
     * 取得 date 所在月份的最晚时间，并按照指定格式转换成 String 类型
     *
     * @param date   需要取得最晚时间的日期
     * @param format 转换的格式
     * @return date 所在那个月的最晚时间
     */
    public static String getMonthLasttime(Date date, String format) {
        date = getMonthLasttime(date);
        return date2String(date, format);
    }

    public static long getTodayAsLong(String format) throws ParseException {
        Date date = new Date();
        long dateLong = date.getTime();
        String dateStr = longToString(dateLong, format);
        date = string2date(dateStr, format);
        return date.getTime();
    }

    /**
     * 返回前几天的时间字符串
     *
     * @param otherDay 天数
     * @return
     * @throws ParseException
     * @throws ParseException
     */
    public static String getTheOtherDay(int otherDay) throws ParseException {
        long subTime = 1000 * 60 * 60 * 24 * otherDay;

        long todayLong = getTodayAsLong(DateUtil.yyyy_MM_dd_hh_mm_ss);

        long result = todayLong - subTime;
        Date resultDate = new Date(result);
        String resultStr = date2String(resultDate, DateUtil.yyyy_MM_dd_hh_mm_ss);
        return resultStr;

    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */

    public static int daysBetween(Date date1, Date date2)

    {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date1);

        long time1 = cal.getTimeInMillis();

        cal.setTime(date2);

        long time2 = cal.getTimeInMillis();

        long between_days = 0;

        if ((time2 - time1) % (1000 * 3600 * 24) != 0) {
            between_days = (time2 - time1) / (1000 * 3600 * 24) + 1;
        } else {
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        }

        return Integer.parseInt(String.valueOf(between_days));

    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * data1在data2之后
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     * @throws ParseException
     */
    public static boolean after(String date1, String date2, String format) throws ParseException {
        long date1Long = string2long(date1, format);
        long date2Long = string2long(date2, format);
        return after(date1Long, date2Long);
    }

    /**
     * data1在data2之后
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean after(Date date1, Date date2) {
        long date1Long = date1.getTime();
        long date2Long = date2.getTime();
        return after(date1Long, date2Long);
    }

    /**
     * data1在data2之后
     *
     * @param data1
     * @param date2
     * @param format
     * @return
     * @throws ParseException
     */
    public static boolean after(Date data1, String date2, String format) throws ParseException {
        long date1Long = data1.getTime();
        long date2Long = string2long(date2, format);
        return after(date1Long, date2Long);
    }

    /**
     * data1在data2之后
     *
     * @param data1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static boolean after(Date data1, long date2) throws ParseException {
        long date1Long = data1.getTime();
        return after(date1Long, date2);
    }

    /**
     * data1在data2之后
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     * @throws ParseException
     */
    public static boolean after(long date1, String date2, String format) throws ParseException {
        long date2Long = string2long(date2, format);
        return after(date1, date2Long);
    }

    /**
     * 比较两个日期.如果data1>data2返回true; data1在data2之后
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean after(long data1, long data2) {
        if (data1 - data2 > 0) return true;
        else return false;
    }

    public static String getCurrent() {

        return date2String(new Date(), DateUtil.yyyy_MM_dd_hh_mm_ss);
    }

    /*
     * public static double getUseHour(long date1, long date2) { Double temp = new Double(date2 - date1).doubleValue();
     * temp = NumUtil.div(temp, 3600000, 2); return temp; } public static double getUseHour(Date date1, Date date2)
     * throws ParseException { long date1Long = date1.getTime(); long date2Long = date2.getTime(); return
     * getUseHour(date1Long, date2Long); } public static double getUseHour(String date1, String date2, String format)
     * throws ParseException { long date1Long = string2long(date1, format); long date2Long = string2long(date2, format);
     * return getUseHour(date1Long, date2Long); }
     */

    public static void main(String[] args) throws ParseException {
    }

    public static String getFormat(LocalDateTime time) {
        String hour = String.valueOf(time.getHour());
        String minute = String.valueOf(time.getMinute());
        if (hour.length() == 1)
            hour = "0" + hour;
        if (minute.length() == 1)
            minute = "0" + minute;
        return time.toLocalDate() + " " + hour + ":" + minute;
    }


    public static String getNowFormat(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static String getMonthAndDay(String time) {
        String[] list = time.split("-");
        if (list[1].length() == 1) {
            list[1] = "0" + list[1];
        }
        if (list[2].length() == 1) {
            list[2] = "0" + list[2];
        }
        return list[1] + "-" + list[2];
    }

    public static String secondToTime(Integer seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }

    public static String secondToTime2(Integer seconds) {
        String timeStr = seconds.toString();
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            String secongStr = second + "";
            String minStr = min + "";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                if (second < 10) {
                    secongStr = "0" + secongStr;
                }
                if (min < 10) {
                    minStr = "0" + minStr;
                }
                timeStr = hour + ":" + secongStr + ":" + minStr;
            } else {
                if (second < 10) {
                    secongStr = "0" + secongStr;
                }
                if (min < 10) {
                    minStr = "0" + minStr;
                }
                timeStr = secongStr + ":" + minStr;
            }
        } else {
            if (seconds < 10) {
                timeStr = "0" + timeStr;
            }
            timeStr = "00:" + timeStr;
        }
        return timeStr;
    }
}
