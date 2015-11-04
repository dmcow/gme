function Util()
{}

/*
 *功能： 字符串前置补零.
 *参数：	__str 需要补零字符串
 *		__num 补零个数
 */
Util.prototype.addZero = function(__str, __num)
{
	__str = __str.toString();
	for(var i = __str.length; i < __num; i++){
		__str = "0"+__str;
	}
	return __str;
}

Util.prototype.getDurationS = function(__t1, __t2)
{
	var t1 = __t1.split(":");
	var t2 = __t2.split(":");
	var duration = 0;
	duration = (Math.floor(t2[0])*3600+Math.floor(t2[1])*60+Math.floor(t2[2])) - (Math.floor(t1[0])*3600+Math.floor(t1[1])*60+Math.floor(t1[2]));
	if(duration < 0) duration = duration + 86400;
	return duration;
}

Util.prototype.getDurationS2 = function(__t1, __t2)
{
	var t1 = __t1.split(":");
	var t2 = __t2.split(":");
	var duration = 0;
	duration = (Math.floor(t2[0])*3600+Math.floor(t2[1])*60+Math.floor(t2[2])) - (Math.floor(t1[0])*3600+Math.floor(t1[1])*60+Math.floor(t1[2]));
	return duration;
}

Util.prototype.numToChar = function(num, count)
{
	if(undefined == count)
	{
		count = 4;	
	}
	
	if((num + "").length >= count)
	{
		return num + "";	
	}
	else
	{
		return this._chanToStr(parseInt(count) - (num + "").length) + num + "";	
	}
}

Util.prototype._chanToStr = function(num)
{
	if(undefined == num)
	{
		num = 0;	
	}

	var resultStr = "";

	for(var i = 0; i < num; i++)
	{
		resultStr += "0";
	}

	return resultStr;
}

Util.prototype.chanStrToDate = function(strDate)
{
    var sDate = "" + strDate.substring(0, 4) + "-";
	sDate += this.numToChar(this.charToNum(strDate.substring(4, 6)) - 1) + "-";
	sDate += strDate.substring(6, 8) + " ";
	sDate += strDate.substring(8, 10) + ":";
	sDate += strDate.substring(10, 12) + ":";
	sDate += "00";
	//sDate += strDate.substring(12, 14) + "";
	
	return new Date(sDate);
}

/*
 *功能： 从url中取得参数值.
 *参数：	param 参数名
 *		url  url地址
 *      flag 是否需要进行解码
 */
Util.prototype.getURLParameter = function(param,url,flag)
{
	var params=(url.substr(url.indexOf("?") + 1)).split("&");
	if (params != null)
	{
		for(var i=0;i<params.length;i++)
		{
			var strs=params[i].split("=");
			if(strs[0]==param)
			{
				if(undefined == flag)
				{
					return strs[1];
				}
				else
				{
					return decodeURIComponent(strs[1]);
				}
			}
		} 
	}
	return "";
}

/*
 *功能： 取得url的参数部分.
 *参数：	url url地址
 */
Util.prototype.getQueryString = function(url)
{
	if(url.indexOf("?")==-1)
	{
		return "";
	}
	return url.substr(url.indexOf("?") + 1);
}

/*
 *功能： 字符串截取，被截取部分已...代替.
 *参数：	str 待截取字符串
 *      num 保留字符串位数
 */
Util.prototype.subLength = function(str,num)
{
	str.toString();
	if(str.length > num)
	{
		return str.substring(0,num) + "...";
	}
	else
	{
		return str;
	}
}

/*
 *功能： 向url中添加参数.
 *参数：	param 参数名
 *      value 参数值
 *      url   url值
 */
Util.prototype.addParamToUrl = function(param,value,url)
{
	var _param = param + "=" + value;
	if(-1 != url.indexOf("?"))
	{
		url += "&" + _param;
	}
	else
	{
		url += "?" + _param;
	}
	return url;
}

Util.prototype.encodeToUrl = function(url,jsonData)
{
	if(undefined != jsonData)
	{
		var tempUrl = "";
		for(var key in jsonData)
		{
			tempUrl += "&" + key + "=" + encodeURIComponent(jsonData[key]);
		}
		if(-1 != url.indexOf("?"))
		{
			url += tempUrl;
		}
		else
		{
			url += "?" + tempUrl.substr(1);
		}
	}
	return url;
}

