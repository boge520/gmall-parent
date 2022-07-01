package com.atguigu.gmall.feign.product;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.vo.CategoryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/rpc/inner/product")
@FeignClient("service-product")
public interface SkuFeignClient {

    /**
     * 查询skuInfo信息
     * @param skuId
     * @return
     */
    @GetMapping("/skuinfo/{skuId}")
    Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    /**
     * 根据c3Id查询完整路径
     */
    @GetMapping("/categoryview/{c3Id}")
    Result<CategoryView> getCategoryView(@PathVariable("c3Id")Long c3Id);

    @GetMapping("/sku/saleattr/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>>  getSaleAttr(@PathVariable("skuId") Long skuId,
                                                  @PathVariable("spuId") Long spuId);
}
