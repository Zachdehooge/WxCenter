package dev.zachdehooge.WxCenter.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller{

    public Controller(){}

    @GetMapping({"/"})
    public String index(){
        return "index";
    }

    @GetMapping({"/tempest"})
    public String tempest(){
        return "tempest";
    }

    @GetMapping({"/platform"})
    public String platform(){
        return "platform";
    }

    @GetMapping({"/outlook"})
    public String outlook(){
        return "outlook";
    }

    @GetMapping({"/warnings"})
    public String warnings(){
        return "warnings";
    }
}