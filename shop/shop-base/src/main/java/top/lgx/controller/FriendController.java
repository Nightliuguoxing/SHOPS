package top.lgx.controller;

import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lgx.service.FriendService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 16:41
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/friend")
@Api(value = "好友接口", tags = "好友接口")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @PutMapping("/like/{friendId}/{type}")
    @ApiOperation(value = "更新好友状态", notes = "更新好友状态")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "请先登录");
        }
        if (type.equals("1")) {
            if (friendService.addFriend(claims.getId(), friendId) == 0) {
                return new Result(false, StatusCode.ERROR, "已经添加为好友");
            }
        }else {
            friendService.addNoFriend(claims.getId(), friendId);
        }
        return new Result(true, StatusCode.OK, "添加好友成功");
    }

    @DeleteMapping("/{friendId}")
    @ApiOperation(value = "删除好友", notes = "删除好友")
    public Result remove(@PathVariable String friendId) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "请先登录");
        }
        friendService.deleteFriend(claims.getId(), friendId);
        return new Result(true, StatusCode.OK, "删除好友成功");
    }
}
