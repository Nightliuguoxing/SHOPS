package top.lgx.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Enterprise;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/19 9:13
 * @Description:
 */
@Repository
public interface EnterpriseDao extends BaseDao<Enterprise, String>{

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 09:17
     * @Params: isHot
     * @Return: java.util.List<top.lgx.entity.Enterprise>
     * @Description: 根据热门状态获取企业列表
     */
    @Query(value = "SELECT e From Enterprise e WHERE e.isHot = ?1")
    public List<Enterprise> findByIsHot(String isHot);
}
