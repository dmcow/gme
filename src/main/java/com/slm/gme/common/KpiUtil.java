package com.slm.gme.common;

/**
 * KPI工具
 * @author Administrator
 *
 */
public class KpiUtil
{
    private int count = 0;
    
    private int threshold = 50;
    
    /**
     * 获得对象
     * @return 对象
     */
    public KpiUtil getInstance()
    {
        return new KpiUtil();
    }
    
    /**
     * 获得当前计数
     * @return 当前计数
     */
    public int getCount()
    {
        return count;
    }

    /**
     * 新增1个计数
     */
    public synchronized void increaseOne()
    {
        count++;
    }

    /**
     * 减少1个计数
     */
    public synchronized void decreaseOne()
    {
        count--;
    }
    
    /**
     * 设置阀值
     * @param threshold
     */
    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }
    
    /**
     * 是否超过阀值
     * @return true超过 false未超过
     */
    public boolean isOverThresholod()
    {
        /*if (count > threshold)
        {
            System.out.println("kpi的阀值是"+threshold+",当前的count值是"+count+",超过最大阀值!");
        }
        else
        {
            System.out.println("kpi的阀值是"+threshold+",当前的count值是"+count+",未达阀值,系统正常!");
        }*/
        
        return count > threshold;
    }
}
