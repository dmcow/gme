package com.slm.gme.notify.dao;

import java.util.List;

import com.slm.gme.framework.BaseDao;
import com.slm.gme.notify.domain.NotifyTask;


/**
 * @author daemon
 */
public interface NotifyDao extends BaseDao
{
    /**
     * 查询所有任务
     */
    public static final String GME_NOTIFY_SELECT = "gme_notify_select";
    
    /**
     * 查询任务
     */
    public static final String GME_NOTIFY_SELETEBYMAP = "gme_notify_seletebymap";
    
    /**
     * 查询TOP N任务
     */
    public static final String GME_NOTIFY_SELECTBYNUM = "gme_notify_selectbynum";
    
    /**
     * 查询TOP N任务更加组件类型
     */
    public static final String GME_NOTIFY_SELECTBYTYPE = "gme_notify_selectbytype";
    
    /**
     * 删除任务
     */
    public static final String GME_NOTIFY_DELETEBYID = "gme_notify_deletebyid";
    
    /**
     * 更新任务状态
     */
    public static final String GME_NOTIFY_UPDATE = "gme_notify_update";
    
    
    /**
     * 查询所有需要执行通知的任务包括未执行的或执行通知失败且失败次数小于3的
     * @return 任务列表
     */
    public List<NotifyTask> queryAllTasks();
    
    /**
     * 查询topnum个未执行的任务
     * @param topNum
     * @return
     */
    public List<NotifyTask> queryAllTasksByTopNum(int topNum);
    
    /**
     * 根据通知的组件类型查询topnum个未执行的任务
     * @param destComptType 通知的组件类型
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<NotifyTask> queryAllTasksByTopType(int destComptType);
    
    
}
