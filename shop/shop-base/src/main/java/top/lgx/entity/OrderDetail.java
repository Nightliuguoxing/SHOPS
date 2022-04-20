package top.lgx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-20 08:38
 * @Description:
 */
@Entity
@Table(name = "tb_order_detail")
@ApiModel(value = "Order Detail", description = "订单详情实体类")
public class OrderDetail {

    @Id
    @ApiModelProperty(value = "订单详情ID")
    private String id;

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "购买数量")
    private Integer num;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品单价")
    private String price;

    @ApiModelProperty(value = "商品图片")
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
