/***
 * Copyright 2019 Icefrog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.baseframework.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间处理工具类
 */
public class DateUtil {

    /**
     * 时间单位
     */
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public static final String HOUR = "HOUR";
    public static final String MINUTE = "MINUTE";
    public static final String SECOND = "SECOND";

    /**
     * yyyyMMdd格式字符
     */
    public static final String DATA_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * HHmmss格式字符
     */
    public static final String DATA_FORMAT_HHMMSS = "HHmmss";

    /**
     * yyyyMMddHHmmss格式字符
     */
    public static final String DATA_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * yyyy-MM-dd格式字符
     */
    public static final String DATA_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyyMM格式字符
     */
    public final static String DATA_FORMAT_YYYY_MM = "yyyyMM";

    /**
     * yyyy年MM月dd日格式字符
     */
    public final static String CHINESE_DT_FORMAT = "yyyy年MM月dd日";

    /**
     * yyyy-MM-dd HH:mm:ss格式字符
     */
    public final static String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm格式字符
     */
    public final static String NO_SECOND_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * HH:mm:ss格式字符
     */
    public static final String DATA_FORMAT_HH_MM_SS = "HH:mm:ss";

    /**
     * 一天秒数
     */
    public final static long ONE_DAY_SECONDS = 86400;
    /**
     * 一天毫秒数
     */
    public final static long ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * 利用时间差来记录时间<br> 平台时间比本地时间快多少(可能为负),<br> 若没设置则span = 0,认为本地时间就是平台时间
     */
    private static final Integer SPAN = 0;
    /**
     * 23
     */
    private static final Integer TWENTY_THREE = 23;
    /**
     * 59
     */
    private static final Integer FIFTY_NINE = 59;

    /**
     * 取当前时间
     *
     * @return 当前时间
     */
    public static Date getDate() {
        Date nowAndroid = new Date();
        long nowPlant = nowAndroid.getTime() + SPAN;

        Date nowPlantDate = new Date(nowPlant);

        return nowPlantDate;
    }

