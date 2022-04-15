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
import top.lgx.entity.Gather;
import top.lgx.service.GatherService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-15 17:47
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/gather")
@Api(value = "活动接口", tags = "活动接口")
public class GatherController {

    @Autowired
    private GatherService gatherService;

    @GetMapping
    @ApiOperation("查询所有活动")
    private Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",gatherService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询活动")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",gatherService.findById(id));
    }

    @PostMapping
    @ApiOperation("新增活动")
    public Result insert(@RequestBody Gather gather){
        gatherService.insert(gather);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PostMapping("/search")
    @ApiOperation("根据条件查询活动")
    public Result search(@RequestBody Map map){
        return new Result(true,StatusCode.OK,"查询成功",gatherService.findSearch(map));
    }

    @ApiOperation("分页查询活动")
    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody Map map,@PathVariable int page,@PathVariable int size){
        Page<Gather> pageList = gatherService.findSearch(map,page,size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }

    @PutMapping("/{id}")
    @ApiOperation("根据ID修改活动")
    public Result update(@PathVariable String id,@RequestBody Gather gather){
        gather.setId(id);
        gatherService.update(gather);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除活动")
    public Result deleteById(@PathVariable String id){
        gatherService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
