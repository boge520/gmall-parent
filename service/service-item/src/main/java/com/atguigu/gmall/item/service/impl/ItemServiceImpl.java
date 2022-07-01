package com.atguigu.gmall.item.service.impl;
import java.math.BigDecimal;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.product.SkuFeignClient;
import com.google.common.collect.Lists;
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

        Result<SkuInfo> data = skuFeignClient.getSkuInfo(skuId);
        SkuInfo skuInfo = data.getData();
        skuDetailVo.setSkuInfo(skuInfo);

        skuDetailVo.setCategoryView(new CategoryView());

        skuDetailVo.setPrice(new BigDecimal("0"));

        skuDetailVo.setSpuSaleAttrList(Lists.newArrayList());

        skuDetailVo.setValuesSkuJson("");


        return null;
    }
}