    /**
     * 取当前时间
     *
     * @return Calendar
     */
    public Calendar getCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        return cal;
    }

    /**
     * 根据format串返回当前日期串
     *
     * @param formatStr formatStr
     * @return String
     */
    public static String getDateStringByFormatString(String formatStr) {
        Date date = getDate();
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 获取指定日期时间的增加偏移量后格式化的字符串
     *
     * @param value 分钟偏移量
     * @return 格式化的日期时间字符串
     */
    public String getSpecifyDateStringByMinuteOffset(int value) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MINUTE, value);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        return df.format(calendar.getTime());
    }

    /**
     * 把data转为制定格式的字符串
     *
     * @param date date
     * @param format format
     * @return data转为制定格式的字符串
     */
    public String convertDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null) {
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 根据long类型的时间转出format的时间
     *
     * @param timeLongVal timeLongVal
     * @param descFormat descFormat
     * @return String
     */
    public static String formatDateLongToString(Long timeLongVal, String descFormat) {
        Date date = new Date(timeLongVal);
        SimpleDateFormat df = new SimpleDateFormat(descFormat);
        return df.format(date);
    }

    /**
     * 从yyyy-MM-dd HH:mm:ss 格式的时间里面取出 ddHHmmss
     *
     * @param date date
     * @return ddHHmmss
     */
    public String convertFormatDataToString(String date) {
        String[] dateSplit = date.split(" ");
        String[] before = dateSplit[0].split("-");
        String[] after = dateSplit[1].split(":");

        StringBuilder builder = new StringBuilder();
        builder.append(before[2]).append(after[0]).append(after[1]).append(after[2]);
        return builder.toString();
    }

    /**
     * 时间字段转换
     *
     * @param srcDateStr 源时间字符
     * @param srcFormat 源时间格式
     * @param descFormat 目标时间格式
     */
    public static String convertStringDateToFormatString(String srcDateStr, String srcFormat,
        String descFormat) {
        if (srcDateStr == null || "".equals(srcDateStr)) {
            return "";
        } else {
            long longTime = formatDateStringToLong(srcDateStr, srcFormat);
            return formatDateLongToString(longTime, descFormat);
        }
    }

    /**
     * 将formatStr的时间 转成 long
     *
     * @param date date
     * @param formatStr formatStr
     * @return Long
     */
    public static Long formatDateStringToLong(String date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return formatter.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 计算时间间隔
     *
     * @param lastEndTime 上一次结束时间
     * @param currentBeginTime 这一次开始时间
     * @param dataFormatHHmmssStr 时间格式
     * @return Long
     */
    public static long getIntervalSecond(String lastEndTime, String currentBeginTime,
        String dataFormatHHmmssStr) {
        long dlast, dbegin, dInterval;
        SimpleDateFormat df = new SimpleDateFormat(dataFormatHHmmssStr);
        try {
            dlast = df.parse(lastEndTime).getTime();
            dbegin = df.parse(currentBeginTime).getTime();
            // 时间间隔
            dInterval = Math.abs((dbegin - dlast) / 1000);
        } catch (Exception e) {
            dInterval = 0;
            e.printStackTrace();
        }
        return dInterval;
    }

    /**
     * 根据秒数，计算时间
     *
     * @param time time
     * @return 时间
     */
    public String computSecond(long time) {
        // 分
        long minute = time % 3600 / 60;
        // 秒
        long second = time % 60;
        return String.valueOf((minute < 10 ? ("0" + minute) : minute) + ":"
            + (second < 10 ? ("0" + second) : second));
    }

    /**
     * 根据秒数，计算时间
     *
     * @param time time
     * @return 时间
     */
    public String computSecondToFullTime(long time) {
        long hour = time / 3600;
        long minute = time % 3600 / 60;
        long second = time % 60;
        return String.valueOf(
            (hour < 10 ? ("0" + hour) : hour) + ":" + (minute < 10 ? ("0" + minute) : minute) + ":"
                + (second < 10 ? ("0" + second) : second));
    }

    /**
     * 对日期进行计算
     *
     * @param date 日期
     * @param unit 计算单位
     * @param offset 偏移量
     */
    public static String calculateDate(String date, String unit, int offset) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date beginDt = null;
        try {
            beginDt = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.YEAR)) {
            type = Calendar.YEAR;
        } else if (unit.equals(DateUtil.MONTH)) {
            type = Calendar.MONTH;
        } else if (unit.equals(DateUtil.DAY)) {
            type = Calendar.DAY_OF_YEAR;
        } else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginDt);
        rightNow.add(type, offset);

        Date endDt = rightNow.getTime();
        String reStr = sdf.format(endDt);
        return reStr;
    }

    /**
     * 对时间进行计算
     *
     * @param time 时间 HHmmss
     * @param unit 单位
     * @param offset 偏移量
     * @return 计算后时间
     */
    public static String calculateTime(String time, String unit, int offset) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        Date beginTm = null;
        try {
            beginTm = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginTm);
        rightNow.add(type, offset);

        Date endTm = rightNow.getTime();
        String reStr = sdf.format(endTm);
        return reStr;
    }

    /**
     * 日期时间计算
     *
     * @param dateTime dateTime yyyyMMddHHmmss
     * @param unit 单位
     * @param offset 偏移量
     * @return 计算后时间
     */
    public static String calculateDateTime(String dateTime, String unit, int offset) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date beginTm = null;
        try {
            beginTm = sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginTm);
        rightNow.add(type, offset);

        Date endTm = rightNow.getTime();
        String reStr = sdf.format(endTm);
        return reStr;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 date1 yyyy-MM-dd
     * @param date2 date2 yyyy-MM-dd
     * @return 相差的天数
     * @throws ParseException ParseException
     */
    public static int calBetweenDays(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return calBetweenDays(date1, date2, sdf);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 date1 格式：SimpleDateFormat
     * @param date2 date2 格式：SimpleDateFormat
     * @param sdf 时间格式
     * @return 相差的天数
     * @throws ParseException ParseException
     */
    public static int calBetweenDays(String date1, String date2,
        SimpleDateFormat sdf) throws ParseException {

        Calendar cal = Calendar.getInstance();
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);

        cal.setTime(d1);
        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long t2 = cal.getTimeInMillis();
        long days = (t2 - t1) / (1000 * 3600 * 24);
        int a = (int) days;
        return a;
    }

    /**
     * 获取日期格式
     *
     * @param pattern pattern
     * @return DateFormat
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 获取当前日期格式：yyyy-MM-dd
     *
     * @return 时间
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATA_FORMAT_YYYY_MM_DD);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(dateString, pos);
    }

    /**
     * 根据传人格式，格式化传入时间
     *
     * @param date 时间
     * @param format 格式
     * @return 格式化后时间
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     *
     * @param sDate 时间
     * @return 格式化后时间
     * @throws ParseException ParseException
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_YYYYMMDD);

        if ((sDate == null) || (sDate.length() < DATA_FORMAT_YYYYMMDD.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     *
     * @param date 时间
     * @param format 格式化后时间
     * @return 格式化后时间
     * @throws ParseException ParseException
     */
    public static Date parseDateNoTime(Date date, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        return dateFormat.parse(dateString, pos);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     *
     * @param sDate 时间
     * @param format 格式化后时间
     * @return 格式化后时间
     * @throws ParseException ParseException
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     */
    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
        throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_YYYYMMDD);

        if ((sDate == null) || (sDate.length() != DATA_FORMAT_YYYYMMDD.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDatedataFormatyyyyMMddHHmmss(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_YYYYMMDDHHMMSS);
        Date d = null;

        if ((sDate != null) && (sDate.length() == DATA_FORMAT_YYYYMMDDHHMMSS.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * 格式化传入时间：yyyy-MM-dd HH:mm:ss"
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(NEW_FORMAT);
        Date d = null;
        if ((sDate != null) && (sDate.length() == NEW_FORMAT.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 计算当前时间N秒之后的时间
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= TWENTY_THREE)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= FIFTY_NINE)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days 天数
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 获取24小时后的时间
     */
    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 格式化传入时间：yyyyMMddHHmmss
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_YYYYMMDDHHMMSS);

        return getDateString(date, dateFormat);
    }

    /**
     * 格式化传入时间：yyyy-MM-dd HH:mm:ss
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(NEW_FORMAT);
        return getDateString(date, dateFormat);
    }

    /**
     * 获取当前时间字符串 传入格式
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * 获取24小时之前的时间
     */
    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYYMMDD);

        return df.format(date);
    }

    /**
     * 格式化时间：yyyy-MM-dd
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(DATA_FORMAT_YYYY_MM_DD);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(CHINESE_DT_FORMAT);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取当天时间 格式dataFormatyyyyMMdd：yyyyMMdd
     *
     * @return 格式化时间
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(DATA_FORMAT_YYYYMMDD);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 格式化时间：HHmmss
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(DATA_FORMAT_HHMMSS);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取n天之前的时间
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(DATA_FORMAT_YYYYMMDD);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * 取得两个日期间隔分钟数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取n天之前的时间 格式：yyyyMMdd
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYYMMDD);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
     * 验证是否yyyyMMdd格式
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != DATA_FORMAT_YYYYMMDD.length()) {
            return false;
        }
        try {
            // ---- 避免日期中输入非数字 ----
            Integer.parseInt(strDate);

        } catch (Exception e) {
            return false;
        }
        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYYMMDD);
        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否yyyyMMdd格式
     */
    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != DATA_FORMAT_YYYYMMDDHHMMSS.length()) {
            return false;
        }
        try {
            // ---- 避免日期中输入非数字 ----
            Long.parseLong(strDate);
        } catch (Exception e) {
            return false;
        }

        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYYMMDDHHMMSS);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYYMMDD);

        return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYY_MM_DD);

        return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYY_MM_DD);

        return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(DATA_FORMAT_YYYY_MM_DD);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * @param date1
     * @param date2
     * @param format
     *
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(DATA_FORMAT_YYYY_MM).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 判断参date上min分钟后，是否小于当前时间
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 判断是否早于当前时间
     *
     * @param date 要比较时间
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null) {
            return false;
        }
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 判断是否晚于当前时间
     *
     * @param date 要比较时间
     */
    public static boolean isAfterNow(Date date) {
        if (date == null) {
            return false;
        }
        return date.compareTo(new Date()) > 0;
    }

    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(NO_SECOND_FORMAT);

        if ((sDate == null) || (sDate.length() < NO_SECOND_FORMAT.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 计算时间差
     *
     * @param dBefor 首日
     * @param dAfter 尾日
     * @return 时间差(毫秒)
     */
    public static final long getDateBetween(Date dBefor, Date dAfter) {
        long lBefor = 0;
        long lAfter = 0;
        long lRtn = 0;

        /** 取得距离 1970年1月1日 00:00:00 GMT 的毫秒数 */
        lBefor = dBefor.getTime();
        lAfter = dAfter.getTime();

        lRtn = lAfter - lBefor;

        return lRtn;
    }

    /**
     * 加减天数
     *
     * @return Date
     */
    public static final Date increaseDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 获取传入时间当前小时
     *
     * @return int
     */
    public static int getDateHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取传入时间当前分钟
     *
     * @return int
     */
    public static int getDateMinutes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 日期格式化,针对日期的秒表示形式 by zengzhuo
     */
    public static String format(long second, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        Date d = new Date(second * 1000);
        return format(d, format);
    }

    public static String format(double second, String format) {
        long s = (long) second;
        return format(s, format);
    }

    /**
     * Date转为1970
     */
    public static long to1970(Date time) {
        return time.getTime() / 1000;
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     *
     * @param date 需要得到24时间点的日期
     * @return 该日期24点的日期串, 各时间点之间用“,”隔开
     */
    public static String get24TimesOfDay(Date date) {
        StringBuilder rv = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        for (int i = 0; i <= TWENTY_THREE; i++) {
            c.set(Calendar.HOUR_OF_DAY, i);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            String t = String.valueOf(to1970(c.getTime()));
            rv.append(t);
            if (i < TWENTY_THREE) {
                rv.append(",");
            }
        }

        return rv.toString();
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     *
     * @param second 需要得到24时间点的日期
     * @return 该日期24点的日期串, 各时间点之间用“,”隔开
     */
    public static String get24TimesOfDay(long second) {
        return get24TimesOfDay(new Date(second * 1000));
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     *
     * @return long[]
     */
    public static long[] get24TimesArrOfDay(long second) {
        String ts = get24TimesOfDay(second);
        String[] ta = ts.split(",");
        long[] l = new long[ta.length];
        for (int i = 0; i < ta.length; i++) {
            l[i] = Long.parseLong(ta[i]);
        }
        return l;
    }

    /**
     * 得到指定日期的零点零分零秒 返回表示秒数的long型数值
     */
    public static long getBeginOfDay(long second) {
        long[] l = DateUtil.get24TimesArrOfDay(second);
        return l[0];
    }


    /**
     * 得到指定日期的23点59分59秒
     */
    public static long getEndOfDay(long second) {
        long d = second * 1000;
        Date date = new Date(d);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis() / 1000;
    }
}
