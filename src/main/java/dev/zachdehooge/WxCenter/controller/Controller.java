package dev.zachdehooge.WxCenter.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller{

    public Controller(){}

    @GetMapping({"/home", "/platform"})
    public String index(){
        return "index";
    }
}