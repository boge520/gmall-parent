package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.dto.CategoryViewDo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CategoryViewService extends IService<CategoryViewDo> {

    CategoryViewDo getViewByC3Id(Long c3Id);
}
