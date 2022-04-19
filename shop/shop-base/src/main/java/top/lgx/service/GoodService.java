package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.GoodDao;
import top.lgx.entity.Good;
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
 * @Date: 2022-04-19 21:41
 * @Description:
 */
@Service
public class GoodService {

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private IdWorker idWorker;

    public List<Good> findAll() {
        return goodDao.findAll();
    }

    public Good findById(String id) {
        return goodDao.findById(id).orElse(null);
    }

    public void insert(Good good) {
        good.setId(idWorker.nextId() + "");
        goodDao.save(good);
    }

    public void update(Good good) {
        goodDao.save(good);
    }

    public void deleteById(String id) {
        goodDao.deleteById(id);
    }

    public List<Good> findSearch(Map searchMap) {
        Specification<Good> specification = createSpecification(searchMap);
        return goodDao.findAll(specification);
    }

    public Page<Good> findSearch(Map searchMap, int page, int size) {
        Specification<Good> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return goodDao.findAll(specification, pageRequest);
    }

    private Specification<Good> createSpecification(Map searchMap) {
        return new Specification<Good>() {
            @Override
            public Predicate toPredicate(Root<Good> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("name") != null &&
                        !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(
                            root.get("name").as(String.class), "%" +
                                    (String) searchMap.get("name") + "%"));
                }
                if (searchMap.get("descp") != null &&
                        !"".equals(searchMap.get("descp"))) {
                    predicateList.add(cb.like(
                            root.get("descp").as(String.class), "%" +
                                    (String) searchMap.get("descp") + "%"));
                }
                if (searchMap.get("status") != null &&
                        !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(
                            root.get("status").as(String.class), "%" +
                                    (String) searchMap.get("status") + "%"));
                }
                if (searchMap.get("createTime") != null &&
                        !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(
                            root.get("createTime").as(String.class), "%" +
                                    (String) searchMap.get("createTime") + "%"));
                }
                if (searchMap.get("upTime") != null &&
                        !"".equals(searchMap.get("upTime"))) {
                    predicateList.add(cb.like(
                            root.get("upTime").as(String.class), "%" +
                                    (String) searchMap.get("upTime") + "%"));
                }
                if (searchMap.get("price") != null &&
                        !"".equals(searchMap.get("price"))) {
                    predicateList.add(cb.like(
                            root.get("price").as(String.class), "%" +
                                    (String) searchMap.get("price") + "%"));
                }
                if (searchMap.get("brandId") != null &&
                        !"".equals(searchMap.get("brandId"))) {
                    predicateList.add(cb.like(
                            root.get("brandId").as(String.class), "%" +
                                    (String) searchMap.get("brandId") + "%"));
                }
                if (searchMap.get("categoryId") != null &&
                        !"".equals(searchMap.get("categoryId"))) {
                    predicateList.add(cb.like(
                            root.get("categoryId").as(String.class), "%" +
                                    (String) searchMap.get("categoryId") + "%"));
                }
                if (searchMap.get("userId") != null &&
                        !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(
                            root.get("userId").as(String.class), "%" +
                                    (String) searchMap.get("userId") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
