package com.slm.gme.httpserver.service;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.slm.gme.common.CommonUtil;
import com.slm.gme.framework.GmeException;
import com.slm.gme.httpserver.domain.Multipart;
import com.slm.gme.httpserver.domain.RequestHeadField;
import com.slm.gme.httpserver.domain.ResponseHeadField;
import com.slm.gme.httpserver.domain.ServiceResponse;


/**
 * 
 * @author  lipan
 * @version  [版本号, Sep 21, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractHttpService implements HttpServerService
{
    private final static Logger logger = Logger.getLogger(AbstractHttpService.class);
    
    private RequestHeadField requestHeadField;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param requestHeadField
     * @throws GmeException
     * @see [类、类#方法、类#成员]
     */
    private void validatorHeader(RequestHeadField requestHeadField)
        throws GmeException
    {
        //校验基础消息头
        validatorBaseHeader(requestHeadField);
        //校验业务消息头，子类进行重写
        validatorServiceHeader(requestHeadField);
    }
    
    //校验基础消息头，默认实现，子类可以重写此接口
    public void validatorBaseHeader(RequestHeadField requestHeadField)
        throws GmeException
    {
        //TODO 校验基础消息头
        
    }
    
    public abstract void validatorServiceHeader(RequestHeadField requestHeadField)
        throws GmeException;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param requestHeadField
     * @param String  xml字符串 
     * @throws GmeException
     * @see [类、类#方法、类#成员]
     */
    public abstract ServiceResponse processBody(RequestHeadField requestHeadField, String xmlStr)
        throws GmeException;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param requestHeadField
     * @param multiParts 多附件信息 
     * @throws GmeException
     * @see [类、类#方法、类#成员]
     */
    public abstract ServiceResponse processBody(RequestHeadField requestHeadField, Map<String, Multipart> multiParts)
        throws GmeException;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param response
     * @see [类、类#方法、类#成员]
     */
    public void responseErrorResult(HttpServletResponse response, GmeException exception)
    {
        logger.info("出现错误");
        logger.info(exception.getCause());
        // 返回失败结果，通过异常基础处理错误消息码和错误消息体
        String resultCode = exception.getErrorCode();
        
        String resultMessage = exception.getMessage();
        
        response.setHeader("resultCode", resultCode);
        response.setHeader("resultMessage", resultMessage);
        
        logger.info("resultCode:" + resultCode);
        logger.info("resultMessage:" + resultMessage);
        return;
    }
    
    protected RequestHeadField processHeader(HttpServletRequest request, HttpServletResponse response)
        throws GmeException
    {
        RequestHeadField requestHead = new RequestHeadField();
        
        @SuppressWarnings("rawtypes")
        Enumeration headerEnumeerations = request.getHeaderNames();
        
        String headerName = null;
        
        while (headerEnumeerations.hasMoreElements())
        {
            headerName = String.valueOf(headerEnumeerations.nextElement());
            
            String headValue = request.getHeader(headerName);
            
            if (CommonUtil.isNotEmpty(headValue))
            {
                headValue=headValue.trim();
            }
            
            requestHead.addHeader(headerName, headValue);
        }
        
        logger.info("reqeustHead:" + requestHead);
        
        return requestHead;
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
        //String contentType = request.getContentType();
        try
        {
            //解析消息头
            requestHeadField = processHeader(request, response);
            
            //校验消息头
            validatorHeader(requestHeadField);
            
            //处理消息体
            ServiceResponse serviceResponse;
            //如果是多附件模式，则按照多附件模式解析；如果是单xml模式则按照xml文件解析

            StringBuffer sbf = new StringBuffer();
            
            byte[] buffer = new byte[10240];
            
            int num = -1;
            
            String msg = null;
            
            InputStream in = request.getInputStream();
            
            num = in.read(buffer);
            
            while (-1 != num)
            {
                msg = new String(buffer, 0, num);
                
                sbf.append(msg);
                
                num = in.read(buffer);
            }
            
            String reqeustStr = sbf.toString();
            
            String xmlStr = URLDecoder.decode(URLDecoder.decode(reqeustStr, "UTF-8"),"UTF-8");
            
            serviceResponse = processBody(requestHeadField, xmlStr);
            
            response.setHeader("resultCode", "0");
            
            response.setHeader("resultMessage", "ok");
            
            //处理消息返回体
            processResponse(serviceResponse, response);
        }
        catch (GmeException ex)
        {
            ex.printStackTrace();
            String resultCode = "500";
            String resultMessage = "系统内部异常";
            
            //TODO 捕获到异常后抛出异常，协议级进行处理消息
            if (CommonUtil.isNotEmpty(ex.getErrorCode()))
            {
                resultCode = ex.getErrorCode();
            }
            
            if (CommonUtil.isNotEmpty(ex.getMessage()))
            {
                resultMessage = ex.getMessage();
            }
            
            response.setHeader("resultCode", resultCode);
            
            try
            {
                response.setHeader("resultMessage", URLEncoder.encode(resultMessage, "utf-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            // 返回失败结果，通过异常基础处理错误消息码和错误消息体
            String resultCode = "500";
            String resultMessage = "系统内部异常";
            response.setHeader("resultCode", resultCode);
            try
            {
                response.setHeader("resultMessage", URLEncoder.encode(resultMessage, "utf-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void processResponse(ServiceResponse serviceResponse, HttpServletResponse response)
        throws GmeException
    {
        if (serviceResponse != null)
        {
            ResponseHeadField headField = serviceResponse.getResponseHead();
            
            Set<String> keys = headField.getHeadKeys();
            
            if (keys != null && keys.size() > 0)
            {
                Iterator<String> iterator = keys.iterator();
                
                while (iterator.hasNext())
                {
                    String key = iterator.next();
                    
                    String value = (String)headField.getHeader(key);
                    
                    response.addHeader(key, value);
                }
            }
        }
        //对返回的数据进行处理
        
        //对外开的接口
        processServiceResponse(serviceResponse, response);
    }
    
    public abstract void processServiceResponse(ServiceResponse serviceResponse, HttpServletResponse response)
        throws GmeException;
    
    public RequestHeadField getRequestHeadField()
    {
        return requestHeadField;
    }
    
    public void setRequestHeadField(RequestHeadField requestHeadField)
    {
        this.requestHeadField = requestHeadField;
    }
}