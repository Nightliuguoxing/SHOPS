package top.lgx.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 15:22
 * @Description:
 */
@ApiModel(value = "Comment", description = "评论实体类")
public class Comment implements Serializable {

    private static final long serialVersionUID = 4638802495606630625L;

    @Id
    private String _id;
    private String articleId;
    private String content;
    private String userId;
    private String parentId;
    private Date publishTime;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
