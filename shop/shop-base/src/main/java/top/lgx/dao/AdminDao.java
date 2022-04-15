package top.lgx.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Admin;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/14 19:19
 * @Description:
 */
@Repository
public interface AdminDao extends BaseDao<Admin, String>{

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:29
     * @Params: username
     * @Return: top.lgx.entity.Admin
     * @Description: 根据用户名查询管理员
     */
    @Query(value = "SELECT admin FROM Admin admin WHERE admin.username = ?1")
    public Admin findByUsername(String username);
}
