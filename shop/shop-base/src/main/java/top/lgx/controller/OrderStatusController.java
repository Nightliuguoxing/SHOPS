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
import top.lgx.entity.OrderStatus;
import top.lgx.service.OrderStatusService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-20 08:28
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/order/status")
@Api(value = "订单状态接口", tags = "订单状态接口")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", orderStatusService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", orderStatusService.findById(id));
    }

    @PostMapping
    public Result insert(@RequestBody OrderStatus orderStatus) {
        orderStatusService.insert(orderStatus);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK, "查询成功", orderStatusService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map map, @PathVariable int page, @PathVariable int size){
        Page<OrderStatus> orderStatusPage = orderStatusService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(orderStatusPage.getTotalElements(), orderStatusPage.getContent()));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody OrderStatus orderStatus) {
        orderStatus.setOrderId(id);
        orderStatusService.update(orderStatus);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        orderStatusService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
