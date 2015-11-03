
package com.slm.gme.common;

import java.io.File;

/**
 * 平台侧常量类
 * @author  zhangziwen
 * @see  [相关类/方法]
 * @date 2015年8月28日 下午3:56:05
 */
public interface CommonConst
{
    public final String CONTENTTYPE_HTMLTEXT = "text/html; charset=UTF-8";

    //UTF-8编码
    public static final String CHARSET_UTF_8 = "UTF-8";

    //普通公共的分隔符
    public static final String COC_COMMON_SPLITER = "/";

    //点别名
    public static final String COC_COMMON_POINT = ".";

    //通配符*的别名
    public static final String COC_COMMON_WILDCARD = "*";

    //逗号的别名
    public static final String COC_COMMON_DOUHAO = ",";

    //冒号的别名
    public static final String COC_COMMON_MAOHAO = ":";

    //连接串的别名
    public static final String COC_COMMON_AND = "&";

    //等号的别名
    public static final String COC_COMMON_DENGHAO = "=";

    //文件的分隔符
    public static final String COC_FILE_SEPARATOR = File.separator;

    //下划线
    public static final String COC_COMMON_ENDLINE = "_";

    /**
     *日期常量 
     * 
     * @author  symlich
     * @version  [版本号, Jun 7, 2012]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public interface GmeDate
    {
        /**
         * 默认的日期时间格式：yyyyMMddHHmmss
         */
        public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

        /**
         * 用于页面显示的日期时间格式：yyyy-MM-dd HH:mm:ss
         */
        public static final String DISPLAYED_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

        /**
         * 日期格式（年月日）：yyyyMMdd
         */
        public static final String DATE_FORMAT = "yyyyMMdd";

        /**
         * 用于页面显示的日期格式（年-月-日）：yyyy-MM-dd
         */
        public static final String DISPLAYED_DATE_FORMAT = "yyyy-MM-dd";

        /**
         * 时间格式：HHmmss
         */
        public static final String TIME_FORMAT = "HHmmss";

        /**
         * 用于页面显示的时间格式：HH:mm:ss
         */
        public static final String DISPLAYED_TIME_FORMAT = "HH:mm:ss";

        /**
         * 默认的媒体文件删除时间
         */
        public static final String DEFAULT_ACCESS_ENDDATETIME = "20991231235959";
    }

    /**
     * 机构认证中心的审核记录的常量
     * 
     * @author Administrator
     *
     */
    public interface CocCompanyQualityConstant
    {
        //企业资质的审核状态  ：未审核
        public static final Integer COMPANYQUALITY_AUDITSTATUS_NOTAUDIT = 1;

        //企业资质的审核状态：已审核通过
        public static final Integer COMPANYQUALITY_AUDITSTATUS_PASS = 2;

        //企业资质的审核状态：未审核通过
        public static final Integer COMPANYQUALITY_AUDITSTATUS_REFUND = 3;

        //商家认证提交时间：最近一周
        public static final Integer COMPANYQUALITY_DATETYPE_LASTWEEK = 1;

        //商家认证提交时间：最近一月
        public static final Integer COMPANYQUALITY_DATETYPE_LASTMONTH = 2;

        //商家认证提交时间：最近三月
        public static final Integer COMPANYQUALITY_DATETYPE_LASTTHREEMONTH = 3;

        //商家认证提交时间：最近一年
        public static final Integer COMPANYQUALITY_DATETYPE_LASTYEAR = 4;

    }

    /**
     * @ClassName: CommonConst.java
     * @Description:模板常量
     * @author: zhangziwen
     * @Date: 2015年9月17日 下午2:29:33
     */
    public interface CocTemplate
    {
        /**
         * 模板状态：启用
         */
        public static final Integer STATUS_ON = 1;

        /**
         * 模板状态：禁用
         */
        public static final Integer STATUS_OFF = 2;

        /**
         * 是否需要上传图片：非质量属性
         */
        public static final Integer ISPICTURE_NOT = -1;

        /**
         * 是否需要上传图片：需要
         */
        public static final Integer ISPICTURE_YES = 1;

        /**
         * 是否需要上传图片：不需要
         */
        public static final Integer ISPICTURE_NO = 2;

        /**
         * 是否可选：非质量属性
         */
        public static final Integer ISREQUIRED_NOT = -1;

        /**
         * 是否可选：必选
         */
        public static final Integer ISREQUIRED_YES = 1;

        /**
         * 是否可选：可选
         */
        public static final Integer ISREQUIRED_NO = 2;
    }

    /**
     * 行业分类的常量类
     * 
     * @author 李艳海
     *
     */
    public interface IndustryCategoryConstant
    {
        //未封装
        String QUERY_CATEGORY_ALL = "1";

        //已经封装
        String QUERY_CATEGORY_COMM = "2";
    }

    /**
     * 平台类型的常量
     * @author 李艳海
     */
    public interface PlantFormType
    {
        //平台类型=BMC
        Integer PLANTFORMTYPE_BMC = 1;

        //平台类型-COC
        Integer PLANTFORMTYPE_COC = 2;

        //平台类型-CMC
        Integer PLANTFORMTYPE_CMC = 3;
    }

    /**
     * @ClassName: CommonConst.java
     * @Description: cooike常量
     * @author: zhangziwen
     * @Date: 2015年10月28日 上午10:28:55
     */
    public interface CookieConst
    {
        //记住密码COOKIE常量
        String COOKIE_REMEMBER_ACOUNT = "cookie_remember_acount";
    }
}