
package com.slm.gme.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * @author 张自文
 * @version  [版本号, Oct 28, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CookieUtil
{
    /**
     * 设置cookie
     * @param response 响应
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response , String name , String value , int maxAge)
    {
        try
        {
            String tempValue = java.net.URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, tempValue);
            if (maxAge > 0)
            {
                cookie.setMaxAge(maxAge);
            }
            response.addCookie(cookie);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 清除指定的cookie
     * @param request 请求
     * @param name cookie名称
     */
    public static void removeCookie(HttpServletRequest request , String name , HttpServletResponse response)
    {
        Map<String, Cookie> cookieMap = readCookieMap(request);

        if (cookieMap.containsKey(name))
        {
            Cookie cookie = (Cookie) cookieMap.get(name);
            cookie.setValue(null);
            // 置为失效
            cookie.setMaxAge(0);

            response.addCookie(cookie);
        }
    }

    /**
    * 根据名字获取cookie
    * @param request
    * @param name cookie名字
    * @return COOKIE
    */
    public static Cookie getCookieByName(HttpServletRequest request , String name)
    {
        Map<String, Cookie> cookieMap = readCookieMap(request);

        if (cookieMap.containsKey(name))
        {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        }
        else
        {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * @param request 请求
     * @return 参数
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request)
    {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

        Cookie[] cookies = request.getCookies();

        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
