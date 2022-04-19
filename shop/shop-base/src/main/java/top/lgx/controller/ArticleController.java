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
import top.lgx.entity.Article;
import top.lgx.service.ArticleService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 09:44
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
@Api(value = "文章接口", tags = "文章接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    @ApiOperation(value = "查询所有文章", notes = "查询所有文章")
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", articleService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询文章", notes = "根据id查询文章")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK, "查询成功", articleService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增文章", notes = "新增文章")
    public Result insert(@RequestBody Article article){
        articleService.insert(article);
        return new Result(true, StatusCode.OK, "插入成功");
    }

    @PostMapping("/search")
    @ApiOperation(value = "模糊查询", notes = "模糊查询")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK, "查询成功", articleService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result findSearch(@PathVariable int page, @PathVariable int size, @RequestBody Map map){
        Page<Article> articles = articleService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(articles.getTotalElements(), articles.getContent()));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改文章", notes = "修改文章")
    public Result update(@PathVariable String id, @RequestBody Article article){
        article.setId(id);
        articleService.update(article);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @PutMapping("/examine/{id}")
    public Result examine(@PathVariable String id){
        articleService.examine(id);
        return new Result(true, StatusCode.OK, "审核成功");
    }

    @PutMapping("/thumbup/{id}")
    public Result updateThumbup(@PathVariable String id){
        articleService.updateThumbup(id);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文章", notes = "删除文章")
    public Result deleteById(@PathVariable String id){
        articleService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
