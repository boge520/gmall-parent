package com.atguigu.gmall.front.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.item.ItemFeignClient;
import com.atguigu.gmall.feign.product.SkuFeignClient;
import com.atguigu.gmall.model.vo.SkuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * 商品详情控制器
 */
@Controller
public class ItemController {

    @Autowired
    private ItemFeignClient itemFeignClient;
    @Autowired
    private SkuFeignClient skuFeignClient;

    //查询sku的详情
    @GetMapping("/{skuId}.html")
    public String item(@PathVariable("skuId") Long skuId, Model model) {

        Result<SkuDetailVo> data = itemFeignClient.getSkuDetailVo(skuId);

        SkuDetailVo skuDetailVo = data.getData();
        if (skuDetailVo != null) {
            //分类
            model.addAttribute("categoryView", skuDetailVo.getCategoryView());
            //sku信息
            model.addAttribute("skuInfo",skuDetailVo.getSkuInfo());
            //sku价格，现场再查一下
            Result<BigDecimal> price = skuFeignClient.getSkuPrice(skuId);
            model.addAttribute("price",price.getData());
            //spu定义的所有销售属性名和值
            model.addAttribute("spuSaleAttrList",skuDetailVo.getSpuSaleAttrList());
            //valuesSkuJson： {“118|120”：49， “119|120”：50}
            model.addAttribute("valuesSkuJson",skuDetailVo.getValuesSkuJson());
        }else {
            return "item/error";
        }
        return "item/index";
    }
}
