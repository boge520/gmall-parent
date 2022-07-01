package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.dto.CategoryViewDo;
import com.atguigu.gmall.product.mapper.CategoryViewMapper;
import com.atguigu.gmall.product.service.CategoryViewService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryViewServiceImpl extends ServiceImpl<CategoryViewMapper, CategoryViewDo>
    implements CategoryViewService{

    @Autowired
    CategoryViewMapper categoryViewMapper;
    @Override
    public CategoryViewDo getViewByC3Id(Long c3Id) {
        QueryWrapper<CategoryViewDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c3id",c3Id);
        CategoryViewDo viewDo = categoryViewMapper.selectOne(queryWrapper);
        return viewDo;
    }
}




