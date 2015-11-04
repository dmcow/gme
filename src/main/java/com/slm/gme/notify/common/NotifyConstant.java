package com.slm.gme.notify.common;

public interface NotifyConstant
{
    public interface NotifyTypeCode
    {
        /**
         * 通知类型：机构
         */
        public static final String ORGANIZATION_TYPE = "1";
        
        /**
         * 通知类型：人员
         */
        public static final String EMPLOYEE_TYPE = "2";
        
        /**
         * 通知类型：样品
         */
        public static final String SAMPLE_TYPE = "3";
        
        /**
         * 通知类型：工程
         */
        public static final String PROJECT_TYPE = "4";
        
        /**
         * 通知类型：检测项目
         */
        public static final String ITEM_TYPE = "5";
    }
    
    public interface InterfaceType
    {
        /**
         * 接口类型:IF1
         */
        public static final String IF1_TYPE = "IF1";
        
        /**
         * 接口类型:IF2
         */
        public static final String IF2_TYPE = "IF2";
        
        /**
         * 接口类型:IF3
         */
        public static final String IF3_TYPE = "IF3";
        
        /**
         * 接口类型:IF4
         */
        public static final String IF4_TYPE = "IF4";
        
        /**
         * 接口类型:IF5
         */
        public static final String IF5_TYPE = "IF5";
        
        /**
         * 接口类型:IF6
         */
        public static final String IF6_TYPE = "IF6";
        
        /**
         * 接口类型:IF7
         */
        public static final String IF7_TYPE = "IF7";
        
        /**
         * 接口类型:IF8
         */
        public static final String IF8_TYPE = "IF8";
        
        /**
         * 接口类型:IF9
         */
        public static final String IF9_TYPE = "IF9";
        
        /**
         * 接口类型:IF10
         */
        public static final String IF10_TYPE = "IF10";
        
        /**
         * 接口类型:IF11
         */
        public static final String IF11_TYPE = "IF11";
        
        /**
         * 接口类型:IF12
         */
        public static final String IF12_TYPE = "IF12";
    }
    
    public interface Operatortype
    {
        /**
         * 通知操作的类型：新增
         */
        public static final String ADD_TYPE = "add";
        
        /**
         * 通知操作的类型：修改
         */
        public static final String UPDATE_TYPE = "update";
        
        /**
         * 通知操作的类型：删除
         */
        public static final String DELETE_TYPE = "delete";
        
        /**
         * 通知操作的类型：合并
         */
        public static final String MEGER_TYPE = "meger";
    }
    
    public interface OperatortypeFilePrefix
    {
        /**
         * 通知操作的类型：新增
         */
        public static final String ADD_TYPE = "Add";
        
        /**
         * 通知操作的类型：修改
         */
        public static final String UPDATE_TYPE = "Update";
        
        /**
         * 通知操作的类型：合并
         */
        public static final String MEGER_TYPE = "Meger";
        
        /**
         * 通知操作的类型：删除
         */
        public static final String DELETE_TYPE = "Delete";
    }
    
    public interface DBOperatortype
    {
        /**
         * 通知操作的类型：新增
         */
        public static final int ADD_TYPE = 1;
        
        /**
         * 通知操作的类型：修改
         */
        public static final int UPDATE_TYPE = 2;
        
        /**
         * 通知操作的类型：删除
         */
        public static final int DELETE_TYPE = 3;
        
        /**
         * 通知操作的类型：合并
         */
        public static final int MERGE_TYPE = 4;
        
        /**
         * 通知操作的类型：删除所有
         */
        public static final int DELETEALL_TYPE = 5;
    }
    
    public interface ModuleType
    {
        /**
         * GTMS类型
         */
        public static final String MODULE_TYPE_GTMS = "01";
        
        /**
         * GBMS类型
         */
        public static final String MODULE_TYPE_GBMS = "02";
        
        /**
         * GReport类型
         */
        public static final String MODULE_TYPE_GReport = "03";
        
        /**
         * DNAdapter类型
         */
        public static final String MODULE_TYPE_DNAdapter = "04";
        
        /**
         * GMonitor类型
         */
        public static final String MODULE_TYPE_GMonitor = "05";
        
        /**
         * GPortal类型
         */
        public static final String MODULE_TYPE_GPortal = "06";
        
        /**
         * GSB类型
         */
        public static final String MODULE_TYPE_GSB = "00";
    }
    
    public interface NotifyStatus
    {
        /**
         * 执行成功
         */
        public static final int SUCCESSFUL_STATUS = 3;
        
        /**
         * 初始，待执行
         */
        public static final int INIT_STATUS = 1;
        
