package cn.jc.mvc.controller;

import cn.jc.mvc.vo.Spittle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
// 声明控制器
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHomePage(Long id, Map<String, Object> model){
        LOGGER.info("url:/home请求参数：{}", id);
        List<Spittle> spittles = new ArrayList<Spittle>();

        model.put("spittles", spittles);

        LOGGER.info("url:/home请求完成");
        return "home";  // return逻辑视图的名称
    }
}
