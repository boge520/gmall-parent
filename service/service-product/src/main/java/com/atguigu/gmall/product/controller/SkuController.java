package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Sku接口
 */
@RestController
@RequestMapping("/admin/product")
public class SkuController {

    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 获取sku分页列表
     * @param page:第几页
     * @param limit:每页数量
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result getSkuListPage(@PathVariable("page") Long page,
                                 @PathVariable("limit") Long limit) {
        Page<SkuInfo> skuInfoPage = new Page<>(page,limit);
        Page<SkuInfo> result = skuInfoService.page(skuInfoPage);
        return Result.ok(result);
    }

    /**
     * 添加sku
     * @param skuInfo
     * @return
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        skuInfoService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable("skuId") Long skuId){
        skuInfoService.upSku(skuId);
        return Result.ok();
    }

    /**
     * 下架
     * @param skuId
     * @return
     */
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId") Long skuId){
        skuInfoService.downSku(skuId);
        return Result.ok();
    }

}