/*
 *功能：从URL中获得参数
 *参数：	param	参数名
 *		url
 */
Util.prototype.getURLParameter = function(param , url)
{
	var params=(url.substr(url.indexOf("?") + 1)).split("&");
	if (params != null)
	{
		for(var i=0;i<params.length;i++)
		{
			var strs=params[i].split("=");
			if(strs[0]==param)
			{
				return strs[1];
			}
		} 
	}
	return "";
}

/*字符转义，防止页面通过innerHTML展示信息时，执行其中的脚本*/
Util.prototype.encodeToESC = function(str)
{
	return str.replace(/</g,"&lt;").replace(/>/g,"&gt;");
}

/*时间格式化 格式如:200912072153*/
Util.prototype.getTimeFromYear = function(d)
{
	var year =d.getFullYear() ;
	var month0 = (d.getMonth()+1);
	var month = (month0<10)?"0"+month0:month0;
	var day = (d.getDate()<10)?"0"+d.getDate():d.getDate();
	var hour = (d.getHours() <10)?"0"+d.getHours():d.getHours();
	var minu = (d.getMinutes()<10)?("0"+d.getMinutes()):d.getMinutes();
	var sec = (d.getSeconds()<10)?("0"+d.getSeconds()):d.getSeconds();
	return ""+year + month+day+ hour+minu+sec;
}

/**
 *功能： 日期转换函数.
 *参数：	datetimeStr	时间日期格式字符串  如：20120101130000
 */
Util.prototype.convertDatetimeFun = function(datetimeStr)
{
    var yearValue = parseInt(datetimeStr.slice(0, 4),10);
	var monthValue = parseInt(datetimeStr.slice(4, 6),10);
	var dayValue = parseInt(datetimeStr.slice(6, 8),10);
	var hourValue = parseInt(datetimeStr.slice(8, 10),10);
	var minuteValue = parseInt(datetimeStr.slice(10, 12),10);
	var secondValue = parseInt(datetimeStr.slice(12, 14),10);
	var date = new Date(yearValue,monthValue - 1,dayValue,hourValue,minuteValue,secondValue);
	return date.getTime();
}

/**
 *功能： 日期时间比较函数.
 *参数：	d1 日期时间1
 *      d2 日期时间2
 */
Util.prototype.compareDatetime = function(d1, d2)
{
	var beginDatetime = this.convertDatetimeFun(d1);
	var endDatetime = this.convertDatetimeFun(d2);
    var tempDate = Math.ceil((endDatetime - beginDatetime) / 1000);
	return tempDate;
}

Util.prototype.charToNum = function(str)
{
	if(("" || undefined) == str)
	{
		return 0;	
	}

	var result = 0;
	var topIndex = str.length - 1

	for(var i = topIndex; i >=0; i--)
	{
		result += parseInt(str.substr(i, 1)) * Math.pow(10, parseInt(topIndex) - i);	
	}

	return result;
}

/**
 *功能： 获取页面文件名函数.
 *参数：	url 页面url
 *      入参形式"http://ip:port/aaa.htm?a=1"
 *      返回值"aaa"
 */
Util.prototype.trimUrl = function(url)
{
	var tempUrlArr =  url.split(".htm");
	var tempUrlArr2 =  url.split(".jsp");

	if(1 < tempUrlArr.length)
	{
		var tempUrl =  tempUrlArr[0];
		var start = tempUrl.lastIndexOf("/") + 1;
		return tempUrl.substring(start);
	}
	else if(1 < tempUrlArr2.length)
	{
		var tempUrl =  tempUrlArr2[0];
		var start = tempUrl.lastIndexOf("/") + 1;
		return tempUrl.substring(start);
	}
	else
	{
	}
}

/**
 * @author chenwei
 * 根据Div大小自动截取字符串填充
 * @param [_wnd] [div所在的window对象]
 * @param [_divId] [div的ID]
 * @param [_str] [需要填充的字符串]
 * @param [type] [填充类型：
				  0：单页不截取；
				  1：单页截取，去掉最后一个单词，尾部加上'...',不会有单词截断(建议多行时使用)，非多行时自行处理；
				  2：支持翻页不截取
				  3：不采用自适应填充，overFlow：hidden]
 * @param [pageNum] [type=2时需要传递，标志展示第几页]
 * @return [pageCount] [type=2时返回tempArr的长度，其他返回0]
 */
