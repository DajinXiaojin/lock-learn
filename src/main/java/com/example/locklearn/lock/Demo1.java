package com.example.locklearn.lock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Person1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Demo1 {

    /**
     * aop的原理是动态代理，被拦截的方法不能是private
     * @param str
     * @param num
     * @param nm
     * @return
     */
    @RequestMapping("test")
    @MyLock(key = "'my:key'+ #str + #num + #nm")
    public String test(String str, String num, String nm){
        System.out.println("-----执行test()-----");
        return "SUCC";
    }


    @PostMapping("test1")
    public String test1(@RequestBody String req){
        System.out.println(req);

        JSONObject body1 = JSON.parseObject(req).getJSONObject("body");

        System.out.println("-----执行test()-----"+body1);

        Person1 person1 = JSON.parseObject(String.valueOf(body1), Person1.class);
        System.out.println(person1.getOwnerId());

        JSONArray objects = JSON.parseArray(body1.get("list").toString());
        System.out.println(objects);

        JSON.parseArray("");
        JSONObject.parseArray("");



        return "SUCC";
    }
    
    
    

}
