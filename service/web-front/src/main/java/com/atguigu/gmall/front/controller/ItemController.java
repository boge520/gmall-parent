package com.atguigu.gmall.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @GetMapping("/{skuId}.html")
    public String item(@PathVariable() Long skuId, Model model){

        model.addAttribute("",null);
        return "item/index";
    }
}
