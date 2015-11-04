package com.slm.gme.notify.domain;

import java.util.List;
import java.util.Map;

/**
 * 通知模块类型
 * @author daemon
 *
 */
public class NotifyModule
{
    /**
     * 通知：模块编码
     */
    private String moduleCode;
    
    /**
     * 通知：模块名称
     */
    private String moduleName;
    
    /**
     * 通知：操作类型与通知组件列表映射，如新增操作对应的通知模块列表
     */
    private Map<String, List<ModuleCompt>> comptList;

    public String getModuleCode()
    {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = moduleCode;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public Map<String, List<ModuleCompt>> getComptList()
    {
        return comptList;
    }

    public void setComptList(Map<String, List<ModuleCompt>> comptList)
    {
        this.comptList = comptList;
    }
}
