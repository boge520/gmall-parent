package com.atguigu.gmall.product.rpc;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.dto.CategoryViewDo;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.vo.CategoryView;
import com.atguigu.gmall.product.service.CategoryViewService;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SKU相关的暴露的远程接口
 */
@RestController
@RequestMapping("/rpc/inner/product")
public class SkuRpcController {

    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private CategoryViewService categoryViewService;
    @Autowired
    private SpuSaleAttrService spuSaleAttrService;

    @GetMapping("/skuinfo/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        return Result.ok(skuInfo);
    }

    /**
     * 根据c3Id查询完整路径
     */
    @GetMapping("/categoryview/{c3Id}")
    public Result<CategoryView> getCategoryView(@PathVariable("c3Id") Long c3Id) {
        CategoryViewDo viewDo = categoryViewService.getViewByC3Id(c3Id);

        //把do转成页面需要的vo；
        CategoryView view = new CategoryView();

        view.setCategory1Id(viewDo.getId());
        view.setCategory1Name(viewDo.getName());
        view.setCategory2Id(viewDo.getC2id());
        view.setCategory2Name(viewDo.getC2name());
        view.setCategory3Id(viewDo.getC3id());
        view.setCategory3Name(viewDo.getC3name());

        return Result.ok(view);
    }


    @GetMapping("/sku/saleattr/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>> getSaleAttr(@PathVariable("skuId") Long skuId,
                                                 @PathVariable("spuId") Long spuId) {


        List<SpuSaleAttr> list = spuSaleAttrService.getSpuSaleAttrAndMarkSkuSaleValue(skuId, spuId);
        return Result.ok(list);
    }
}