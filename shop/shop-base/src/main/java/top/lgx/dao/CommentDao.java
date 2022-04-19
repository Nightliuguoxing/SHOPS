package top.lgx.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.lgx.entity.Comment;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022/4/19 15:24
 * @Description:
 */
@Repository
public interface CommentDao extends MongoRepository<Comment, String> {

    public List<Comment> findByArticleId(String articleId);
}
