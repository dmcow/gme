package com.slm.gme.framework;

import java.io.Serializable;

/**
 * 
 * @ClassName: StandardBean
 * @Description: 序列化基础类
 * @author Administrator
 * @date 2015年9月6日 上午10:36:49
 */
public class StandardBean implements Serializable {

    private static final long serialVersionUID = 1L;
  
    //表主键，所有的表都必须要有ID的字段，此字段是设计的根本
    protected Integer id;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
}
