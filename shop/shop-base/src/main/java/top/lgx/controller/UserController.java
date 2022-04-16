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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lgx.entity.User;
import top.lgx.service.UserService;
import top.lgx.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-14 10:49
 * @Description:
 */
@CrossOrigin
@RestController
@Api(value = "用户接口", tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:05
     * @Params:
     * @Return: entity.Result
     * @Description: 查询全部用户
     */
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:05
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询用户
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }

    @GetMapping("/info")
    public Result info(){
        String token = request.getHeader("token");
        String id = jwtUtil.getId(token);
        User user = userService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 23:29
     * @Params: tel
     * @Params: password
     * @Return: entity.Result
     * @Description: 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        User user = userService.findUserByTelAndPassword(loginMap.get("username"), loginMap.get("password"));
        if (user != null) {
            String token = jwtUtil.createJwt(user.getId(), user.getUsername(), "user");
            Map map = new HashMap();
            map.put("token", token);
            map.put("name", user.getUsername());
            map.put("avatar", user.getAvatar());
            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:05
     * @Params: user
     * @Return: entity.Result
     * @Description: 用户注册
     */
    @PostMapping("/register/{code}")
    public Result register(@RequestBody User user, @PathVariable String code){
        userService.register(user, code);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:05
     * @Params: mobile
     * @Return: entity.Result
     * @Description: 发送短信验证码
     */
    @PostMapping("/sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:06
     * @Params: user
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id修改用户
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody User user){
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 11:06
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除用户
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}