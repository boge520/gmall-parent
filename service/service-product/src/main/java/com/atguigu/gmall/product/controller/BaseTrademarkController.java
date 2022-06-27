package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌属性
 */
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;

    /**
     * 获取所有的品牌属性
     * @return
     */
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> trademarkList = baseTrademarkService.list();
        return Result.ok(trademarkList);
    }

    /**
     * 获取品牌分页列表
     * @param page：第几页
     * @param limit：每页数量
     * @return
     */
    @GetMapping("/baseTrademark/{page}/{limit}")
    public Result getTrademarkPage(@PathVariable("page") Long page,
                               @PathVariable("limit") Long limit) {
        Page<BaseTrademark> trademarkPage = new Page<>(page,limit);
        Page<BaseTrademark> result = baseTrademarkService.page(trademarkPage);
        return Result.ok(result);
    }

    /**
     * 添加品牌
     * @param baseTrademark
     * @return
     */
    @PostMapping("/baseTrademark/save")
    public Result save(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * 根据品牌id修改品牌
     * @param baseTrademark
     * @return
     */
    @PutMapping("/baseTrademark/update")
    public Result update(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    /**
     * 根据Id获取品牌
     * @param id :品牌Id
     * @return
     */
    @GetMapping("/baseTrademark/get/{id}")
    public Result getTrademarkById(@PathVariable("id") Long id){
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    /**
     * 删除品牌
     * @param id:删除品牌id
     * @return
     */
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result remove(@PathVariable("id") Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

}
