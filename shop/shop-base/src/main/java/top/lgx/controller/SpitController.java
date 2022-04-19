package top.lgx.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lgx.entity.Spit;
import top.lgx.service.SpitService;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 17:12
 * @Description:
 */
@CrossOrigin
@RestController
@Api(value = "吐槽接口", tags = "吐槽接口")
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:36
     * @Params:
     * @Return: entity.Result
     * @Description: 查询所有
     */
    @GetMapping
    @ApiOperation(value = "查询所有吐槽")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功", spitService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:36
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询吐槽")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功", spitService.findById(id));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:36
     * @Params: parentId
     * @Params: page
     * @Params: size
     * @Return: entity.Result
     * @Description: 根据父id查询
     */
    @ApiOperation(value = "根据父id查询吐槽")
    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageList = spitService.findByParentId(parentId, page, size);
        return new Result(true, StatusCode.OK,"查询成功", new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:36
     * @Params: spit
     * @Return: entity.Result
     * @Description: 添加吐槽
     */
    @PostMapping
    @ApiOperation(value = "添加吐槽")
    public Result insert(@RequestBody Spit spit){
        spitService.insert(spit);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:36
     * @Params: id
     * @Params: spit
     * @Return: entity.Result
     * @Description: 根据id修改
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改吐槽")
    public Result update(@PathVariable String id, @RequestBody Spit spit){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:35
     * @Params: id
     * @Return: entity.Result
     * @Description: 点赞成功
     */
    @PutMapping("/thumbup/{id}")
    @ApiOperation(value = "点赞成功")
    public Result updateThumbup(@PathVariable String id){
        // TODO userId 需要修改为当前登陆的用户
        String userId = "1";
        if (redisTemplate.opsForValue().get("thumbup_" + id + "_" + userId) != null){
            return new Result(false, StatusCode.REPERROR,"您已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + id + "_" + userId, "1");
        return new Result(true, StatusCode.OK,"点赞成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 15:19
     * @Params: id
     * @Return: entity.Result
     * @Description: 浏览量+1
     */
    @PutMapping("/visits/{id}")
    @ApiOperation(value = "点击量")
    public Result updateVisits(@PathVariable String id){
        String userId = "1";
        if (redisTemplate.opsForValue().get("visits_" + id + "_" + userId) != null){
            return new Result(false, StatusCode.REPERROR,"您已经浏览过了");
        }
        spitService.updateVisits(id);
        redisTemplate.opsForValue().set("visits_" + id + "_" + userId, "1");
        return new Result(true, StatusCode.OK,"浏览成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 15:20
     * @Params: id
     * @Return: entity.Result
     * @Description: 分享量+1
     */
    @PutMapping("/share/{id}")
    @ApiOperation(value = "分享量")
    public Result updateShare(@PathVariable String id){
        String userId = "1";
        if (redisTemplate.opsForValue().get("share_" + id + "_" + userId) != null){
            return new Result(false, StatusCode.REPERROR,"您已经分享过了");
        }
        spitService.updateShare(id);
        redisTemplate.opsForValue().set("share_" + id + "_" + userId, "1");
        return new Result(true, StatusCode.OK,"分享成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:37
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除")
    public Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}


