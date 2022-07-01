package com.atguigu.gmall.front.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.item.ItemFeignClient;
import com.atguigu.gmall.model.vo.SkuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品详情控制器
 */
@Controller
public class ItemController {

    @Autowired
    private ItemFeignClient itemFeignClient;

    //查询sku的详情
    @GetMapping("/{skuId}.html")
    public String item(@PathVariable("skuId") Long skuId, Model model) {

        Result<SkuDetailVo> skuDetailVo = itemFeignClient.getSkuDetailVo(skuId);
        SkuDetailVo data = skuDetailVo.getData();
        if (data != null) {
            model.addAttribute("categoryView", data.getCategoryView());
            model.addAttribute("skuInfo",data.getSkuInfo());
            model.addAttribute("price",data.getPrice());
            model.addAttribute("spuSaleAttrList",data.getSpuSaleAttrList());
            model.addAttribute("valuesSkuJson",data.getValuesSkuJson());
        }else {
            return "item/error";
        }
        return "item/index";
    }
}
