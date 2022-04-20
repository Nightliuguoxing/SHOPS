package top.lgx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-20 08:12
 * @Description:
 */
@Entity
@Table(name = "tb_order_status")
@ApiModel(value = "Order Status", description = "订单状态")
public class OrderStatus {

    @Id
    @ApiModelProperty(value = "订单状态id")
    private String orderId;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "付款时间")
    private Date paymentTime;

    @ApiModelProperty(value = "发货时间")
    private Date consignTime;

    @ApiModelProperty(value = "交易完成时间")
    private Date endTime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;

    @ApiModelProperty(value = "评价时间")
    private Date commentTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
