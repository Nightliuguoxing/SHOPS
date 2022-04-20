package top.lgx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 22:22
 * @Description:
 */
@Entity
@Table(name = "tb_order")
@ApiModel(value = "Order", description = "订单实体类")
public class Order {

    @Id
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "支付总额(分)")
    private String totalPay;

    @ApiModelProperty(value = "实际支付(分)")
    private String actualPay;

    @ApiModelProperty(value = "促销ID")
    private String promotionIds;

    @ApiModelProperty(value = "支付方式 1.在线 2.货到付款")
    private String paymentType;

    @ApiModelProperty(value = "邮费(分)")
    private String postFree;

    @ApiModelProperty(value = "订单建立时间")
    private Date createTime;

    @ApiModelProperty(value = "物流名称")
    private String shippingName;

    @ApiModelProperty(value = "物流单号")
    private String shippingCode;

    @ApiModelProperty(value = "买家ID")
    private String userId;

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;

    @ApiModelProperty(value = "买家昵称")
    private String buyerNick;

    @ApiModelProperty(value = "买家是否已经评价")
    private String buyerRate;

    @ApiModelProperty(value = "收货地址(省)")
    private String receiverState;

    @ApiModelProperty(value = "收货地址(市)")
    private String receiverCity;

    @ApiModelProperty(value = "收货地址(区)")
    private String receiverDistrict;

    @ApiModelProperty(value = "收货地址(详细地址)")
    private String receiverAddress;

    @ApiModelProperty(value = "收货人手机")
    private String receiverMobile;

    @ApiModelProperty(value = "收货人邮箱")
    private String receiverZip;

    @ApiModelProperty(value = "收货人")
    private String receiver;

    @ApiModelProperty(value = "发票类型(1：普通发票，2：增值税发票， 3：电子发票， 0：无发票)")
    private Integer invoiceType;

    @ApiModelProperty(value = "订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端")
    private Integer sourceType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getActualPay() {
        return actualPay;
    }

    public void setActualPay(String actualPay) {
        this.actualPay = actualPay;
    }

    public String getPromotionIds() {
        return promotionIds;
    }

    public void setPromotionIds(String promotionIds) {
        this.promotionIds = promotionIds;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFree() {
        return postFree;
    }

    public void setPostFree(String postFree) {
        this.postFree = postFree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
}
