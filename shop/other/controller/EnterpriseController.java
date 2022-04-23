package top.lgx.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lgx.entity.Enterprise;
import top.lgx.service.EnterpriseService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 09:53
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/enterprise")
@Api(value = "企业信息接口", tags = "企业信息接口")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    @ApiOperation(value = "查询所有企业信息")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询企业信息")
    public Result finById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findById(id));
    }

    @GetMapping("/search/hotlist")
    @ApiOperation(value = "查询热门企业信息")
    public Result findByIsHot() {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findByIsHot());
    }

    @PostMapping
    @ApiOperation(value = "添加企业信息")
    public Result insert(@RequestBody Enterprise enterprise) {
        enterpriseService.insert(enterprise);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PostMapping("/search")
    @ApiOperation(value = "模糊查询企业信息")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    @ApiOperation(value = "分页查询企业信息")
    public Result findSearch(@RequestBody Map map, @PathVariable int page, @PathVariable int size){
        Page<Enterprise> pageList = enterpriseService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改企业信息")
    public Result update(@PathVariable String id, @RequestBody Enterprise enterprise) {
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除企业信息")
    public Result deleteById(@PathVariable String id) {
        enterpriseService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
