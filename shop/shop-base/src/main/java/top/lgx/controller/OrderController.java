package top.lgx.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
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
import top.lgx.entity.Order;
import top.lgx.service.OrderService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 23:05
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
@Api(value = "订单接口", tags = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", orderService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK, "查询成功", orderService.findById(id));
    }

    @PostMapping
    public Result insert(@RequestBody Order order){
        orderService.insert(order);
        return new Result(true, StatusCode.OK, "插入成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK, "查询成功", orderService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearchPage(@RequestBody Map map, @PathVariable int page, @PathVariable int size){
        Page<Order> orderList = orderService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(orderList.getTotalElements(), orderList.getContent()));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody Order order){
        order.setOrderId(id);
        orderService.update(order);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        orderService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
