package com.zzx.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@RestController
public class MyRestController {
    /**
     * 加入了@RestController  表示都加入了@ResponseBody注解，返回都是String
     * @PathVariable 路径变量，用来获取url里面的值 属性value:路径变量名
     * @PathVariable 位置在控制器方法形参的前面
     * http://localhost:8080/myboot/student/1001
     * stuId就是1001
     */
    @GetMapping("/student/{stuId}")
    public String queryStudent( @PathVariable(value = "stuId")Integer studentId){
        //表示把{stuId}的值传给studentId
        return "学生id:"+studentId;
    }


    /**
     * http://localhost:8080/myboot/student/周志雄/20
     * @param name
     * @param age
     * @return
     */
    @PostMapping("/student/{name}/{age}")
    public String addStudent(@PathVariable("name")String name,@PathVariable("age")Integer age){
        return "添加学生姓名:"+name+"年龄:"+age;
    }

    @PutMapping("/student/{id}/{name}/{age}")
    /**
     * 当路径变量名称和形参名一样的时候，可以省略value
     */
    public String modifyStudent(@PathVariable Integer id,@PathVariable String name,@PathVariable Integer age){
        return "更新资源put方式,id:"+id+"姓名"+name+"年龄:"+age;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable Integer id){
        System.out.println("访问了资源");
        return "删除学生编号:"+id;
    }

    @PutMapping("/student/test")
    public String test(){
        return "put请求";
    }

    @DeleteMapping("/student/deletetest")
    public String myTest(){
        return "delete方式";
    }
}
