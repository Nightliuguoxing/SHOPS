package top.lgx.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Problem;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 09:53
 * @Description:
 */
@Repository
public interface ProblemDao extends BaseDao<Problem, String> {

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:23
     * @Params: labelId 标签id
     * @Params: pageable 分页
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签id查询最新问题
     */
    @Query(value = "select p from Problem p where p.id in( select pl.problemId from PAL pl where pl.labelId=?1 ) order by p.replyTime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);


    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:45
     * @Params: labelId
     * @Params: pageable
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签id查询热门问题
     */
    @Query(value = "select p from Problem p where p.id in( select pl.problemId from PAL pl where pl.labelId=?1 ) order by p.reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);


    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:49
     * @Params: labelId
     * @Params: pageable
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签ID查询等待回答列表
     */
    @Query(value = "select p from Problem p where p.id in( select pl.problemId from PAL pl where pl.labelId=?1 ) and p.reply = 0 order by p.createTime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
