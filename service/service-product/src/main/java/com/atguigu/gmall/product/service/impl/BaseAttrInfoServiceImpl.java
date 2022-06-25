package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueService baseAttrValueService;

    @Override
    public List<BaseAttrInfo> getBaseAttrInfoWithValue(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.getBaseAttrInfoWithValue(category1Id,category2Id,category3Id);
    }

    @Override
    public void saveAttrAndValue(BaseAttrInfo baseAttrInfo) {
        //1.保存属性名
        baseAttrInfoMapper.insert(baseAttrInfo);
        Long id = baseAttrInfo.getId();

        //2.保存属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue value : attrValueList) {
            value.setAttrId(id);
        }
        //3.批量保存属性值
        baseAttrValueService.saveBatch(attrValueList);
    }

    @Override
    public void updateAttrAndValue(BaseAttrInfo baseAttrInfo) {
        //1、修改属性名
        baseAttrInfoMapper.updateById(baseAttrInfo);
        //2、修改属性值
        List<Long> ids = new ArrayList<>();
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue value : attrValueList) {
            //没带id
            if (value.getId()==null) {
                value.setAttrId(baseAttrInfo.getId());
                baseAttrValueService.save(value);
            }
            //带了id
            if (value.getId()!=null) {
                baseAttrValueService.updateById(value);
                ids.add(value.getId());
            }
        }
        if (ids.size()>0) {
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id",baseAttrInfo.getId());
            wrapper.notIn("id",ids);
            baseAttrValueService.remove(wrapper);
        }else {
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id",baseAttrInfo.getId());
        }
    }
}




