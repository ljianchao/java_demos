package cn.jc.mvc.controller;

import cn.jc.mvc.vo.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
// 声明控制器
@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String showHomePage(Map<String, Object> model){
        List<Spittle> spittles = new ArrayList<Spittle>(){
            {
                add(new Spittle("spittle1"));
                add(new Spittle("spittle2"));
            }
        };

        model.put("spittles", spittles);

        return "home";  // return逻辑视图的名称
    }
}
