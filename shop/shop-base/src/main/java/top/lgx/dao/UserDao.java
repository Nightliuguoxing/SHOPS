package top.lgx.dao;

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
    @Query(value = "SELECT user FROM User user WHERE user.tel = ?1")
    public User findByTel(String tel);
}
