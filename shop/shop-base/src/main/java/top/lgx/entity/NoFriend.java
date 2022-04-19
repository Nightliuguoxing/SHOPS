package top.lgx.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 16:46
 * @Description:
 */
@Entity
@Table(name = "tb_no_friend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {

    @Id
    private String userId;

    @Id
    private String friendId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
