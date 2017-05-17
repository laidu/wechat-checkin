package com.laidu.bishe.backstage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信考勤系统后台
 * Created by laidu on 2017/5/15.
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class CheckInController {

    @GetMapping({"/","/index","/home"})
    public String home(ModelMap map){

        return "index";
    }
}


