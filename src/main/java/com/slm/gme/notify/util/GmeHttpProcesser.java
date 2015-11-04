package com.slm.gme.notify.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.slm.gme.common.CollectionUtil;
import com.slm.gme.common.CommonUtil;
import com.slm.gme.notify.domain.HttpBaseResult;


/**
 * 发送HTTP消息
 * 
 * @author  李艳海
 * @version  [版本号, May 27, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class GmeHttpProcesser
{
    private static final int HTTPCONNECTION_TIMEOUT = 15000;
    
    private static final String HTTPMETHOD_POST = "POST";
    
    //private static final int HTTP_RESTART_THREETIMES = 1;
    
    public static HttpBaseResult send(String notifyUrl, Map<String, String> headers, String msgBody)
    {
        HttpBaseResult resResult = null;
        // HTTP接口通知其他系统时进行三次重试操作
        //for (int i = 0; i < HTTP_RESTART_THREETIMES; i++)
        //{
        // 将Http请求post给发送给目的url
        resResult = postMessageImpl(notifyUrl, headers, msgBody);
        // 通知成功
        //if (0 == resResult.getResponseCode() || 200 == resResult.getResponseCode())
        //{
        //    break;
        //}
        //}
        return resResult;
    }
    
    /**
     * 直接发送，并获取返回的结果
     * @param notifyUrl
     * @param headers
     * @param msgBody
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String sendDirect(String notifyUrl, Map<String, String> headers, String msgBody)
    {
        String resResult = "";
        
        // 构造通知的URL对象
        URL serverURL = null;
        
        BufferedWriter out = null;
        
        BufferedReader br = null;
        
        HttpURLConnection clientConn = null;
        
        try
        {
            serverURL = new URL(notifyUrl);
            // 通过通知URL对象取得连接，将通过这个连接发送通知消息
            clientConn = (HttpURLConnection)serverURL.openConnection();
            clientConn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            // 设置采用HTTP的哪个方法发送，由调用者指定。
            clientConn.setRequestMethod(HTTPMETHOD_POST);
            clientConn.setConnectTimeout(HTTPCONNECTION_TIMEOUT);
            clientConn.setDoInput(true);
            clientConn.setDoOutput(true);
            
            if (!CollectionUtil.isEmpty(headers))
            {
                for (String key : headers.keySet())
                {
                    clientConn.setRequestProperty(key, headers.get(key));
                }
            }
            
            // 完成发送
            out = new BufferedWriter(new OutputStreamWriter(clientConn.getOutputStream()));
            if (CommonUtil.isNotEmpty(msgBody))
            {
                out.append(URLEncoder.encode(URLEncoder.encode(msgBody, "utf-8"),"utf-8"));
            }
            out.flush();
            
            //获取返回结果
            br = new BufferedReader(new InputStreamReader(clientConn.getInputStream()));
            
            StringBuffer sb = new StringBuffer();
            
            String line = br.readLine();
            
            while (CommonUtil.isNotEmpty(line))
            {
                sb.append(line);
                
                line = br.readLine();
            }
            
            resResult = sb.toString();
            
        }
        catch (Exception e)
        {
        }
        finally
        {
            closeBufferedWriter(out);
            
            closeBufferedReader(br);
        }
        
        return resResult;
        
    }
    
    private static HttpBaseResult postMessageImpl(String notifyUrl, Map<String, String> headers, String msgBody)
    {
        // 构造通知的URL对象
        URL serverURL = null;
        
        BufferedWriter out = null;
        
        HttpURLConnection clientConn = null;
        
        try
        {
            serverURL = new URL(notifyUrl);
            // 通过通知URL对象取得连接，将通过这个连接发送通知消息
            clientConn = (HttpURLConnection)serverURL.openConnection();
            clientConn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            // 设置采用HTTP的哪个方法发送，由调用者指定。
            clientConn.setRequestMethod(HTTPMETHOD_POST);
            clientConn.setConnectTimeout(HTTPCONNECTION_TIMEOUT);
            clientConn.setDoInput(true);
            clientConn.setDoOutput(true);
            
            for (String key : headers.keySet())
            {
                clientConn.setRequestProperty(key, headers.get(key));
            }
            
            // 完成发送
            out = new BufferedWriter(new OutputStreamWriter(clientConn.getOutputStream()));
            out.append(URLEncoder.encode(URLEncoder.encode(msgBody, "utf-8"),"utf-8"));
            out.flush();
        }
        catch (Exception e)
        {
            HttpBaseResult httpBaseResult = new HttpBaseResult();
            httpBaseResult.setResponseCode(500);
            httpBaseResult.setResponseMessage("System Error!");
            return httpBaseResult;
        }
        finally
        {
            closeBufferedWriter(out);
        }
        
        return makeContent(clientConn);
    }
    
    /**  
     * 得到响应对象  
     *   
     * @param urlConnection  
     * @return 响应对象  
     * @throws IOException  
     */
    private static HttpBaseResult makeContent(HttpURLConnection urlConnection)
    {
        HttpBaseResult httpBaseResult = new HttpBaseResult();
        
        try
        {
            if (urlConnection.getResponseCode() == 0
                || (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300))
            {
                int resultCode = 0;
                
                if (CommonUtil.isNotEmpty(urlConnection.getHeaderField("resultCode")))
                {
                    resultCode = Integer.parseInt(urlConnection.getHeaderField("resultCode"));
                }
                
                httpBaseResult.setResponseCode(resultCode);
                
                httpBaseResult.setResponseMessage(urlConnection.getHeaderField("resultMessage"));
            }
            else
            {
                httpBaseResult.setResponseCode(urlConnection.getResponseCode());
                httpBaseResult.setResponseMessage(urlConnection.getResponseMessage());
            }
        }
        catch (Exception e)
        {
            httpBaseResult.setResponseCode(500);
            httpBaseResult.setResponseMessage("System Error!");
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
        
        return httpBaseResult;
    }
    
    private static void closeBufferedWriter(BufferedWriter out)
    {
        try
        {
            if (null != out)
            {
                out.close();
            }
        }
        catch (IOException e)
        {
            //TODO 记录异常信息"close fail in Method postMessage"
        }
    }
    
    private static void closeBufferedReader(BufferedReader in)
    {
        try
        {
            if (null != in)
            {
                in.close();
            }
        }
        catch (IOException e)
        {
            //TODO 记录异常信息"close fail in Method postMessage"
        }
    }
}
