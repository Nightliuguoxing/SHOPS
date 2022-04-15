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
import top.lgx.entity.Problem;
import top.lgx.service.ProblemService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 10:26
 * @Description:
 */
@CrossOrigin
@RestController
@Api(value = "问题接口", tags = "问题接口")
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:36
     * @Params:
     * @Return: entity.Result
     * @Description: 查询所有问题
     */
    @GetMapping
    @ApiOperation(value = "查询所有问题", notes = "查询所有问题")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:37
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询问题
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询问题", notes = "根据id查询问题")
    public Result findById(String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:37
     * @Params: labelId
     * @Params: page
     * @Params: size
     * @Return: entity.Result
     * @Description: 根据标签id查询最新问题
     */
    @GetMapping("/newlist/{labelId}/{page}/{size}")
    @ApiOperation(value = "根据标签id查询最新问题", notes = "根据标签id查询最新问题")
    public Result findNewListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pagelist = problemService.findNewListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(pagelist.getTotalElements(), pagelist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:50
     * @Params: labelId
     * @Params: page
     * @Params: size
     * @Return: entity.Result
     * @Description: 根据标签id查询最热问题
     */
    @GetMapping("/hotlist/{labelId}/{page}/{size}")
    @ApiOperation(value = "根据标签id查询最热问题", notes = "根据标签id查询最热问题")
    public Result findHotListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pagelist = problemService.findHotListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(pagelist.getTotalElements(), pagelist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:52
     * @Params: label
     * @Params: page
     * @Params: size
     * @Return: entity.Result
     * @Description: 根据标签id查询等待回答问题
     */
    @GetMapping("/waitlist/{label}/{page}/{size}")
    @ApiOperation(value = "根据标签id查询等待回答问题", notes = "根据标签id查询等待回答问题")
    public Result findWaitListByLabelId(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pagelist = problemService.findWaitListByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult<>(pagelist.getTotalElements(), pagelist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:38
     * @Params: problem
     * @Return: entity.Result
     * @Description: 添加问题
     */
    @PostMapping
    @ApiOperation(value = "添加问题", notes = "添加问题")
    public Result insert(@RequestBody Problem problem) {
        problemService.insert(problem);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:37
     * @Params: searchMap
     * @Return: entity.Result
     * @Description: 根据条件查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "根据条件查询", notes = "根据条件查询")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:37
     * @Params: page
     * @Params: size
     * @Params: searchMap
     * @Return: entity.Result
     * @Description: 分页查询
     */
    @PostMapping("/search/{page}/{size}")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result findSearch(@PathVariable int page, @PathVariable int size, @RequestBody Map searchMap) {
        Page<Problem> pagelist = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pagelist.getTotalElements(), pagelist.getContent()));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:38
     * @Params: problem
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id修改问题
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改问题", notes = "根据id修改问题")
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:38
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除问题
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除问题", notes = "根据id删除问题")
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
