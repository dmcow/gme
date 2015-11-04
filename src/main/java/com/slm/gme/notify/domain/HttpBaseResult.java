package com.slm.gme.notify.domain;

/**
 * HTTP通知消息的返回结果
 * @author daemon
 *
 */
public class HttpBaseResult
{
    /**
     * 返回码
     */
    private int responseCode;
    
    /**
     * 返回的消息描述
     */
    private String responseMessage;

    public void setResponseCode(int responseCode)
    {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public String getResponseMessage()
    {
        return responseMessage;
    }
}
