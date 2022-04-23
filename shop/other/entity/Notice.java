package top.lgx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 20:33
 * @Description:
 */
@ApiModel(value = "Notice", description = "通知公告实体类")
public class Notice implements Serializable {

    private static final long serialVersionUID = -3204471228685395451L;

    @Id
    @ApiModelProperty(value = "通知ID")
    private String id;

    @ApiModelProperty(value = "接收消息的用户ID" )
    private String receiverId;

    @ApiModelProperty(value = "进行操作的用户ID")
    private String operatorId;

    @ApiModelProperty(value = "进行操作的用户昵称")
    private String operatorName;

    @ApiModelProperty(value = "操作类型（评论，点赞等）")
    private String action;

    @ApiModelProperty(value = "对象类型（评论，点赞等）")
    private String targetType;

    @ApiModelProperty(value = "对象名称或简介")
    private String targetName;

    @ApiModelProperty(value = "对象ID")
    private String targetId;

    @ApiModelProperty(value = "创建日期")
    private Date createtime;

    @ApiModelProperty(value = "消息类型 sys系统消息  user用户消息")
    private String type;

    @ApiModelProperty(value = "消息状态（0 未读，1 已读）")
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
