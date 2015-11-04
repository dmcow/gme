jQuery.extend({
	showMask:function(loadingPicUrl){
		var loader = $("#div_maskContainer");
		if (loader.length == 0)
		{
			var w=$(window).width(); 
			var h=$(window).height(); 
			var _loadingPicUrl = '/coc/resource/common/images/loading.gif';
			if (loadingPicUrl)
			{
				_loadingPicUrl=loadingPicUrl;
			}
			alert(_loadingPicUrl);
			loader = $("<div id='div_maskContainer' style='display:none'><div id='div_mask' style='z-index:1000;filter:alpha(opacity=40); position: absolute;left:0px; top:0px; background-color: #D4D0C8;width:"+w+"px;height:"+h+"px;'><div id='div_loading' style='position: absolute;left:w/2px;top:h/2px;backgroud:url(\""+_loadingPicUrl+"\")'></div></div></div>");
			$("body").append(loader);
		}
		loader.show();
	},
	hideMask:function(){
		var loader = $("#div_maskContainer");
		if (loader.length > 0)
		{
			loader.hide();
		}
	}
});
