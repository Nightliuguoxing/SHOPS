package top.lgx.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.lgx.dao.ArticleDao;
import top.lgx.entity.Article;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 09:21
 * @Description:
 */
@Service
public class ArticleService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Article> findAll() {
        return articleDao.findAll();
    }

    public Article findById(String id) {
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if (article == null) {
            article = articleDao.findById(id).orElse(null);
            redisTemplate.opsForValue().set("article_" + id, article, 1, TimeUnit.DAYS);
        }
        return article;
    }

    public void insert(Article article) {
        article.setId(idWorker.nextId() + "");
        article.setUserId("1");

        article.setVisits(0);
        article.setThumbup(0);
        article.setComment(0);

        articleDao.save(article);
    }

    public void update(Article article) {
        redisTemplate.delete("article_" + article.getId());
        articleDao.save(article);
    }

    @Transactional(rollbackOn = Exception.class)
    public void examine(String id) {
        articleDao.examine(id);
    }

    // TODO HAVE SOME PROBLEM
    @Transactional(rollbackOn = Exception.class)
    public int updateThumbup(String id) {
        return articleDao.updateThumbup(id);
    }

    public void deleteById(String id) {
        redisTemplate.delete("article_" + id);
        articleDao.deleteById(id);
    }

    public List<Article> findSearch(Map searchMap) {
        Specification<Article> specification = createSpecification(searchMap);
        return articleDao.findAll(specification);
    }

    public Page<Article> findSearch(Map searchMap, int page, int size) {
        Specification<Article> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleDao.findAll(specification, pageRequest);
    }

    private Specification<Article> createSpecification(Map searchMap) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("title") != null &&
                        !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(
                            root.get("title").as(String.class), "%" +
                                    (String) searchMap.get("title") + "%"));
                }
                if (searchMap.get("isPublic") != null &&
                        !"".equals(searchMap.get("isPublic"))) {
                    predicateList.add(cb.like(
                            root.get("isPublic").as(String.class), "%" +
                                    (String) searchMap.get("isPublic") + "%"));
                }
                if (searchMap.get("isHot") != null &&
                        !"".equals(searchMap.get("isHot"))) {
                    predicateList.add(cb.like(
                            root.get("isHot").as(String.class), "%" +
                                    (String) searchMap.get("isHot") + "%"));
                }
                if (searchMap.get("createTime") != null &&
                        !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(
                            root.get("createTime").as(String.class), "%" +
                                    (String) searchMap.get("createTime") + "%"));
                }
                if (searchMap.get("content") != null &&
                        !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(
                            root.get("content").as(String.class), "%" +
                                    (String) searchMap.get("content") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
