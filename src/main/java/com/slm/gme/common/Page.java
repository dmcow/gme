
package com.slm.gme.common;

import java.io.Serializable;

/**
 * 关于分页的数据类 
 * @author zhangziwen
 * @see [相关类/方法]
 * @since 1.0
 */
public class Page implements Serializable
{
    /**
     * 序号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 默认每页显示的条数
     */
    public static final int DEFAULT_PAGESIZE = 10;

    /**
     * 默认页数
     */
    public static final int DEFAULT_PAGE = 1;

    /**
     * 第几页
     */
    private int pageNo = 1;

    /**
     * 页面大小
     */
    private int pageSize = DEFAULT_PAGESIZE;

    /**
     * 总条数
     */
    private int totalCount = 0;

    /**
     * 页面一次最多可以展示的页数
     */
    private int showNum = 5;

    /**
     * 表示是否第一次查询时，就查询最后一页面
     * 0表示否
     * 1表示是
     */
    private int initLastPage = 0;

    public Page()
    {

    }

    /**
     * 构造函数
     * @param pageNo 当前页面
     * @param pageSize 每页的条数
     */
    public Page(int pageNo, int pageSize)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 默认构造函数
     *
     * @param pageNo 当前页数
     * @param pageSize 每页条数
     * @param totalCount 总数
     */
    public Page(int pageNo, int pageSize, int totalCount)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    /**
     * Mysql语法时获得开始的条数
     * 从0开始
     * @return 开始的条数
     */
    public int getLimitStart()
    {
        if ((pageNo < 0) || (pageSize < 0))
        {
            return -1;
        }

        return (pageNo - 1) * pageSize;
    }

    /**
     *  非Mysql语法时获取当前首条
     *  从1开始
     * @return 条数
     * @see [类、类#方法、类#成员]
     */
    public int getStart()
    {
        if ((pageNo < 0) || (pageSize < 0))
        {
            return -1;
        }

        return (pageNo - 1) * pageSize + 1;
    }

    /**
     * 获取当前末条
     * @return 条数
     * @see [类、类#方法、类#成员]
     */
    public int getEnd()
    {
        if ((pageNo < 0) || (pageSize < 0))
        {
            return -1;
        }

        return pageNo * pageSize;
    }

    /**
     * 获取总数
     * @return 总数
     * @see [类、类#方法、类#成员]
     */
    public int getTotalPageCount()
    {
        int count = totalCount / pageSize;
        if (totalCount % pageSize > 0)
        {
            ++count;
        }

        if (count < 1)
        {
            count = 1;
        }

        return count;
    }

    /**
     * @Description:获取第N个显示的实际页数
     * @param N的值
     * @return 实际页数
     */
    private int getPageN(int num)
    {
        if (num > showNum)
        {
            return -1;
        }

        int rstPage = ((pageNo - 1) / (showNum - 1)) * (showNum - 1) + num;

        if (rstPage <= getTotalPageCount())
        {
            return rstPage;
        }

        return -1;
    }

    /**
     * @Description:获得下一批
     */
    public int getAfterBatch()
    {
        if (getTotalPageCount() > getPageShow())
        {
            return getPageShow() + 1;
        }

        return -1;
    }

    /**
     * @Description:获得前一批
     */
    public int getBeforeBatch()
    {
        if (getPage1() > 1)
        {
            return getPage1() - showNum;
        }

        return -1;
    }

    /**
     * @Description:获取第1个显示的实际页数
     * @return 实际页数
     */
    public int getPage1()
    {
        return getPageN(1);
    }

    /**
     * @Description:获取第2个显示的实际页数
     * @return 实际页数
     */
    public int getPage2()
    {
        return getPageN(2);
    }

    /**
     * @Description:获取第3个显示的实际页数
     * @return 实际页数
     */
    public int getPage3()
    {
        return getPageN(3);
    }

    /**
     * @Description:获取第4个显示的实际页数
     * @return 实际页数
     */
    public int getPage4()
    {
        return getPageN(4);
    }

    /**
     * @Description:获取第5个显示的实际页数
     * @return 实际页数
     */
    public int getPage5()
    {
        return getPageN(5);
    }

    /**
     * @Description:获取第6个显示的实际页数
     * @return 实际页数
     */
    public int getPage6()
    {
        return getPageN(6);
    }

    /**
     * @Description:获取第7个显示的实际页数
     * @return 实际页数
     */
    public int getPage7()
    {
        return getPageN(7);
    }

    /**
     * @Description:获取第8个显示的实际页数
     * @return 实际页数
     */
    public int getPage8()
    {
        return getPageN(8);
    }

    /**
     * @Description:获取第9个显示的实际页数
     * @return 实际页数
     */
    public int getPage9()
    {
        return getPageN(9);
    }

    /**
     * @Description:获取第10个显示的实际页数
     * @return 实际页数
     */
    public int getPage10()
    {
        return getPageN(10);
    }

    /**
     * @Description:获得显示的最后一页
     * @return 页数
     */
    public int getPageShow()
    {
        return getPageN(this.showNum);
    }

    /**
     * @Description:是否有下页
     * @return true：有
     */
    public boolean isHasNextPage()
    {
        return pageNo + 1 <= getTotalPageCount();
    }

    /**
     * 获取下一页面数
     *
     * @return 页面数
     * @see [类、类#方法、类#成员]
     */
    public int getNextPage()
    {
        if (isHasNextPage())
        {
            return pageNo + 1;
        }

        return pageNo;
    }

    /**
     * 是否有前一页
     * @return true：有
     */
    public boolean isHasPrePage()
    {
        return pageNo - 1 >= 1;
    }

    /**
     * 获取前一页 <功能详细描述>
     * @return 页数
     * @see [类、类#方法、类#成员]
     */
    public int getPrePage()
    {
        if (isHasPrePage())
        {
            return pageNo - 1;
        }

        return pageNo;
    }
    
    /**
     * @Description:是否是最后一批页面
     * @return true：是最后一批页面 false:不是最后一批页面
     */
    public boolean isLastBatch()
    {
        return getPage1() + this.showNum > this.getTotalPageCount();
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;

        //如果是初始化最后一页则设置页面为最后一页
        if (1 == initLastPage)
        {
            this.setPageNo(getTotalPageCount());
        }
    }

    public int getInitLastPage()
    {
        return initLastPage;
    }

    public void setInitLastPage(int initLastPage)
    {
        this.initLastPage = initLastPage;
    }

    public int getShowNum()
    {
        return showNum;
    }

    public void setShowNum(int showNum)
    {
        this.showNum = showNum;
    }
}
