package com.slm.gme.notify.domain;

/**
 * 获取GME HTTP消息的公共配置
 * @author daemon
 *
 */
public class GmeHttpConfig
{
    /**
     * 通知的URL
     */
    private String url;
    
    /**
     * 超时的时间
     */
    private int timeOut = 10000;
    
    /**
     * 发送方法
     */
    private String sendMethod = "POST";
    
    /**
     * 字符编码
     */
    private String charEncode = "UTF-8";
    
    /**
     * sourceComptType
     */
    private int sourceComptType;
    
    /**
     * sourceAreaCode
     */
    private String sourceAreaCode;
    
    /**
     * sourceComptCode
     */
    private String sourceComptCode;
    
    private String version = "2.0";
    
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public int getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(int timeOut)
    {
        this.timeOut = timeOut;
    }

    public String getSendMethod()
    {
        return sendMethod;
    }

    public void setSendMethod(String sendMethod)
    {
        this.sendMethod = sendMethod;
    }

    public String getCharEncode()
    {
        return charEncode;
    }

    public void setCharEncode(String charEncode)
    {
        this.charEncode = charEncode;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public int getSourceComptType()
    {
        return sourceComptType;
    }

    public void setSourceComptType(int sourceComptType)
    {
        this.sourceComptType = sourceComptType;
    }

    public String getSourceAreaCode()
    {
        return sourceAreaCode;
    }

    public void setSourceAreaCode(String sourceAreaCode)
    {
        this.sourceAreaCode = sourceAreaCode;
    }

    public String getSourceComptCode()
    {
        return sourceComptCode;
    }

    public void setSourceComptCode(String sourceComptCode)
    {
        this.sourceComptCode = sourceComptCode;
    }
    
    
}
