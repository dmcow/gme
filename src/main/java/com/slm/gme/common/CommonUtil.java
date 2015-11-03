
package com.slm.gme.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 公共的工具类
 * @author  zhangziwen
 * @version  [版本号, Jun 1, 2012]
 * @see  [相关类/方法]
 * @date 2015年8月28日 下午3:56:05
 */
public class CommonUtil
{
    private static Random random = new Random();
    
    public static boolean isNull(Object param) {
        return null == param;
    }

    public static boolean isNotNull(Object param) {
        return null != param;
    }
    
    public static boolean isNotEmpty(Object[] params) {
        return isNotNull(params) && params.length > 0;
    }

    /** 
     * 得到几年前的时间 
     * @param d 日期
     * @param month 年
     * @return 几年前的时间
     */
    public static Date getDateYearBefore(Date d , int year)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) - year);
        return now.getTime();
    }

    /** 
     * 得到几年前的时间 
     * @param d 日期
     * @param month 年
     * @return 几年后的时间
     */
    public static Date getDateYearAfter(Date d , int year)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + year);
        return now.getTime();
    }

    /** 
     * 得到几月前的时间 
     * @param d 日期
     * @param month 几月
     * @return 几天月的时间
     */
    public static Date getDateMonthBefore(Date d , int month)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) - month);
        return now.getTime();
    }

    /** 
     * 得到几月后的时间 
     * @param d 日期
     * @param month 几月
     * @return 几月后的时间
     */
    public static Date getDateMonthAfter(Date d , int month)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
        return now.getTime();
    }

    /** 
     * 得到几天前的时间 
     * @param d 日期
     * @param day 几天
     * @return 几天前的时间
     */
    public static Date getDateBefore(Date d , int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /** 
     * 得到几天后的时间 
     * @param d 日期
     * @param day 几天
     * @return 几天后的日期
     */
    public static Date getDateAfter(Date d , int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 将日期从字符串类型转换为Date类型，按照默认格式（yyyyMMddHHmmss）进行转换
     * @param dateStr 字符串格式的日期
     * @return Date类型的日期
     */
    public static Date parseDate(String dateStr)
    {
        return parseDate(dateStr, CommonConst.GmeDate.DATE_TIME_FORMAT);
    }

    /**
     * 将日期从字符串类型转换为Date类型，使用自定义的转换格式
     * @param dateStr 字符串格式的日期
     * @param format 日期转换格式
     * @return Date 类型的日期
     */
    public static Date parseDate(String dateStr , String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try
        {
            return null == dateStr ? null : dateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 将日期从Date类型转换为字符串类型，按照默认格式（yyyyMMddHHmmss）进行转换
     * @param date Date格式的日期
     * @return String 字符串类型的日期
     */
    public static String formatDate(Date date)
    {
        return formatDate(date, CommonConst.GmeDate.DATE_TIME_FORMAT);
    }

    /**
     * 将日期从Date类型转换为字符串类型，按照页面展示格式（yyyy-MM-dd HH:mm:ss）进行转换
     * @param date Date格式的日期
     * @return String 字符串类型的日期
     */
    public static String formatDateToDisplay(Date date)
    {
        return formatDate(date, CommonConst.GmeDate.DISPLAYED_DATE_TIME_FORMAT);
    }

    /**
     * 将日期从Date类型转换为字符串类型，使用自定义的转换格式
     * @param date Date格式的日期
     * @param format 日期转换格式
     * @return String 字符串类型的日期
     */
    public static String formatDate(Date date , String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return null == date ? null : dateFormat.format(date);
    }

    /**
     * <p>
     * 转换日期的格式，将原有格式的日期转换为新的格式
     * </p>
     * 
     * @param dateStr 字符串类型的日期
     * @param oldFormat 原有格式
     * @param newFormat 新格式
     * @return String 转换后的日期（String类型）
     */
    public static String transDateFormat(String dateStr , String oldFormat , String newFormat)
    {
        Date date = parseDate(dateStr, oldFormat);
        return formatDate(date, newFormat);
    }

    /**
     * 获取当前时间
     * 
     * @return 返回当前时间 格式：yyyyMMddHHmmss
     * @see [类、类#方法、类#成员]
     */
    public static String getTodayDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 获取日期
     * @param date
     * @return
     */
    public static String getDate(Date date)
    {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取日期
     * @param date
     * @return
     */
    public static String getDateDisplay(Date date)
    {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取时间
     * @param date
     * @return
     */
    public static String getTime(Date date)
    {
        return formatDate(date, "HHmmss");
    }

    /**
     * 获取时间
     * @param date
     * @return
     */
    public static String getTimeDisplay(Date date)
    {
        return formatDate(date, "HH:mm:ss");
    }

    /**
     * [简要描述]把数组转换字符串 如: str1,str2 如果 strs 为空,返回 null
     * @param strs String[]
     * @return String (or NULL)
     */
    public static String transArrayToStringFormat(String... strs)
    {
        if (ArrayUtils.isEmpty(strs))
        {
            return null;
        }

        StringBuffer strBuff = new StringBuffer();
        for (String str : strs)
        {
            strBuff.append(str);
            strBuff.append(',');
        }

        return strBuff.substring(0, strBuff.length() - 1);

    }

    /**
     * [简要描述]判断两个字符串数组是否完全相同(不验证数组中是否有重复的数据)
     * @param mainString String[
     * @param subString String[
     * @return boolean
     */
    public static boolean isSameArray(String[] mainString , String[] subString)
    {
        // 都为空时，返回true
        if (null == mainString || null == subString)
        {
            return false;
        }

        if (mainString.length != subString.length)
        {
            return false;
        }

        for (int i = 0; i < subString.length; i++)
        {
            if (!ArrayUtils.contains(mainString, subString[i]))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * [简要描述]判断第一个字符串数组是否完全包含第二个字符串(不验证数组中是否有重复的数据)
     * @param mainString String[]
     * @param subString String[]
     * @return boolean
     */
    public static boolean contains(String[] mainString , String[] subString)
    {
        if (ArrayUtils.isEmpty(subString))
        {
            return true;
        }

        for (final String str : subString)
        {
            if (!ArrayUtils.contains(mainString, str))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * [简要描述] 检查字符串是否为NULL或者空字符串
     * @param str str
     * @return boolean
     */
    public static boolean isNullOrBlank(String str)
    {
        return null == str || "".equals(str.trim());
    }

    /**
     * [简要描述] 字符串数组转换为List
     * @param array String[]
     * @return List<String>
     */
    public static List<String> arrayToList(String[] array)
    {
        if (ArrayUtils.isEmpty(array))
        {
            return new ArrayList<String>();
        }

        List<String> list = new ArrayList<String>();
        for (String str : array)
        {
            list.add(str);
        }

        return list;
    }

    /**
     * [简要描述]把array转换成List
     * @param array Object[]
     * @return List< ? >
     */
    public static List<?> transArrayToListFormat(Object[] array)
    {
        List<Object> list = new ArrayList<Object>();
        if (!ArrayUtils.isEmpty(array))
        {
            for (Object obj : array)
            {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * [简要描述]把list转换成 "aaa"+split+"bbb"
     * @param list List
     * @param split String
     * @return String
     */
    public static String tranListToStringFormat(List<String> list , String split)
    {
        Collections.reverse(list);

        if (null == list || list.size() <= 0)
        {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (String str : list)
        {
            sb.append(str + split);
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * [简要描述]:根据string对象获得string数组
     * @param s String
     * @param split String
     * @return String[] string数组
     * @exception
     */
    public static String[] getStringArray(String s , String split)
    {
        return CommonUtil.isNullOrBlank(s) ? null : s.split(split);
    }

    /**
     * [简要描述]:获得随机数<br/>
     * @param seed 随机数种子，位数不能大于length，当然，大于的话，这边也没问题
     * @param length 生成的随机字符串位数
     * @return 长度为length的随机数字字符串
     */
    public static String getRandom(int seed , int length)
    {
        if (length == 0)
        {
            return "";
        }
        char[] ranChars = String.valueOf(random.nextInt(seed)).toCharArray();
        int ranLength = ranChars.length;
        StringBuilder tar = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            if (ranLength - i >= 1)
            {
                tar.append(ranChars[ranLength - i - 1]);
            }
            else
            {
                tar.append('0');
            }
        }
        return tar.reverse().toString();
    }

    /**
     * 判断是否为空
     * @author 张自文
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * 判断字符串是否为null对象或是空白字符
     * @author 张自文
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 判断数组是否为空
     * @author 张自文
     * @param objs
     * @return
     */
    public static boolean isEmpty(Object[] objs)
    {
        if (null == objs || objs.length == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 获取UTC时间
     * @author 张自文
     * @return
     */
    public static Date getUtcTime()
    {
        //1、取得本地时间：
        Calendar cal = java.util.Calendar.getInstance();

        //2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

        //3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

        //4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        //之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
        Date date = new Date(cal.getTimeInMillis());
        return date;
    }

    /**
     * 获取UTC时间
     * @author 张自文
     * @param formatStr
     * @return
     */
    public static String getUtcTime(String formatStr)
    {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(getUtcTime());
    }

    /**
     * 
     * 为指定时间增加天数
     *
     * @author 张自文
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date , int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取远程客户端IP地址
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getRemoteIp(HttpServletRequest request)
    {
        if (null == request)
        {
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");

        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getRemoteAddr();
        }
        if (null != ip)
        {
            String[] ips = ip.split(",");

            if ((null != ips) && (ips.length > 0))
            {
                ip = ips[0];
            }
        }
        return ip;
    }

    /**
     * 获取服务器IP
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getLocalIp(HttpServletRequest request)
    {
        if (null == request)
        {
            return null;
        }
        String ip = request.getLocalAddr();

        if (null != ip)
        {
            String[] ips = ip.split(",");

            if ((null != ips) && (ips.length > 0))
            {
                ip = ips[0];
            }
        }
        return ip;
    }

    /**
     * 
     * 获得format格式的当天日期
     *
     * @author 张自文
     * @param format
     * @return
     */
    public static Date getToday(String format)
    {
        return parseDate(formatDate(new Date(), format), format);
    }

    /**
     * 将日期转换为指定格式的日期
     * @author 张自文
     * @param date
     * @param format
     * @return
     */
    public static Date dateFormat(Date date , String format)
    {
        return parseDate(formatDate(date, format), format);
    }

    /** 
     * 产生指定范围内的随机数(仅限非负数) 
     * @param min 最小范围 
     * @param containMin 是否包括这个最小范围(true:包括;false:不包括) 
     * @param max 最大范围 
     * @param containMax 是否包括这个最大范围(true:包括;false:不包括) 
     * @return 正常情况:>=0  异常情况:-1 
     */
    public static int threadLocalRandom(int min , boolean containMin , int max , boolean containMax)
    {
        if (min < 0 || max < 0)
        {
            return -1;
        }
        if (min > max)
        {
            max = max ^ min;
            min = max ^ min;
            max = max ^ min;
        }
        Random random = new Random();
        if (containMin == true && containMax == true)
        {
            //产生min-max之间的随机数(包括min和max，即[min,max])  
            return random.nextInt(max - min + 1) + min;
        }
        else if (containMin == true && containMax == false)
        {
            //产生min-max之间的随机数(包括min不包括max，即[min,max))  
            return random.nextInt(max - min) + min;
        }
        else if (containMin == false && containMax == false)
        {
            //产生min-max之间的随机数(不包括min也不包括max，即(min,max))  
            max = max - 1;
            return random.nextInt(max - min) + min + 1;
        }
        else
        {
            //产生min-max之间的随机数(不包括min包括max，即(min,max])  
            min = min + 1;
            return random.nextInt(max - min + 1) + min;
        }
    }

    /**
     * @param telNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generalPassTelNum(String telNum)
    {
        return telNum.substring(0, 3) + "****" + telNum.substring(7, telNum.length());
    }

    /**
     * 把字符串按照指定长度进行分割
     * @param str
     * @param length
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<String> splitString(String str , int length)
    {
        List<String> ret = new ArrayList<String>();
        if (length >= str.length())
        {
            ret.add(str);
        }
        else
        {
            while (length < str.length())
            {
                ret.add(str.substring(0, length));
                str = str.substring(length, str.length());
            }
            ret.add(str);
        }

        return ret;
    }

    /**
     * 手机号码预处理（替换掉空格和- 获取左侧第一个从1开始的数字
     * @param telNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String matchTelNum(String telNum)
    {
        if (CommonUtil.isEmpty(telNum))
        {
            return "";
        }
        else
        {
            String result = telNum.replaceAll(" ", "").replaceAll("-", "");

            //从左侧开始寻找1开头的数字即可
            if (result.indexOf("1") > 0)
            {
                result = result.substring(result.indexOf("1"));
            }

            return result;
        }
    }

    /**
     * 判定一个手机号码是否是合法的手机号码
     */
    public static boolean isTelNum(String telNum)
    {
        Pattern pattern = Pattern.compile("((\\+86)|(86)|(0))?1(3[4-9]|4[57]|5[0-9]|7[03678]|8[0-9])\\d{8}");
        Matcher matcher = pattern.matcher(telNum);
        return matcher.matches();
    }
}
