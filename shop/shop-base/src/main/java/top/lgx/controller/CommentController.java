package top.lgx.controller;

import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lgx.entity.Comment;
import top.lgx.service.CommentService;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 15:26
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
@Api(value = "评论接口", tags = "评论接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{articleId}")
    public Result findByArticleId(@PathVariable String articleId) {
        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleId(articleId));
    }

    @PostMapping
    public Result insert(@RequestBody Comment comment) {
        commentService.insert(comment);
        return new Result(true, StatusCode.OK, "提交成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