        /**
         * 执行中
         */
        public static final int EXECUTE_STATUS = 2;
        
        /**
         * 执行失败
         */
        public static final int FAIL_STATUS = 3;
        
    }
    
    public interface NotifyObjTypeCode
    {
        //1：试验任务推送接口类型
        public static final String EXAM_TASK_TYPE = "1";
        
        //2：检测试验结果回传接口类型
        public static final String EXAM_RESULT_TYPE = "2";
        
        //3：对接监管平台信息模型
        public static final String MONITOR_CONFIG_TYPE = "3";
        
        //4：检测项目和试验类型的关联关系
        public static final String ITEM_EXAM_TYPE = "4";
        
        //5：检测项目和人员关系模型
        public static final String ITEM_EMPLOYEE_TYPE = "5";
        
        //6：操作员基本信息模型
        public static final String OPERATOR_TYPE = "6";
        
        //7：设备基本信息模型
        public static final String DEVICE_TYPE = "7";
        
        //8：监管设备基本信息模型
        public static final String DEVICEGMONITOR_TYPE = "8";
        
        //9：机构基本信息模型
        public static final String ORG_TYPE = "9";
        
        //10：人员基本信息模型
        public static final String EMP_TYPE = "10";
        
        //11：工程基本信息模型
        public static final String PROJECT_TYPE = "11";
        
        //13：报告曲线基本信息模型
        public static final String CURVE_TYPE = "13";
        
        //14：委托信息绑定IC卡模型
        public static final String COLLOCORDER_TYPE = "14";
    }
    
    public interface DBNotifyObjTypeCode
    {
        //1：试验任务推送接口类型
        public static final int EXAM_TASK_TYPE = 1;
        
        //2：检测试验结果回传接口类型
        public static final int EXAM_RESULT_TYPE = 2;
        
        //3：对接监管平台信息模型
        public static final int MONITOR_CONFIG_TYPE = 3;
        
        //4：检测项目和试验类型的关联关系
        public static final int ITEM_EXAM_TYPE = 4;
        
        //5：检测项目和人员关系模型
        public static final int ITEM_EMPLOYEE_TYPE = 5;
        
        //6：操作员基本信息模型
        public static final int OPERATOR_TYPE = 6;
        
        //7：设备基本信息模型
        public static final int DEVICE_TYPE = 7;
        
        //8：监管设备基本信息模型
        public static final int DEVICEGMONITOR_TYPE = 8;
        
        //9：机构基本信息模型
        public static final int ORG_TYPE = 9;
        
        //10：人员基本信息模型
        public static final int EMP_TYPE = 10;
        
        //11：工程基本信息模型
        public static final int PROJECT_TYPE = 11;
        
        //13：报告曲线基本信息模型
        public static final int CURVE_TYPE = 13;
        
        //14：委托信息绑定IC卡模型
        public static final int COLLOCORDER_TYPE = 14;
    }
    
    /**
     * 基于ONEGME解决方案的HTTP+XML协议的公共错误返回码定义
     * @author zhangziwen
     *
     */
    public interface ReturnCode
    {
        /**
         * 成功
         */
        public static final String RETURNCODE_SUCCESS = "0";
        
        /**
         * Version校验错误
         */
        public static final String VERSION_ERROR = "0000001";
        
        /**
         * traceId校验错误
         */
        public static final String TRACEID_ERROR = "0000002";
        
        /**
         * interfaceType校验写错误
         */
        public static final String IFTYPE_ERROR = "0000003";
        
        /**
         * sourceCompt Type校验错误
         */
        public static final String SOURCECOMPTTYPE_ERROR = "0000004";
        
        /**
         * sourceAreaCode校验错误
         */
        public static final String SOURCEAREACODE_ERROR = "0000005";
        
        /**
         * sourceComptCode校验错误
         */
        public static final String SOURCECOMPTCODE_ERROR = "0000006";
        
        /**
         * destComptType校验错误
         */
        public static final String DESTCOMPTTYPE_ERROR = "0000007";
        
        /**
         * destAreaCode校验错误
         */
        public static final String DESTAREACODE_ERROR = "0000008";
        
        /**
         * authSecret校验错误
         */
        public static final String UTHSECRET_ERROR = "0000009";
        
        /**
         * timeStamp校验错误
         */
        public static final String TIMESTAMP_ERROR = "0000010";
        
        /**
         * 消息体解析异常
         */
        public static final String PASREBODY_ERROR = "0000011";
        
        /**
         * 系统其他异常
         */
        public static final String SYSTEMOTHER_ERROR = "999999";
    }
}
