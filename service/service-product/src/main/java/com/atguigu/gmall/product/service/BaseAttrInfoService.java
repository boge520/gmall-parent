package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    List<BaseAttrInfo> getBaseAttrInfoWithValue(Long category1Id, Long category2Id, Long category3Id);

    void saveAttrAndValue(BaseAttrInfo baseAttrInfo);

    void updateAttrAndValue(BaseAttrInfo baseAttrInfo);

}
