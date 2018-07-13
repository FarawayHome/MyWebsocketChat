package edu.my.controller;

import edu.my.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: shi
 * @Date: 2018/6/29 23:37
 * @Description:
 */
@Controller
public class MainController {
    @Autowired
    StudentService studentService;

    @RequestMapping("toClient.do")
    public String toClient() {
        return "client";
    }

    @RequestMapping("register.do")
    public String gtRegister() {
        return "register";
    }

    @RequestMapping("login.do")
    public String gtLogin() {
        return "login";
    }

    @RequestMapping("loginServer.do")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        boolean r = studentService.login(username, password);
        if (r) {
            modelAndView.addObject("name", username);
            modelAndView.setViewName("chat");
        } else {
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value = "registerServer.do", method = RequestMethod.POST)
    public ModelAndView gtRegister(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        boolean r = studentService.register(username, password);
        if (r) {
            modelAndView.addObject("name", username);
            modelAndView.setViewName("chat");
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;

    }
}
