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
import top.lgx.entity.Recruit;
import top.lgx.service.RecruitService;

import java.util.List;
import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 10:02
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/recruit")
@Api(value = "招聘接口", tags = "招聘接口")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping
    @ApiOperation(value = "查询所有招聘信息", notes = "查询所有招聘信息")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询招聘信息", notes = "根据id查询招聘信息")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }

    @GetMapping("/search/recommend")
    @ApiOperation(value = "查询推荐招聘信息", notes = "查询推荐招聘信息")
    public Result recommend(){
        List<Recruit> list = recruitService.findTop4ByStateOrderByCreateTimeDesc("2");
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @GetMapping("/search/newlist")
    @ApiOperation(value = "查询最新招聘信息", notes = "查询最新招聘信息")
    public Result newList(){
        List<Recruit> list = recruitService.findTop12ByStateNotOrderByCreatetimeDesc("0");
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping
    @ApiOperation(value = "新增招聘信息", notes = "新增招聘信息")
    public Result insert(@RequestBody Recruit recruit) {
        recruitService.insert(recruit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PostMapping("/search")
    @ApiOperation(value = "模糊查询招聘信息", notes = "模糊查询招聘信息")
    public Result findSearch(@RequestBody Map map) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    @ApiOperation(value = "分页查询招聘信息", notes = "分页查询招聘信息")
    public Result findSearch(@RequestBody Map map, @PathVariable int page, @PathVariable int size) {
        Page<Recruit> recruitList = recruitService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(recruitList.getTotalElements(), recruitList.getContent()));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改招聘信息", notes = "根据id修改招聘信息")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除招聘信息", notes = "根据id删除招聘信息")
    public Result deleteById(@PathVariable String id) {
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
