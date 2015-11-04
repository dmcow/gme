package com.slm.gme.common;

import java.io.InputStream;

/**
 * 多附件传输时，每一个附件的实体对象 
 * @author  李艳海
 * @version  [版本号, Feb 25, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Multipart
{
    //多附件，每一部分的附件的名称
    String name;
    
    //每一部分的附件流的标志
    String multiFlag;
    
    //每一部分附件的输入流
    InputStream inputStream;
    
    //消息内容的mimeType
    String contentMimeType;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getMultiFlag()
    {
        return multiFlag;
    }
    
    public void setMultiFlag(String multiFlag)
    {
        this.multiFlag = multiFlag;
    }
    
    public InputStream getInputStream()
    {
        return inputStream;
    }
    
    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
    
    public String getContentMimeType()
    {
        //如果mimetype为空，则默认按照对象消息流传输
        return null == contentMimeType ? "application/octet-stream" : contentMimeType;
    }
    
    public void setContentMimeType(String contentMimeType)
    {
        this.contentMimeType = contentMimeType;
    }
}
