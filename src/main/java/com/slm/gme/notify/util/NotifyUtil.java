package com.slm.gme.notify.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.slm.gme.common.CommonUtil;
import com.slm.gme.common.IConfigHelper;
import com.slm.gme.framework.GmeException;
import com.slm.gme.notify.common.NotifyConstant;
import com.slm.gme.notify.domain.GmeHttpConfig;
import com.slm.gme.notify.domain.ModuleCompt;
import com.slm.gme.notify.domain.NotifyConfig;
import com.slm.gme.notify.domain.NotifyModule;
import com.slm.gme.notify.domain.NotifyTask;
import com.slm.gme.common.XmlParseUtil;

/**
 * HTTP通知工具类
 * @author zhangziwen
 *
 */
public class NotifyUtil
{
    private static int messageSeq = 0;
    private static Map<String, NotifyModule> notifyModules;
    
    /**
     * 将gme.notifyconfig.xml的操作类型，转换为数据库中通知表的操作类型
     * @param operType
     * @return
     */
    public static int transOpertype(String operType)
    {
        if (NotifyConstant.Operatortype.ADD_TYPE.equals(operType))
        {
            return NotifyConstant.DBOperatortype.ADD_TYPE;
        }
        else if (NotifyConstant.Operatortype.UPDATE_TYPE.equals(operType))
        {
            return NotifyConstant.DBOperatortype.UPDATE_TYPE;
        }
        else if (NotifyConstant.Operatortype.DELETE_TYPE.equals(operType))
        {
            return NotifyConstant.DBOperatortype.DELETE_TYPE;
        }
        else if (NotifyConstant.Operatortype.MEGER_TYPE.equals(operType))
        {
            return NotifyConstant.DBOperatortype.MERGE_TYPE;
        }
        else
        {
            throw new GmeException("gme.notifyconfig.xml OperType invalid!");
        }
        
    }
    
    public static Map<String, NotifyModule> gainNotifyConfig()
    {
        if (null == notifyModules)
        {
            notifyModules = new HashMap<String, NotifyModule>();
            
            XmlParseUtil xmlParseUtil = XmlParseUtil.getInstance();
            String notifyFilePath = IConfigHelper.getConfig("gme.common.properties", false).getString("notify.config.path");
            Document doc = xmlParseUtil.loadFile(notifyFilePath);
            Element element = xmlParseUtil.getRootElement(doc);
            List<Element> list2 = xmlParseUtil.getSubElements(element, "notifymodule");
            for (Element em2 : list2)
            {
                NotifyModule notifyModule = new NotifyModule();
                String moduleCode = xmlParseUtil.getContent(em2, "modulecode");
                notifyModule.setModuleCode(moduleCode);
                notifyModule.setModuleName(xmlParseUtil.getContent(em2, "modulename"));
                List<Element> list3 = xmlParseUtil.getSubElements(em2, "moduleoperator");
                Map<String, List<ModuleCompt>> modeMap = new HashMap<String, List<ModuleCompt>>();
                for (Element em3 : list3)
                {
                    String operatortype = xmlParseUtil.getContent(em3, "operatortype");
                    List<ModuleCompt> compList = new ArrayList<ModuleCompt>();
                    
                    List<Element> list4 = xmlParseUtil.getSubElements(em3, "modulecompts");
                    for (Element em4 : list4)
                    {
                        List<Element> list5 = xmlParseUtil.getSubElements(em4, "modulecompt");
                        for (Element em5 : list5)
                        {
                            ModuleCompt moduleCompt = new ModuleCompt();
                            moduleCompt.setDestComptCode(xmlParseUtil.getAttributeValue(em5, "destComptCode"));
                            moduleCompt.setNotifyStrategy(xmlParseUtil.getAttributeValue(em5, "notifyStrategy"));
                            moduleCompt.setTemplateName(xmlParseUtil.getAttributeValue(em5, "templateName"));
                            compList.add(moduleCompt);
                        }
                    }
                    
                    modeMap.put(operatortype, compList);
                }
                notifyModule.setComptList(modeMap);
                notifyModules.put(moduleCode, notifyModule);
            }
        }
        
        return notifyModules;
    }
    
    public static NotifyModule gainNotifyConfig(String notifyTypeCode)
    {
        return gainNotifyConfig().get(notifyTypeCode);
    }
    
