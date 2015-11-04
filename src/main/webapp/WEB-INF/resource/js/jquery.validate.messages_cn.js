
jQuery.validator.addMethod("mobile",function(value,element){
	var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return mobile.test(value);
},"手机号码格式不正确");
jQuery.validator.addMethod("word",function(value,element){
	var myword = /^[0-9a-zA-Z\u4e00-\u9fa5]*$/;
	return myword.test(value);
},"请输入字母,数字或汉字");
jQuery.validator.addMethod("eword",function(value,element){
	var myword = /^[0-9a-zA-Z]*$/;
	return myword.test(value);
},"请输入字母或数字");
jQuery.validator.addMethod("exceptChinese",function(value,element){
	var myword = /^[u4E00-u9FA5]+$/;
	if(!myword.test(value)){
	       return false;
	}
    return true;
	
},"请输入非汉字");
/* 
 * Translated default messages for the jQuery validation plugin. 
 * Language: CN 
 * Author: Fayland Lam <fayland at gmail dot com> 
 */  
jQuery.extend(jQuery.validator.messages, {  
        required: "必选字段",  
        remote: "请修正该字段",  
        email: "请输入正确格式的电子邮件",  
        url: "请输入合法的网址",  
        date: "请输入合法的日期",  
        dateISO: "请输入合法的日期 (ISO).",  
        number: "请输入合法的数字",  
        digits: "只能输入整数",  
        creditcard: "请输入合法的信用卡号",  
        equalTo: "请再次输入相同的值",  
        accept: "请输入拥有合法后缀名的字符串",  
        maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),  
        minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),  
        rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),  
        range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),  
        max: jQuery.format("请输入一个最大为 {0} 的值"),  
        min: jQuery.format("请输入一个最小为 {0} 的值")  
}); 