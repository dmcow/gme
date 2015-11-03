
package com.slm.gme.framework;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: BaseServiceSupport.java
 * @Description:
 * @author: zhangziwen
 * @Date: 2015年11月3日 上午9:41:45
 */
public class BaseServiceSupport implements BaseService
{
    @Autowired
    protected BaseDao baseDao;

    public <T> T queryObjectById(Object id , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryObjectById(id, clz);
    }

    public <T> List<T> queryObjectByMulitiIdList(List<? extends Object> idList , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryObjectByMulitiIdList(idList, clz);
    }

    public <T> T queryObjectByMulitiCondition(Map<String, Object> params , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryObjectByMulitiCondition(params, clz);
    }

    public <T> List<T> queryListByMultiCondition(Map<String, Object> params , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryListByMultiCondition(params, clz);
    }

    public boolean deleteById(Object id , Class<? extends StandardBean> clz)
    {
        return this.baseDao.deleteById(id, clz) > 0;
    }

    public boolean deleteByIdList(List<? extends Object> idList , Class<? extends StandardBean> clz)
    {
        return this.baseDao.deleteByIdList(idList, clz) == idList.size();
    }

    public boolean deleteByMulitiCondition(Map<String, Object> params , Class<? extends StandardBean> clz)
    {
        return this.baseDao.deleteByMulitiCondition(params, clz) > 0;
    }

    public Object saveOrUpdate(StandardBean bean)
    {
        return this.baseDao.saveOrUpdate(bean);
    }

    public boolean saveOrUpdate(List<StandardBean> list)
    {
        return this.baseDao.saveOrUpdate(list) == list.size();
    }

    public <T> List<T> queryListByPage(Map<String, Object> params , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryListByPage(params, clz);
    }

    public int queryCountByPage(Map<String, Object> params , Class<? extends StandardBean> clz)
    {
        return this.baseDao.queryCountByPage(params, clz);
    }
}
