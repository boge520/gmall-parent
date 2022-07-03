package com.atguigu.gmall.item.service.impl;
import java.math.BigDecimal;
import java.util.List;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.product.SkuFeignClient;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.vo.CategoryView;

import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.vo.SkuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SkuFeignClient skuFeignClient;

    @Override
    public SkuDetailVo getItemDetail(Long skuId) {

        SkuDetailVo skuDetailVo = new SkuDetailVo();

        //1、查询基本信息
        Result<SkuInfo> data = skuFeignClient.getSkuInfo(skuId);
        SkuInfo skuInfo = data.getData();
        skuDetailVo.setSkuInfo(skuInfo);

        //2.查分类
        Long category3Id = skuInfo.getCategory3Id();
        //2.1按照三级分类id查出所在的完整分类信息
        Result<CategoryView> categoryView = skuFeignClient.getCategoryView(category3Id);
        skuDetailVo.setCategoryView(categoryView.getData());

        //3.查价格
        skuDetailVo.setPrice(skuInfo.getPrice());

        //4.查销售属性
        Long spuId = skuInfo.getSpuId();
        Result<List<SpuSaleAttr>> saleAttr = skuFeignClient.getSaleAttr(skuId, spuId);
        if (saleAttr.isOk()) {
            skuDetailVo.setSpuSaleAttrList(saleAttr.getData());
        }

        //5.一个sku对应的spu的所有sku的组合关系、
        Result<String> value = skuFeignClient.getSpudeAllSkuSaleAttrAndValue(skuInfo.getSpuId());
        skuDetailVo.setValuesSkuJson(value.getData());

        return skuDetailVo;
    }
}
