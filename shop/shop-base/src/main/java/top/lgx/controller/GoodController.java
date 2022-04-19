package top.lgx.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
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
import top.lgx.entity.Good;
import top.lgx.service.GoodService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 21:56
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/good")
@Api(value = "商品接口", tags = "商品接口")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK,"查询成功", goodService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK,"查询成功", goodService.findById(id));
    }

    @PostMapping
    public Result insert(@RequestBody Good good){
        goodService.insert(good);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK,"查询成功", goodService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map map, @PathVariable int page, @PathVariable int size){
        Page<Good> pageList = goodService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK,"查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Good good, @PathVariable String id){
        good.setId(id);
        goodService.update(good);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        goodService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
