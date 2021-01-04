package io.pandora.mall.module.shop;

import io.pandora.mall.response.ResponseBean;
import io.pandora.mall.search.domain.TestBean;
import io.pandora.mall.search.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@RestController
@Api(tags = {"商城系统 - 【商品服务】"})
@RequestMapping("/${mall}/product")
public class MallProductController {

    @Autowired
    private EsProductService esProductService;

    @GetMapping("/list")
    @ApiOperation("查询商品列表")
    public ResponseBean list(){
        return ResponseBean.succeed(esProductService.findAll());
    }

    @GetMapping("/save")
    @ApiOperation("保存")
    public ResponseBean save(){
        List<TestBean> list = null;
        esProductService.save(list);
        return ResponseBean.succeed("成功");
    }

}
