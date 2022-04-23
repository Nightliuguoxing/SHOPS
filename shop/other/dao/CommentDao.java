package top.lgx.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Comment;

import java.util.Date;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/19 15:24
 * @Description:
 */
@Repository
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 20:41
     * @Params: articleId
     * @Return: java.util.List<top.lgx.entity.Comment>
     * @Description: 根据文章id查询文章评论数据
     */
    public List<Comment> findByArticleId(String articleId);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 20:41
     * @Params: date
     * @Params: thumbup
     * @Return: java.util.List<top.lgx.entity.Comment>
     * @Description: 根据发布时间和点赞数查询查询
     */
    public List<Comment> findByPublishTimeAndThumbup(Date date, Integer thumbup);

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 20:42
     * @Params: userid
     * @Return: java.util.List<top.lgx.entity.Comment>
     * @Description: 根据用户id查询，并且根据发布时间倒序排序
     */
    public List<Comment> findByUserIdOrderByPublishTimeDesc(String userid);
}
