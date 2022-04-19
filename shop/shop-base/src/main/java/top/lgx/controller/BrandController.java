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
import top.lgx.entity.Brand;
import top.lgx.service.BrandService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 20:43
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/brand")
@Api(value = "品牌接口", tags = "品牌接口")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:04
     * @Return: entity.Result
     * @Description: 查询所有品牌
     */
    @GetMapping
    @ApiOperation(value = "查询所有品牌")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查找成功", brandService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:04
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询品牌
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询品牌")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查找成功", brandService.findById(id));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:04
     * @Params: brand
     * @Return: entity.Result
     * @Description: 添加品牌
     */
    @PostMapping
    @ApiOperation(value = "添加品牌")
    public Result insert(@RequestBody Brand brand){
        brandService.insert(brand);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:04
     * @Params: map
     * @Return: entity.Result
     * @Description: 根据条件查询品牌
     */
    @PostMapping("/search")
    @ApiOperation(value = "根据条件查询品牌")
    public Result search(@RequestBody Map map){
        return new Result(true, StatusCode.OK,"查找成功", brandService.findSearch(map));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:05
     * @Params: page
     * @Params: size
     * @Params: map
     * @Return: entity.Result
     * @Description: 分页查询品牌
     */
    @ApiOperation(value = "分页查询品牌")
    @PostMapping("/search/{page}/{size}")
    public Result search(@PathVariable int page, @PathVariable int size, @RequestBody Map map){
        Page<Brand> pageList = brandService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK,"查找成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:05
     * @Params: brand
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id修改品牌
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改品牌")
    public Result update(@RequestBody Brand brand, @PathVariable String id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:05
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除品牌
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除品牌")
    public Result deleteById(@PathVariable String id){
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

}
