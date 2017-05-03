package cn.jc.mvc.controller;

import cn.jc.mvc.service.SpitterRepository;
import cn.jc.mvc.vo.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/4/26.
 */
@Controller
@RequestMapping("/spitter") //根url路径
public class SpitterController {

    @Autowired
    SpitterRepository spitterRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

    /**
     * Spitter属性会使用请求中同名的参数进行填充
     * Spitter必须有默认构造函数
     * @param spitter
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors){
        if (errors.hasErrors()){
            return "registerForm";
        }   // 校验Spitter输入，需要hibernate-validation包的支持

        spitterRepository.sava(spitter);
        return "redirect:/spitter/" + spitter.getUsername();   // 充定向到基本信息页
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model){
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
