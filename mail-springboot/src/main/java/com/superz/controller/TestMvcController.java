package com.superz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lovez
 */
@Controller
@RequestMapping("mvc")
public class TestMvcController {
    @RequestMapping("test1")
    public ModelAndView test1(String param1, String param2, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("param1", param1);
        mav.addObject("param2", param2);
        return mav;
    }
}
