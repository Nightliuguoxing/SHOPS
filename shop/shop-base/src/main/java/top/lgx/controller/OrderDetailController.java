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
import top.lgx.entity.OrderDetail;
import top.lgx.service.OrderDetailService;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-20 08:56
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/order/detail")
@Api(value = "订单详情接口", tags = "订单详情接口")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK ,"查询成功", orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK ,"查询成功", orderDetailService.findById(id));
    }

    @PostMapping
    public Result insert(@RequestBody OrderDetail orderDetail){
        orderDetailService.insert(orderDetail);
        return new Result(true, StatusCode.OK ,"添加成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map map){
        return new Result(true, StatusCode.OK ,"查询成功", orderDetailService.findSearch(map));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map map,@PathVariable int page,@PathVariable int size){
        Page<OrderDetail> orderDetails = orderDetailService.findSearch(map, page, size);
        return new Result(true, StatusCode.OK ,"查询成功", new PageResult<>(orderDetails.getTotalElements(), orderDetails.getContent()));
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody OrderDetail orderDetail,@PathVariable String id){
        orderDetail.setId(id);
        orderDetailService.update(orderDetail);
        return new Result(true, StatusCode.OK ,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        orderDetailService.deleteById(id);
        return new Result(true, StatusCode.OK ,"删除成功");
    }
}
