
package com.slm.gme.framework;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseService.java
 * @Description:
 * @author: zhangziwen
 * @Date: 2015年11月3日 上午9:41:18
 */
public interface BaseService
{
    <T> T queryObjectById(Object id , Class<? extends StandardBean> clz);

    <T> List<T> queryObjectByMulitiIdList(List<? extends Object> idList , Class<? extends StandardBean> clz);

    <T> T queryObjectByMulitiCondition(Map<String, Object> params , Class<? extends StandardBean> clz);

    <T> List<T> queryListByMultiCondition(Map<String, Object> params , Class<? extends StandardBean> clz);

    boolean deleteById(Object id , Class<? extends StandardBean> clz);

    boolean deleteByIdList(List<? extends Object> idList , Class<? extends StandardBean> clz);

    boolean deleteByMulitiCondition(Map<String, Object> params , Class<? extends StandardBean> clz);

    Object saveOrUpdate(StandardBean bean);

    boolean saveOrUpdate(List<StandardBean> list);

    <T> List<T> queryListByPage(Map<String, Object> params , Class<? extends StandardBean> clz);

    int queryCountByPage(Map<String, Object> params , Class<? extends StandardBean> clz);
}
