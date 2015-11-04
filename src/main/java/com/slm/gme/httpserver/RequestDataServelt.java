package com.slm.gme.httpserver;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.slm.gme.common.IConfigHelper;
import com.slm.gme.common.KpiUtil;
import com.slm.gme.httpserver.service.HttpServerService;

/**
 * 基于GHTTP协议的消息服务端解析 
 * 
 * @author  李艳海
 * @version  [版本号, Feb 26, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RequestDataServelt extends HttpServlet
{
    private static final long serialVersionUID = -4453729225078636228L;
    
    private static final Logger logger = Logger.getLogger(RequestDataServelt.class);
    
    private static final String SOURCECOMPTTYPE = "sourceComptType";
    
    //private static final String SOURCECOMPTCODE = "sourceComptCode";
    
    private static final String DESTCOMPTTYPE = "destComptType";
    
    private KpiUtil kpiUtil = new KpiUtil();
    
    /**
     * path之间 连接符号 - 
     */
    private static final String LINK_SEPARATOR = "-";
    
    public RequestDataServelt()
    {
        init();
    }
    
    public void init()
    {
        int threshold = IConfigHelper.getConfig("gme.common.properties", false).getInt("httpKpiThreshold");
        
        kpiUtil.setThreshold(threshold);
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        this.doPost(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        int threshold = IConfigHelper.getConfig("gme.common.properties", false).getInt("httpKpiThreshold");
        
        kpiUtil.setThreshold(threshold);
        
        if (kpiUtil.isOverThresholod())
        {
            resp.addHeader("resultCode", "0000007");
            resp.addHeader("resultMessage", "System is busy");
            
            return;
        }
        else
        {
            try
            {
                kpiUtil.increaseOne();
                
                /*
                 * 获取path = sourceComptType-destComptType
                 */
                String sourceComptType = req.getHeader(SOURCECOMPTTYPE);
                
                String destComptType = req.getHeader(DESTCOMPTTYPE);
                
                if (sourceComptType == null || ((sourceComptType = sourceComptType.trim()).length() == 0))
                {
                    logger.error("源目标类型为空");
                    
                    resp.addHeader("resultCode", "0000004");
                    resp.addHeader("resultMessage", "sourceCompt Type check error");
                    return;
                }
                
                if (destComptType == null || ((destComptType = destComptType.trim()).length() == 0))
                {
                    //TODO
                    logger.error("目标类型为空");
                    resp.addHeader("resultCode", "0000007");
                    resp.addHeader("resultMessage", "destCompt Type check error");
                    return;
                }
                //String sourceComptCode = req.getHeader(SOURCECOMPTCODE);
                
                String path = sourceComptType + LINK_SEPARATOR + destComptType;
                
                HttpServerService workService = ConfigInitiator.getInstance().getWorkService(path);
                
                if (workService == null)
                {
                    logger.error(path + " 下处理还不支持");
                    resp.sendError(500);
                    return;
                }
                
                workService.execute(req, resp);
            }
            finally
            {
                kpiUtil.decreaseOne();
            }
        }
    }
}
