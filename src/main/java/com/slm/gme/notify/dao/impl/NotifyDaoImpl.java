package com.slm.gme.notify.dao.impl;

import java.util.List;

import com.slm.gme.common.Params;
import com.slm.gme.framework.BaseDaoSupport;
import com.slm.gme.notify.dao.NotifyDao;
import com.slm.gme.notify.domain.NotifyTask;


/**
 * @author daemon
 *
 */
public class NotifyDaoImpl extends BaseDaoSupport implements NotifyDao
{
    @Override
    public List<NotifyTask> queryAllTasks()
    {
        return this.queryListByMultiCondition(new Params().toParamMap(), NotifyTask.class);
    }

    @Override
    public List<NotifyTask> queryAllTasksByTopNum(int topNum)
    {
        return this.queryListByMultiCondition(new Params("topNum", topNum).toParamMap(), NotifyTask.class);
    }
    
    public List<NotifyTask> queryAllTasksByTopType(int destComptType)
    {
        return this.queryListByMultiCondition(new Params("destComptType", destComptType).toParamMap(), NotifyTask.class);
    }
}