Util.prototype.feedDiv = function(_wnd,_divId,_str,type,pageNum)
{
	var fillStr = _str.replace(/(^\s*)|(\s*$)/g, "");	//去掉前后空格
	var pageCount = 0;
	_wnd.document.getElementById(_divId).style.overflow = "hidden";
	
	//iPanel3.0浏览器div自有方法divideString的使用
	var tempArr = _wnd.document.getElementById(_divId).divideString(fillStr);
	if(tempArr.length > 1 && type != 3)
	{
		fillStr = tempArr[0];	
		if(type == 1)
		{
			//单页截取，去掉最后一个单词，尾部加上'...',不截断单词
			var tempWordArr = tempArr[0].replace(/(^\s*)|(\s*$)/g, "").split(" ");
			if(tempWordArr.length > 1) //如果只有一个单词，不处理，让div自动截断
			{
				fillStr = "";
				for(var i = 0; i < tempWordArr.length - 2; i++)
				{
					fillStr += tempWordArr[i] + " ";
				}
				fillStr += tempWordArr[tempWordArr.length -2] + "...";
			}
		}
		else if(type == 2)
		{
			pageCount = tempArr.length;
			fillStr =(undefined == pageNum) ? tempArr[0] : tempArr[pageNum];	
		}
	}

	_wnd.document.getElementById(_divId).innerText = fillStr;
	return pageCount;
}
/**
 * 日期加减方法：
   interval：加减的位置  d,D:日   m,M:月  h,H:小时
   number：日期周期
   date_year：
   date_month：
   date_day：
 */
Util.prototype.addDate = function (interval,number,date_year,date_month,date_day)
{
    var resultdate= new Date(date_year,date_month-1,date_day);
    return this.addDate2(interval,number,resultdate);
}
/**
 * 日期加减方法：
   interval：加减的位置  d,D:日   m,M:分钟  h,H:小时
   number：日期周期
   date:日期
 */
Util.prototype.addDate2 = function (interval,number,resultdate)
{
    var flag;
    var result;
    var Mi = 1000 * 60;
    var Hr = Mi * 60;
    var Dy = Hr * 24;
    switch(interval)
    {
    case "d":
    case "D":flag=Dy;break;
    case "m":
    case "M":flag=Mi;break;
    case "h":
    case "H":flag=Hr;break;
    default:flag=Dy;
    }
    result=resultdate.getTime()+number*flag;
    resultdate.setTime(result);
    temp_year=resultdate.getFullYear()+"";
    temp_month=resultdate.getMonth()+1+ "";
    temp_day=resultdate.getDate()+ "";
    if(temp_month.length < 2)
    {
    	temp_month = "0" + temp_month;
    }
    if(temp_day.length < 2)
    {
    	temp_day = "0" + temp_day;
    }

    return temp_year+"-"+temp_month+"-"+temp_day;
}

/**
 *将yyyy-MM-dd格式的日期，转换为Date类型 
 */
Util.prototype.formatStrToDate = function(dateStr,splitChar)
{
	if(splitChar == null || typeof splitChar == "undefined")
	{
		splitChar = "-";
	}
	var strs = dateStr.split(splitChar);
	var date = new Date(parseInt(this.charToNum(strs[0])),parseInt(this.charToNum(strs[1])) - 1,parseInt(this.charToNum(strs[2])));
	return date;
}

/**
 *判断两个日期相差多少天
 *date1:结束时间
 *date2:开始时间
 */
Util.prototype.getDateBetweenDate = function(date1,date2)
{
	var dateTime1 = date1.getTime();
	var dateTime2 = date2.getTime();
	var temp = dateTime1 - dateTime2;
	return Math.ceil(temp/(1000*60*60*24));
}

/**
 *功能： Js模拟实现java中的Map对象工具方法.
 */
Util.prototype.MapHelp = new MapHelp();

function MapHelp()
{
	this.dataArray = new Array();
}

