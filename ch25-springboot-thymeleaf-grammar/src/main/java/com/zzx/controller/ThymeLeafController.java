package com.zzx.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zzx.domain.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
@RequestMapping("/temp")
public class ThymeLeafController {
    /**
     * 第一个标准变量表达式
     */
    @GetMapping("/expression01")
    public String expression01(Model model){
        //添加数据
        model.addAttribute("myData01","成功！");
        model.addAttribute("data01",new SysUser(1001,"周志雄","男",20));
        //指定视图
        return "expression01";
    }

    @GetMapping("/expression02")
    public String expression02(Model model){
        //添加数据
        model.addAttribute("myData02","成功！");
        model.addAttribute("data02",new SysUser(1001,"周志雄","男",20));
        //指定视图
        return "expression02";
    }

    @GetMapping("/expression03")
    public String expression03(Model model){
        //添加数据
        model.addAttribute("myData03","成功！");
        model.addAttribute("data03",new SysUser(1001,"周志雄","男",20));
        model.addAttribute("userId",1008);
        model.addAttribute("userName","张三");
        //指定视图
        return "expression03";
    }

    /**
     * 测试链接表达式的地址
     */
    @ResponseBody
    @GetMapping("/query/account01")
    public String queryAccount01(Integer id){
        return "queryAccount,参数:"+id;
    }

    @ResponseBody
    @GetMapping("/query/account02")
    public String queryAccount02(String name,Integer id){
        return "queryAccount,参数name:"+name+"===id:"+id;
    }


    /**
     * 模板的属性
     */
    @GetMapping("/property")
    public String useProperty(Model model){
        model.addAttribute("method","post");
        model.addAttribute("id",1234);

        model.addAttribute("key","name");
        model.addAttribute("value","周志雄");

        model.addAttribute("skey","submit");
        model.addAttribute("svalue","提交");
        return "html-property";
    }
}
