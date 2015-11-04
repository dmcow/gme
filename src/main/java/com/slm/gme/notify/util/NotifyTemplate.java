package com.slm.gme.notify.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slm.gme.common.CollectionUtil;
import com.slm.gme.framework.GmeException;
import com.slm.gme.framework.PojoDomain;
import com.slm.gme.notify.common.NotifyConstant;
import com.slm.gme.notify.dao.NotifyDao;
import com.slm.gme.notify.domain.ModuleCompt;
import com.slm.gme.notify.domain.NamedParameter;
import com.slm.gme.notify.domain.NotifyModule;
import com.slm.gme.notify.domain.NotifyTask;
import com.slm.gme.notify.strategy.NotifyStrategy;
import com.slm.gme.notify.strategy.impl.DirectNotifyStrategy;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 基于One GME平台的HTTP协议 支持普通消息通知发送XML文件的模板；支持发送多附件的 通知消息模板
 * 
 * @author  李艳海
 * @version  [版本号, Feb 26, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class NotifyTemplate
{
    private Configuration freemarkerConfig;
    
    private NotifyDao notifyDao;
    
    /**
     * 同步通知，第一次发送非定时任务用
     * @param operType
     * @param notifyObj
     * @param notifyObjTypeCode
     */
    public synchronized void notify(String operType, PojoDomain notifyObj, String notifyObjTypeCode)
    {
        List<ModuleCompt> comptList = queryModuleComptList(operType, notifyObjTypeCode);
        
        boolean isNotifySuccess = true;
        
        if (null != comptList && !comptList.isEmpty())
        {
            for (ModuleCompt moduleCompt : comptList)
            {
                if (isNotifySuccess)
                {
                    //对每个需要通知的组件进行通知
                    isNotifySuccess = notifyModuleCompt(operType, notifyObj, notifyObjTypeCode, moduleCompt);
                }
                else
                {
                    break;
                }
            }
        }
        if (!isNotifySuccess)
        {
            throw new GmeException("通知失败!");
        }
    }
    
    private List<ModuleCompt> queryModuleComptList(String operType, String notifyObjTypeCode)
    {
        //读取gme.notifyconfig.xml的通知模块配置，查询某块（如：机构的通知）的配置信息
        NotifyModule notifyModule = NotifyUtil.gainNotifyConfig(notifyObjTypeCode);
        
        Map<String, List<ModuleCompt>> compMap = notifyModule.getComptList();
        
        //获得这个模块，某个操作如新增需要通知的组件列表
        List<ModuleCompt> comptList = null;
        if (null != compMap && !compMap.isEmpty())
        {
            comptList = compMap.get(operType);
        }
        return comptList;
    }
    
    /**
     * 供定时任务用的同步方法
     * @param operType
     * @param notifyObj
     * @param notifyObjTypeCode
     */
    public synchronized boolean notifyForTimer(String operType, PojoDomain notifyObj, String notifyObjTypeCode,
        NotifyTask notifyTask)
    {
        String destComptCode = notifyTask.getDestComptCode();
        
        ModuleCompt moduleCompt = queryModuleCompt(operType, notifyObjTypeCode, destComptCode);
        
        boolean notifyFlag = notifyModuleComptForTimer(operType, notifyObj, notifyObjTypeCode, moduleCompt);
        
        if (notifyFlag)
        {
            //成功后删除notifyTask
            notifyDao.deleteById(notifyTask.getId(), notifyTask.getClass());
        }
        else
        {
            //失败后更新状态为失败
            notifyTask.setStatus(NotifyConstant.NotifyStatus.FAIL_STATUS);
            notifyTask.setLastUpdateDate(new Date());
            notifyDao.saveOrUpdate(notifyTask);
        }
        
        return notifyFlag;
    }
    
    /**
     * 根据operType  notifyObjTypeCode destComptCode查询ModuleCompt
     * @param operType operType
     * @param notifyObjTypeCode notifyObjTypeCode
     * @param destComptCode destComptCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    private ModuleCompt queryModuleCompt(String operType, String notifyObjTypeCode, String destComptCode)
    {
        List<ModuleCompt> comptList = queryModuleComptList(operType, notifyObjTypeCode);
        
        if (!CollectionUtil.isEmpty(comptList))
        {
            for (ModuleCompt moduleCompt : comptList)
            {
                if (destComptCode.equals(moduleCompt.getDestComptCode()))
                {
                    return moduleCompt;
                }
            }
        }
        
        return new ModuleCompt();
    }
    
    /**
     * 供定时任务用的组件模块通知策略
     * @param operType
     * @param notifyObj
     * @param notifyObjTypeCode
     * @param moduleCompt
     */
    private boolean notifyModuleComptForTimer(String operType, PojoDomain notifyObj, String notifyObjTypeCode,
        ModuleCompt moduleCompt)
    {
        DirectNotifyStrategy notifyStrategy = new DirectNotifyStrategy();
        
        String templateName = moduleCompt.getTemplateName();
        
        String msgBody = genralXmlMsgBody(operType, notifyObj, templateName);
        
        boolean notifyFlag = notifyStrategy.notify(msgBody, operType, notifyObj, notifyObjTypeCode, moduleCompt);
        
        return notifyFlag;
    }
    
    /**
     * 第一次通知非定时实务时发送的策略
     * @param operType
     * @param notifyObj
     * @param notifyObjTypeCode
     * @param moduleCompt
     */
    private boolean notifyModuleCompt(String operType, PojoDomain notifyObj, String notifyObjTypeCode,
        ModuleCompt moduleCompt)
    {
        try
        {
            NotifyStrategy notifyStrategy =
                (NotifyStrategy)Class.forName(moduleCompt.getNotifyStrategy()).newInstance();
            
            String templateName = moduleCompt.getTemplateName();
            
            String msgBody = genralXmlMsgBody(operType, notifyObj, templateName);
            
            return notifyStrategy.notify(msgBody, operType, notifyObj, notifyObjTypeCode, moduleCompt);
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     * @param operType 操作类型：新增，修改，删除
     * @param notifyObj 通知对象
     * @param templateName 通知模版名称
     * @return 通知的消息体
     */
    private String genralXmlMsgBody(String operType, PojoDomain syncData, String templateName)
    {
        String resultXml = "";
        
        StringWriter out = new StringWriter();
        try
        {
            //Template template = genralTestTemplate(deviceNotifyFtlName);
            Template template = freemarkerConfig.getTemplate(templateName + ".ftl");
            
            Map<String, Object> dataMap = genralDataMap(syncData, null);
            
            dataMap.put("operType", NotifyUtil.transOpertype(operType));
            
            template.process(dataMap, out);
            
            resultXml = out.toString();
        }
        catch (Exception e)
        {
            throw new GmeException("genralXmlMsgBody产生通知消息体失败", e);
        }
        finally
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
                //日志throw new GmeException("close writer fail", e);
            }
        }
        
        return resultXml;
    }
    
    private Map<String, Object> genralDataMap(PojoDomain syncData, List<NamedParameter> namedParameters)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        
        if (null != syncData)
        {
            dataMap.put("syncData", syncData);
        }
        
        if (null != namedParameters && !namedParameters.isEmpty())
        {
            dataMap.put("namedParameters", namedParameters);
        }
        
        return dataMap;
    }
    
    public Template genralTestTemplate(String ftlName)
    {
        Template template = null;
        @SuppressWarnings("deprecation")
        Configuration cdd2 = new Configuration();
        StringBuffer sb = new StringBuffer("/WebRoot/resource/template/");
        String ftlPath = sb.append(ftlName).toString();
        try
        {
            template = cdd2.getTemplate(ftlPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return template;
    }
    
    public Configuration getFreemarkerConfig()
    {
        return freemarkerConfig;
    }
    
    public void setFreemarkerConfig(Configuration freemarkerConfig)
    {
        this.freemarkerConfig = freemarkerConfig;
    }
    
    public NotifyDao getNotifyDao()
    {
        return notifyDao;
    }
    
    public void setNotifyDao(NotifyDao notifyDao)
    {
        this.notifyDao = notifyDao;
    }
}
