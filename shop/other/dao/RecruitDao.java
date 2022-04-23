package top.lgx.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Recruit;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/19 9:14
 * @Description:
 */
@Repository
public interface RecruitDao extends BaseDao<Recruit, String> {

    @Query(value = "SELECT r FROM Recruit r WHERE r.state = ?1 ORDER BY r.createTime DESC")
    public List<Recruit> findTop4ByStateOrderByCreateTimeDesc(String state);

    @Query(value = "SELECT r FROM Recruit r WHERE r.state = ?1 ORDER BY r.createTime DESC")
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
