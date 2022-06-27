package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售属性
 */
@RestController
@RequestMapping("/admin/product")
public class SaleAttrController {

    @Autowired
    private BaseSaleAttrService baseSaleAttrService;

    /**
     * 获取销售属性
     * @return
     */
    @GetMapping("/baseSaleAttrList")
    public Result getBaseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList = baseSaleAttrService.list();
        return Result.ok(baseSaleAttrList);
    }

}
