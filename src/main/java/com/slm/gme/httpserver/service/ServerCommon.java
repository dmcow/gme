package com.slm.gme.httpserver.service;


public interface ServerCommon
{
    /** 版本号  */
    public static String HEAD_VERSION = "version";
    
    /** 消息传输序列，由原始消息组件生成，消息传递过程中，此消息传输序列不变。
     * 消息传输序列默认固定为32位，具体格式如下：
     *  AAAAAABBBBBBBBBCCCCCCCCCCCCCCDDD
     *  其中AAAAAA：标示原始消息所在的区域的编码
     * */
    String HEAD_TRACEID = "traceid";
    
    /** 接口类型 */
    String HEAD_INTERFACETYPE = "interfacetype";
    
    /**  */
    String HEAD_SOURCECOMPTTYPE = "sourcecompttype";
    
    /**  */
    String HEAD_SOURCEAREACODE = "sourceareacode";
    
    /**  */
    String HEAD_SOURCECOMPTCODE = "sourcecomptcode";
    
    /**  */
    String HEAD_DESTCOMPTTYPE = "destcompttype";
    
    /**  */
    String HEAD_DESTAREACODE = "destareaaode";
    
    /**  */
    String HEAD_DESTCOMPTCODE = "destaomptcode";
    
    /**  */
    String HEAD_AUTHSECRET = "authsecret";
    
    /**  */
    String HEAD_TIMESTAMP = "timestamp";
    
    String RESP_HEAD_RESULTCODE = "resultcode";
    
    String RESP_HEAD_RESULTMESSAGE = "resultmessage";
    
}
