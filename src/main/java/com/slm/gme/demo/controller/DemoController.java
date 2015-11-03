package com.slm.gme.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.slm.gme.demo.domain.CocBaseInfo;
import com.slm.gme.demo.service.CocBaseService;
import com.slm.gme.framework.BaseController;
import com.slm.gme.redis.RedisService;

/**
 * @ClassName: DemoController.java
 * @Description:平台测试DEMO控制器
 * @author: zhangziwen
 * @Date: 2015年11月2日 下午5:01:07
 */
@Controller
public class DemoController extends BaseController
{
    @Autowired
    private CocBaseService cocBaseService;
    
    @Autowired
    private RedisService redisService;
    
    @RequestMapping(value = "test")
    public String toTestDb(Model model)
    {
        CocBaseInfo cocBaseInfo = cocBaseService.queryObjectById(1, CocBaseInfo.class);
        
        redisService.set("test_cocBaseInfo", JSONObject.toJSONString(cocBaseInfo));
        
        String redisData = redisService.get("test_cocBaseInfo");
        
        System.out.println("redisData=" + redisData);
        
        model.addAttribute("cocBaseInfo", cocBaseInfo);
        
        return "test";
    }
}
