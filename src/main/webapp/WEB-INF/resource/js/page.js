/**
*pageDivId 分页的区域ID
*page 平台的分页JSON对象
*submitFunction 分页提交的ajax查询方法
*/
function showPage(pageDivId,page,submitFunction)
{
    var cont = '';
	
    cont += '<a style="cursor:pointer;">总共'+page.totalCount+'条</a>';
	//if (page.pageNo >= page.showNum)
	//{
	cont += '<a style="cursor:pointer;" onclick="'+submitFunction+'('+1+')">首页</a>';
	//}
	//if (page.pageNo > 1)
	//{
	cont += '<a style="cursor:pointer;" onclick="'+submitFunction+'('+page.prePage+')" >上一页</a>';
	//}
	if (page.page1 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page1)
		{
		  cont +=' pageOn';
		}
		cont +='" onclick="'+submitFunction+'('+page.page1+')">'+page.page1+'</a>';
	}
	if (page.page2 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page2)
		{
		  cont +=' pageOn';
		}
		cont +='" onclick="'+submitFunction+'('+page.page2+')">'+page.page2+'</a>';
	}
	if (page.page3 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page3)
		{
		  cont +=' pageOn';
		}
		cont +='" onclick="'+submitFunction+'('+page.page3+')">'+page.page3+'</a>';
	}
	if (page.page4 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page4)
		{
		  cont +=' pageOn';
		}
		cont += '" onclick="'+submitFunction+'('+page.page4+')">'+page.page4+'</a>';
	}
	if (page.page5 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page5)
		{
		  cont +=' pageOn';
		}
		cont += '" onclick="'+submitFunction+'('+page.page5+')">'+page.page5+'</a>';
	}
	if (page.page6 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page6)
		{
		  cont +=' pageOn';
		}
		cont += '" onclick="'+submitFunction+'('+page.page6+')">'+page.page6+'</a>';
	}
	if (page.page7 > -1)
	{
		cont += '<a style="cursor:pointer;" class="';
		if (page.pageNo == page.page7)
		{
		  cont +=' pageOn';
		}
		cont +='" onclick="'+submitFunction+'('+page.page7+')">'+page.page7+'</a>';
	}
	if (!page.lastBatch)
	{
		cont += '<a style="cursor:pointer;" class="more" onclick="javascript:void(0)">...</a>';
		cont += '<a style="cursor:pointer;" onclick="'+submitFunction+'('+page.totalPageCount+')">'+page.totalPageCount+'</a>';
	}
	//if ((page.pageNo + 1) <= page.totalPageCount)
	//{
	cont += '<a style="cursor:pointer;" onclick="'+submitFunction+'('+page.nextPage+')">下一页</a>';
	//}
	cont += '<a style="cursor:pointer;" onclick="'+submitFunction+'('+page.totalPageCount+')">尾页</a>';
    
    $("#"+pageDivId).html('');
    $("#"+pageDivId).html(cont);
}