package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台属性接口
 */
@Slf4j
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {

    @Autowired
    private BaseAttrInfoService baseAttrInfoService;
    @Autowired
    private BaseAttrValueService baseAttrValueService;

    /**
     * 根据分类id获取平台属性
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrInfoList(@PathVariable("category1Id") Long category1Id,
                                  @PathVariable("category2Id") Long category2Id,
                                  @PathVariable("category3Id") Long category3Id) {
        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoService.getBaseAttrInfoWithValue(category1Id, category2Id, category3Id);
        return Result.ok(baseAttrInfoList);
    }

    /**
     * 添加/修改平台属性
     *
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        log.info("保存/修改属性: {}", baseAttrInfo);
        if (baseAttrInfo.getId() != null) {
            //修改属性
            baseAttrInfoService.updateAttrAndValue(baseAttrInfo);
        } else {
            //保存属性
            baseAttrInfoService.saveAttrAndValue(baseAttrInfo);
        }
        return Result.ok();
    }

    /**
     * 根据平台属性ID获取平台属性对象数据
     *
     * @param attrId
     * @return
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId") Long attrId) {
        QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id", attrId);
        List<BaseAttrValue> attrValueList = baseAttrValueService.list(wrapper);
        return Result.ok(attrValueList);
    }

}