MapHelp.prototype.put = function(key, value)
{
	var obj = this.get(key);
	if(obj == null)
	{
		var info = {};
		info.key = key;
		info.value = value;
		this.dataArray[this.dataArray.length] = info;
	}
	else
	{
		for(var i=0; i<this.dataArray.length; i++)
		{
			if(this.dataArray[i].key == key)
			{
				this.dataArray[i].value = value;
			}
		}
	}
}

MapHelp.prototype.get = function(key)
{
	for(var i=0; i<this.dataArray.length; i++)
	{
		if(this.dataArray[i].key == key)
		{
			return this.dataArray[i].value;
		}
	}
	return null;
}

MapHelp.prototype.drop = function(key)
{
	var dropIndex = -1;
	for(var i=0; i<this.dataArray.length; i++)
	{
		if(this.dataArray[i].key == key)
		{
			dropIndex = i;
			break;
		}
	}
	if(dropIndex == -1)
	{
		return;
	}
	for(var i=dropIndex; i<(this.dataArray.length - 1); i++)
	{
		this.dataArray[i] = this.dataArray[i + 1];
	}
	this.dataArray.length--;
}

MapHelp.prototype.dropAll = function()
{
	this.dataArray.length = 0;
}

MapHelp.prototype.getKeySet = function()
{
	var newArray = new Array();
	for(var i=0; i<this.dataArray.length; i++)
	{
		newArray[i] = this.dataArray[i].key; 
	}
	return newArray;
}

MapHelp.prototype.getValueSet = function()
{
	var newArray = new Array();
	for(var i=0; i<this.dataArray.length; i++)
	{
		newArray[i] = this.dataArray[i].value; 
	}
	return newArray;
}

Util.prototype.f={
	confirmDialog:function(content,a,b){
	    var o = 
	    {
	        content:'<div class="alert-content"><div class="alert-head"></div><div class="alert-body"><div class="alert-body-content"><p>' + content + '</p></div></div><div class="alert-footer"><a href="javascript:void(0)" class="btn-alert-ok">确定</a><a href="javascript:void(0)" class="btn-alert-cancel">取消</a></div></div>'
	    };
	    var dialogWrap = '<div class="overlay"></div><div class="alert-dialog"></div>';
	    var contentObj = $(o.content);
	    if(!$(".overlay").length){
	        $("body").append(dialogWrap);
	        contentObj.appendTo($(".alert-dialog"));
	    }
	    var dialogCon = $(".alert-dialog");
	    var dialogWidth = dialogCon.width(),
	        dialogHeight = dialogCon.height();
	    dialogCon.css({
	        "margin-left":- dialogWidth / 2,
	        "margin-top":- dialogHeight / 2
	    })
	    contentObj.find(".btn-alert-ok").bind("click",function(){
	    	$(".overlay").hide().remove();
	    	$(".alert-dialog").hide().remove();
	    	a();
        })
	    contentObj.find(".btn-alert-cancel").bind("click",function(){
            $(".overlay").hide().remove();
	    	$(".alert-dialog").hide().remove();
	    	b();
        })
	},
	alertDialog:function(content,func)
	{
		var o = 
	    {
	        content:'<div class="alert-content"><div class="alert-head"></div><div class="alert-body"><div class="alert-body-content"><p>' + content + '</p></div></div><div class="alert-footer"><a href="javascript:void(0)" class="btn-alert-ok">确 定</a></div></div>'
	    };
	    var dialogWrap = '<div class="overlay"></div><div class="alert-dialog"></div>';
	    var contentObj = $(o.content);
	    if(!$(".overlay").length){
	        $("body").append(dialogWrap);
	        contentObj.appendTo($(".alert-dialog"));
	    }
	    var dialogCon = $(".alert-dialog");
	    var dialogWidth = dialogCon.width(),
	        dialogHeight = dialogCon.height();
	    dialogCon.css({
	        "margin-left":- dialogWidth / 2,
	        "margin-top":- dialogHeight / 2
	    })
	    contentObj.find(".btn-alert-ok").bind("click",function(){
	    	$(".overlay").hide().remove();
	    	$(".alert-dialog").hide().remove();
	    	if(func)
	    	{
	    		func();
	    	}
        })
	}
}

//自定义confirm
Util.prototype.confirm = function(o,a,b)
{
	this.f.confirmDialog(o,a,b);
}

//自定义alert
Util.prototype.alert = function(o,f)
{
	this.f.alertDialog(o,f);
}

var Util = new Util();