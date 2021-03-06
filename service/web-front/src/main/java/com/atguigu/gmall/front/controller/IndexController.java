package com.atguigu.gmall.front.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.product.CategoryFeignClient;
import com.atguigu.gmall.model.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryFeignClient categoryFeignClient;

    @RequestMapping("/")
    public String indexPage(Model model){

        //远程调用商品服务=查询出三级分类数据。
        Result<List<CategoryVo>> result = categoryFeignClient.getCategorys();
        List<CategoryVo> data = result.getData();
        model.addAttribute("list",data);

        return "index/index";
    }

}
