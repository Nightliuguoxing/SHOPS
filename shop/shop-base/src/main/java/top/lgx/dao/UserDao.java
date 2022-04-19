package top.lgx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.User;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/14 10:34
 * @Description:
 */
@Repository
public interface UserDao extends BaseDao<User, String> {

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 23:24
     * @Params: tel
     * @Return: top.lgx.entity.User
     * @Description: 根据手机号查询用户
     */
    @Query(value = "SELECT user FROM User user WHERE user.tel = :tel")
    public User findByTel(String tel);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 17:00
     * @Params: id
     * @Params: x
     * @Return: void
     * @Description: 更新粉丝数
     */
    @Modifying
    @Query(value = "UPDATE User u SET u.fansCount = u.fansCount + :x WHERE u.id = :id")
    public void incFansCount(String id, int x);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 17:04
     * @Params: id
     * @Params: x
     * @Return: void
     * @Description: 更新关注数
     */
    @Modifying
    @Query(value = "UPDATE User u SET u.followCount = u.followCount + :x WHERE u.id = :id")
    public void incFollowCount(String id, int x);
}
