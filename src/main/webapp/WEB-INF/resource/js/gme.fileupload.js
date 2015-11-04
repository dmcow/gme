function ajaxFileUpload(callbackfun,fileId,actionUrl)
{
	var _urlPath = 'fileUpload';
	if (actionUrl)
	{
		_urlPath = actionUrl;
	}
	
	var _fileElementId = 'file';
	if (fileId)
	{
		_fileElementId = fileId;
	}
	
	$.ajaxFileUpload({
        url: _urlPath, 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: _fileElementId, // 上传文件的id、name属性名
        dataType: 'json', //返回值类型，一般设置为json、application/json
        //elementIds: ["userName"], //传递参数到服务器
        success: function(data, status){  
            if (data && data.result && data.result == 'success')
        	{
            	if (callbackfun)
        		{
            		//成功返回URL
                	callbackfun(1,data.fileUrl);
        		}
            	else
        		{
            		alert('确少回调函数callbackfun(status,fileUrl)');
        		}
        	}
            else
        	{
            	if (callbackfun)
        		{
            		//失败
                	callbackfun(0);
        		}
            	else
        		{
            		alert('确少回调函数callbackfun(status,fileUrl)');
        		}
        	}
        },
        error: function(data, status, e){ 
        	if (callbackfun)
    		{
        		//失败
            	callbackfun(0);
    		}
        	else
    		{
        		alert('确少回调函数callbackfun(status,fileUrl)');
    		}
        }
    });
    return false;  
}