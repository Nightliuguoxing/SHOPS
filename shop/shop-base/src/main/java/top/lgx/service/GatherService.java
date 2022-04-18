package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.GatherDao;
import top.lgx.entity.Gather;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 15:59
 * @Description:
 */
@Service
public class GatherService {

    // Cacheable 使用这个注解的方法在执行后会缓存其返回结果
    // CacheEvict 使用这个注解的方法在其执行前或执行后移除Spring Cache中的某些元素

    @Autowired
    private GatherDao gatherDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 16:04
     * @Params:
     * @Return: java.util.List<top.lgx.entity.Gather>
     * @Description: 查询所有
     */
    public List<Gather> findAll() {
        return gatherDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 16:05
     * @Params: id
     * @Return: top.lgx.entity.Gather
     * @Description: 根据id查询
     */
    @Cacheable(value = "gather", key = "#id")
    public Gather findById(String id) {
        return gatherDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 16:05
     * @Params: gather
     * @Return: void
     * @Description: 添加活动
     */
    public void insert(Gather gather) {
        gather.setId(idWorker.nextId() + "");
        gatherDao.save(gather);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 16:06
     * @Params: gather
     * @Return: void
     * @Description: 修改活动
     */
    @CacheEvict(value = "gather", key = "#gather.id")
    public void update(Gather gather) {
        gatherDao.save(gather);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 16:06
     * @Params: id
     * @Return: void
     * @Description: 删除活动
     */
    @CacheEvict(value = "gather", key = "#id")
    public void deleteById(String id) {
        gatherDao.deleteById(id);
    }

    public List<Gather> findSearch(Map searchMap) {
        Specification<Gather> specification = createSpecification(searchMap);
        return gatherDao.findAll(specification);
    }

    public Page<Gather> findSearch(Map searchMap, int page, int size) {
        Specification<Gather> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return gatherDao.findAll(specification, pageRequest);
    }

    private Specification<Gather> createSpecification(Map searchMap) {
        return new Specification<Gather>() {
            @Override
            public Predicate toPredicate(Root<Gather> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("name") != null &&
                        !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(
                            root.get("name").as(String.class), "%" +
                                    (String) searchMap.get("name") + "%"));
                }
                if (searchMap.get("detail") != null &&
                        !"".equals(searchMap.get("detail"))) {
                    predicateList.add(cb.like(
                            root.get("detail").as(String.class), "%" +
                                    (String) searchMap.get("detail") + "%"));
                }
                if (searchMap.get("sponsor") != null &&
                        !"".equals(searchMap.get("sponsor"))) {
                    predicateList.add(cb.like(
                            root.get("sponsor").as(String.class), "%" +
                                    (String) searchMap.get("sponsor") + "%"));
                }
                if (searchMap.get("createTime") != null &&
                        !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(
                            root.get("createTime").as(String.class), "%" +
                                    (String) searchMap.get("createTime") + "%"));
                }
                if (searchMap.get("endTime") != null &&
                        !"".equals(searchMap.get("endTime"))) {
                    predicateList.add(cb.like(
                            root.get("endTime").as(String.class), "%" +
                                    (String) searchMap.get("endTime") + "%"));
                }
                if (searchMap.get("enrollTime") != null &&
                        !"".equals(searchMap.get("enrollTime"))) {
                    predicateList.add(cb.like(
                            root.get("enrollTime").as(String.class), "%" +
                                    (String) searchMap.get("enrollTime") + "%"));
                }
                if (searchMap.get("status") != null &&
                        !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(
                            root.get("status").as(String.class), "%" +
                                    (String) searchMap.get("status") + "%"));
                }
                if (searchMap.get("city") != null &&
                        !"".equals(searchMap.get("city"))) {
                    predicateList.add(cb.like(
                            root.get("city").as(String.class), "%" +
                                    (String) searchMap.get("city") + "%"));
                }
                return cb.and(predicateList.toArray(new
                        Predicate[predicateList.size()]));
            }
        };
    }
}
