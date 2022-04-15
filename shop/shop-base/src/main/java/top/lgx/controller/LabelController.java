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
import top.lgx.entity.Label;
import top.lgx.service.LabelService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-12 23:22
 * @Description:
 */
@CrossOrigin
@RestController
@Api(value = "标签接口", tags = "标签接口")
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:21
     * @Params:
     * @Return: entity.Result
     * @Description: 查询所有
     */
    @GetMapping
    @ApiOperation(value = "查询所有标签")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:22
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询标签")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:22
     * @Params: label
     * @Return: entity.Result
     * @Description: 添加
     */
    @PostMapping
    @ApiOperation(value = "添加标签")
    public Result insert(@RequestBody Label label) {
        labelService.insert(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:22
     * @Params: searchMap
     * @Return: entity.Result
     * @Description: 根据条件查询
     */
    @PostMapping("/serach")
    @ApiOperation(value = "根据条件查询标签")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findSearch(searchMap));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:22
     * @Params: searchMap
     * @Params: page
     * @Params: size
     * @Return: entity.Result
     * @Description: 分页查询
     */
    @ApiOperation(value = "分页查询标签")
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:23
     * @Params: id
     * @Params: label
     * @Return: entity.Result
     * @Description: 根据id修改
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "根据ID修改标签")
    public Result update(@PathVariable String id, @RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:23
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除标签")
    public Result delete(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
