package com.slm.gme.framework;

/**
 * 
 * 系统全局性异常处理类
 * 
 * @author  李艳海
 * @version  [版本号, May 22, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GmeException extends RuntimeException
{
    String errorCode;
    
    String message;
    
    private static final long serialVersionUID = -6157999957316295171L;
    
    public GmeException()
    {
        super();
    }
    
    public GmeException(String errorCode, String message)
    {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
    
    public GmeException(String message)
    {
        super(message);
        this.message = message;
    }
    
    public GmeException(Throwable cause)
    {
        super(cause);
    }
    
    public GmeException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = message;
    }
    
    public String getErrorCode()
    {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }
    
    public String getMessage()
    {
        return  this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
}
