package com.slm.gme.notify.domain;

/**
 * 通知组件
 * @author daemon
 *
 */
public class ModuleCompt
{
    /**
     * 通知：源组件编码
     */
    //private String srcComptCode;
    
    /**
     * 通知：目标组件编码
     */
    private String destComptCode;
    
    /**
     * 通知：通知策略
     */
    private String notifyStrategy;
    
    /**
     * 通知的模版名称
     */
    private String templateName;

    /*public String getSrcComptCode()
    {
        return srcComptCode;
    }

    public void setSrcComptCode(String srcComptCode)
    {
        this.srcComptCode = srcComptCode;
    }*/

    public String getDestComptCode()
    {
        return destComptCode;
    }

    public void setDestComptCode(String destComptCode)
    {
        this.destComptCode = destComptCode;
    }

    public String getNotifyStrategy()
    {
        return notifyStrategy;
    }

    public void setNotifyStrategy(String notifyStrategy)
    {
        this.notifyStrategy = notifyStrategy;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }
}
