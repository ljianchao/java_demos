package cn.jc.mvc.controller;

import cn.jc.mvc.service.SpittleRepository;
import cn.jc.mvc.vo.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model){
        model.addAttribute("spittles",
                spittleRepository.findSpittles(Long.MAX_VALUE, 10));    // 将spittle添加到模型中
        return "spittles";  // 返回视图名称
    }

    /**
     * model的key根据类型推断为spittleList
     * 视图的名称根据请求路径推断为list
     * 访问路径为"/spittles/list"
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Spittle> mySpittles(){
        return spittleRepository.findSpittles(Long.MAX_VALUE, 10);
    }
}
