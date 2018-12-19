package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    public static void main(String[] args) throws Exception {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(getDateTimeString(sdf.parse("2013-10-30 6:00:00")));
        //System.out.println(sdf.format(DateUtils.addHours(new Date(), 2)));
        System.out.println(getActivityEndDateTimeString("2014-04-30 6:00:00"));
    }

    private static final long m = 60 * 1000L;//分
    private static final long hour = 3600 * 1000L;//小时
    private static final long day = 24 * hour;//天
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_hh_mm = "yyyy-MM-dd HH:mm";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String MM_dd_hh_mm = "MM-dd HH:mm";

    /**
     * 日期时间转换成文字
     *
     * @param date
     * @return
     */
    public static String getDateTimeString(LocalDateTime date) {
        if (date == null) {
            throw new NullPointerException();
        }

        LocalDateTime currentDate = LocalDateTime.now();
        long cha = Math.abs(date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - currentDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//		System.out.println("cha="+cha);
        long hours = cha / hour;
//		System.out.println(hours);
        if (hours < 1) {
            if (cha / m <= 0) {
                return "刚刚";
            }
            return cha / m + "分钟前";
        }
        if (hours < 24) {
            return cha / hour + "小时前";
        }
        if (hours <= 72) {
            int nn = Integer.valueOf(cha / day + "");
            if (cha % day > 0) {
                nn++;
            }
            return nn + "天前";
        }
        return date.format(DateTimeFormatter.ofPattern(DateTimeUtil.yyyy_MM_dd));
    }

    public static boolean compareDateTime(Date d1, Date d2) {
        return d1.getTime() > d2.getTime();
    }

    /**
     * 获取促销商品活动的结束时间的字符串
     *
     * @param activityEndDateTime
     * @return
     * @throws Exception
     */
    public static String getActivityEndDateTimeString(String activityEndDateTime) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
        long nm = 1000 * 60;// 一分钟的毫秒数   
        long ns = 1000;// 一秒钟的毫秒数   
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异   
        try {
            diff = sdf.parse(activityEndDateTime).getTime() - (new Date()).getTime();
            day = diff / nd;// 计算差多少天   
            hour = diff % nd / nh + day * 24;// 计算差多少小时   
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟   
            sec = diff % nd % nh % nm / ns;// 计算差多少秒

            StringBuilder buff = new StringBuilder();

            if (day > 0) {
                buff.append(day).append("天");
            }

            if ((hour - day * 24) > 0) {
                buff.append(hour - day * 24).append("小时");
            }

            if ((min - day * 24 * 60) > 0) {
                buff.append(min - day * 24 * 60).append("分钟");
            }

            if (sec > 0) {
                buff.append(sec).append("秒");
            }

            //String cha = day + "天" + (hour - day * 24) + "小时"  + (min - day * 24 * 60) + "分钟" + sec + "秒";
            return buff.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() <= dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compare_day(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() <= dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int daysBetween1(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(sdf.format(new Date())));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getCurrentTime() {
        SimpleDateFormat ft = null;
        Date date = null;
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        date = cl.getTime();
        //格式可以自己根据需要修改
        ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = ft.format(date);
        return dateTime;
    }

    public static String getDateTimeString(Date date, String format) {
        SimpleDateFormat ft = null;
        //格式可以自己根据需要修改
        ft = new SimpleDateFormat(format);
        String dateTime = ft.format(date);
        return dateTime;
    }

    public static String getDateFormatStyle(int interval) {
        SimpleDateFormat ft = null;
        Date date = null;
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DATE, interval);
        date = cl.getTime();
        //格式可以自己根据需要修改
        ft = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = ft.format(date);
        return dateTime;
    }

    public static Timestamp getCurrentTimestamp() {
        SimpleDateFormat ft = null;
        Date date = null;
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        date = cl.getTime();
        //格式可以自己根据需要修改
        ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(ft.format(date));
            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    public static String ToConvertString(Timestamp timestamp) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(timestamp);
            System.out.println(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param format
     * @return
     */
    public static String dateToStr(Date dateDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 时间字符串格式化
     *
     * @param str    时间
     * @param format 时间格式
     * @return
     */
    public static String strToStr(String str, String format) {
        return dateToStr(strToDate(str), format);
    }

    /**
     * 时间格式化，用于社区
     *
     * @param time
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime time) {
        LocalDateTime currentDate = LocalDateTime.now();
        long cha = Math.abs(time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - currentDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long hours = cha / hour;
//		System.out.println(hours);
        if (hours < 1) {
            if (cha / m <= 0) {
                return "刚刚";
            }
            return cha / m + "分钟前";
        }
        if (hours < 24) {
            return cha / hour + "小时前";
        }
        if (time.getYear() == currentDate.getYear()) {
            return time.format(DateTimeFormatter.ofPattern(DateTimeUtil.MM_dd_hh_mm));
        }
        return time.format(DateTimeFormatter.ofPattern(DateTimeUtil.yyyy_MM_dd_hh_mm));
    }

    /**
     * 将时间戳转换为时间
     *
     * @param stamp
     * @return
     */
    public static LocalDateTime stampToDate(String stamp) {
        long lt = new Long(stamp);
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(lt, 0, ZoneOffset.ofHours(8));
        String localDateTimeStr = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(DateTimeUtil.yyyy_MM_dd_hh_mm_ss));
        return localDateTime;
    }

    /**
     * 将时间转换为时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long dateToStamp(LocalDateTime localDateTime) {
        long stamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return stamp / 1000;
    }


}
