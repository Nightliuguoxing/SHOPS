package top.lgx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Friend;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 16:30
 * @Description:
 */
@Repository
public interface FriendDao extends BaseDao<Friend, String> {


    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 16:34
     * @Params: userId
     * @Params: friendId
     * @Return: int
     * @Description: 根据用户ID与被关注用户ID查询记录个数
     */
    @Query(value = "SELECT count(f) FROM Friend f WHERE f.userId = :userId AND f.friendId = :friendId")
    public int selectCount(String userId, String friendId);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 16:36
     * @Params: userId
     * @Params: friendId
     * @Params: like
     * @Return: void
     * @Description: 更新为互相喜欢
     */
    @Modifying
    @Query(value = "UPDATE Friend f SET f.isLike = :like WHERE f.userId = :userId AND f.friendId = :friendId")
    public void updateLike(String userId, String friendId, String like);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 16:54
     * @Params: userId
     * @Params: friendId
     * @Return: void
     * @Description: 删除喜欢
     */
    @Modifying
    @Query(value = "DELETE FROM Friend f WHERE f.userId = :userId AND f.friendId = :friendId")
    public void deleteFriend(String userId, String friendId);
}
