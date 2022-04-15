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
import top.lgx.entity.Admin;
import top.lgx.service.AdminService;
import top.lgx.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-14 19:32
 * @Description:
 */
@CrossOrigin
@RestController
@Api(value = "管理员接口", tags = "管理员接口")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpServletRequest request;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params:
     * @Return: entity.Result
     * @Description: 查询所有管理员
     */
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", adminService.findAll());
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id查询管理员
     */
    @GetMapping("/{id}")
    public Result findById(String id){
        return new Result(true, StatusCode.OK, "查询成功", adminService.findById(id));
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params: admin
     * @Return: entity.Result
     * @Description: 添加管理员
     */
    @PostMapping
    public Result insert(@RequestBody Admin admin){
        adminService.insert(admin);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params: loginMap
     * @Return: entity.Result
     * @Description: 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginMap){
        Admin admin = adminService.findByNameAndPassword(loginMap.get("username"), loginMap.get("password"));
        if (admin != null){
            String token = jwtUtil.createJwt(admin.getId(), admin.getUsername(), "admin");
            Map map = new HashMap();
            map.put("token", token);
            map.put("name", admin.getUsername());
            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params: admin
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id修改管理员
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Admin admin, @PathVariable String id){
        admin.setId(id);
        adminService.update(admin);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:38
     * @Params: id
     * @Return: entity.Result
     * @Description: 根据id删除管理员
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        adminService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