    /**
     * 从GME的HTTP配置文件config/gme/notify/gme.notify.xml中读取HTTP的配置如超时时间，编码等.
     * @return 配置信息
     */
    public static GmeHttpConfig getGmeHttpConfig()
    {
        GmeHttpConfig gmeHttpConfig = new GmeHttpConfig();
        gmeHttpConfig.setSourceComptCode(IConfigHelper.getConfig("gme.common.properties", false)
            .getString("global.srcComptCode"));
        gmeHttpConfig.setSourceAreaCode(IConfigHelper.getConfig("gme.common.properties", false)
            .getString("global.srcComptArea"));
        gmeHttpConfig.setSourceComptType(IConfigHelper.getConfig("gme.common.properties", false)
            .getInt("global.srcComptType"));
        gmeHttpConfig.setVersion("2.0");
        
        return gmeHttpConfig;
    }
    
    public static Map<String, String> getHeaders(NotifyConfig notifyConfig, NotifyTask notifyTask)
    {
        Map<String, String> headers = new HashMap<String, String>();
        
        GmeHttpConfig config = getGmeHttpConfig();
        
        headers.put("version", config.getVersion());
        headers.put("traceId", notifyTask.getMessageId());
        headers.put("authSecret", notifyConfig.getSecurityKey());
        //TODO 通知任务表中增加接口类型
        headers.put("interfaceType", notifyTask.getInterfaceType());
        headers.put("timeStamp", CommonUtil.formatDate(notifyTask.getCreateDate()));
        headers.put("sourceAreaCode", config.getSourceAreaCode());
        headers.put("sourceComptCode", config.getSourceComptCode());
        headers.put("sourceComptType", String.valueOf(config.getSourceComptType()));
        headers.put("destAreaCode", notifyConfig.getAreaCode());
        headers.put("destComptType", String.valueOf(notifyConfig.getComptType()));
        headers.put("destComptCode", String.valueOf(notifyConfig.getComptCode()));
        
        return headers;
    }

    public static String genralInterfaceType(String srcComptCode, String destComptCode)
    {
        String interfaceType = NotifyConstant.InterfaceType.IF1_TYPE;
        
        //TODO ???????待实现
        if (srcComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_GTMS))
        {
            if(destComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_DNAdapter))
            {
                interfaceType = NotifyConstant.InterfaceType.IF4_TYPE;
            }
            else if(destComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_GSB))
            {
                interfaceType = NotifyConstant.InterfaceType.IF5_TYPE;
            }
            else if(destComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_GMonitor))
            {
                interfaceType = NotifyConstant.InterfaceType.IF5_TYPE;
            }
            else if(destComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_GReport))
            {
                interfaceType = NotifyConstant.InterfaceType.IF8_TYPE;
            }
            else
            {
                //记录日志接口不存在
            }
        }
        else if (srcComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_DNAdapter))
        {
            if(destComptCode.startsWith(NotifyConstant.ModuleType.MODULE_TYPE_GTMS))
            {
                interfaceType = NotifyConstant.InterfaceType.IF4_TYPE;
            }
            else
            {
                //记录日志接口不存在
            }
        }
        
        return interfaceType;
    }

    /**
     * 产生MessageId 待实现?????????????
     * @return
     */
    public static String genralMessageId()
    {
        StringBuffer msgBuffer = new StringBuffer();
        
        //TODO?????????
        if (messageSeq >= 999)
        {
            messageSeq = 0;
        }
        
        messageSeq++;
        
        String seq = String.valueOf(messageSeq);
        
        while (seq.length() < 3)
        {
            seq = "0" + seq;
        }
        
        GmeHttpConfig gmeHttpConfig = getGmeHttpConfig();
        msgBuffer.append(gmeHttpConfig.getSourceAreaCode()).append(gmeHttpConfig.getSourceComptCode())
        .append(CommonUtil.formatDate(new Date(), "yyyyMMddHHmmss")).append(seq);
        
        return msgBuffer.toString();
    }
    
    /**
     * 测试HTTP地址是否连通
     * @param httpUrl HTTP地址
     * @return true HTTP地址连通 false HTTP地址不通
     * @see [类、类#方法、类#成员]
     */
    public static boolean testHttpConn(String httpUrl)
    {
        boolean ifConn = false;
        
        try
        {
            HttpURLConnection con = (HttpURLConnection)new URL(httpUrl).openConnection();
            
            con.setRequestMethod("HEAD");
            
            ifConn = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e)
        {
            ifConn = false;
        }
        
        return ifConn;
    }
}
