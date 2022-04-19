package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import top.lgx.dao.SpitDao;
import top.lgx.entity.Spit;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 17:06
 * @Description: FIXME: Rewrit visits and shares
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:09
     * @Params:
     * @Return: java.util.List<top.lgx.entity.Spit>
     * @Description: 查询全部数据
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:10
     * @Params: id
     * @Return: top.lgx.entity.Spit
     * @Description: 根据id查询
     */
    public Spit findById(String id) {
        return spitDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:19
     * @Params: parentId
     * @Params: page
     * @Params: size
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Spit>
     * @Description: 根据上级ID查询吐槽分页数据
     */
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return spitDao.findByParentId(parentId, request);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:10
     * @Params: spit
     * @Return: void
     * @Description: 新增吐槽
     */
    public void insert(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishTime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        if (spit.getParentId() == null || "".equals(spit.getParentId())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentId()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:11
     * @Params: spit
     * @Return: void
     * @Description: 更新吐槽
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:30
     * @Params: id
     * @Return: void
     * @Description: 点赞
     */
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 15:16
     * @Params: id
     * @Return: void
     * @Description: 浏览量
     */
    public void updateVisits(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visits", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 15:16
     * @Params: id
     * @Return: void
     * @Description: 分享量
     */
    public void updateShare(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("share", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 17:11
     * @Params: id
     * @Return: void
     * @Description: 删除吐槽
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }
}
