package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lgx.dao.CommentDao;
import top.lgx.entity.Comment;
import utils.IdWorker;

import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 15:25
 * @Description:
 */
@Service
public class CommentService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CommentDao commentDao;

    public List<Comment> findByArticleId(String articleId) {
        return commentDao.findByArticleId(articleId);
    }

    public void insert(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        commentDao.save(comment);
    }

    public void deleteById(String id) {
        commentDao.deleteById(id);
    }

}
