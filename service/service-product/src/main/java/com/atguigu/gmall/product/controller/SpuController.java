package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * spu接口
 */
@RestController
@RequestMapping("/admin/product")
public class SpuController {

    @Autowired
    private SpuInfoService spuInfoService;
    @Autowired
    private SpuImageService spuImageService;
    @Autowired
    private SpuSaleAttrService spuSaleAttrService;

    /**
     * 获取spu分页列表
     * @param page:第几页
     * @param limit:每页数量
     * @param category3Id:三级分类ID
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result getSpuByCategoryId(@PathVariable("page") Long page,
                                     @PathVariable("limit") Long limit,
                                     @RequestParam("category3Id") String category3Id) {
        Page<SpuInfo> spuInfoPage = new Page<>(page,limit);
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);
        Page<SpuInfo> result = spuInfoService.page(spuInfoPage, wrapper);
        return Result.ok(result);
    }

    /**
     * 商品spu_info保存
     * @param spuInfo
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    /**
     * 根据spuId获取图片列表
     * @param spuId
     * @return
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result getSpuImageList(@PathVariable("spuId") Long spuId){
        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuImage> spuImageList = spuImageService.list(wrapper);
        return Result.ok(spuImageList);
    }

    /**
     * 根据spuId获取销售属性
     * @param spuId
     * @return
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result getSpuSaleAttrList(@PathVariable("spuId") Long spuId){

        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrService.getSpuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }

}
