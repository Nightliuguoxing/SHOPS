package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.RecruitDao;
import top.lgx.entity.Recruit;
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
 * @Date: 2022-04-19 09:37
 * @Description:
 */
@Service
public class RecruitService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RecruitDao recruitDao;

    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }

    public Recruit findById(String id){
        return recruitDao.findById(id).orElse(null);
    }

    public List<Recruit> findTop4ByStateOrderByCreateTimeDesc(String state) {
        return recruitDao.findTop4ByStateOrderByCreateTimeDesc(state);
    }

    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state) {
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }

    public void insert(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        recruitDao.save(recruit);
    }

    public void update(Recruit recruit){
        recruitDao.save(recruit);
    }

    public void deleteById(String id){
        recruitDao.deleteById(id);
    }

    public List<Recruit> findSearch(Map searchMap) {
        Specification<Recruit> specification = createSpecification(searchMap);
        return recruitDao.findAll(specification);
    }

    public Page<Recruit> findSearch(Map searchMap, int page, int size) {
        Specification<Recruit> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return recruitDao.findAll(specification, pageRequest);
    }

    private Specification<Recruit> createSpecification(Map searchMap) {
        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("jobName") != null &&
                        !"".equals(searchMap.get("jobName"))) {
                    predicateList.add(cb.like(
                            root.get("jobName").as(String.class), "%" +
                                    (String) searchMap.get("jobName") + "%"));
                }
                if (searchMap.get("salary") != null &&
                        !"".equals(searchMap.get("salary"))) {
                    predicateList.add(cb.like(
                            root.get("salary").as(String.class), "%" +
                                    (String) searchMap.get("salary") + "%"));
                }
                if (searchMap.get("condition") != null &&
                        !"".equals(searchMap.get("condition"))) {
                    predicateList.add(cb.like(
                            root.get("condition").as(String.class), "%" +
                                    (String) searchMap.get("condition") + "%"));
                }
                if (searchMap.get("education") != null &&
                        !"".equals(searchMap.get("education"))) {
                    predicateList.add(cb.like(
                            root.get("education").as(String.class), "%" +
                                    (String) searchMap.get("education") + "%"));
                }
                if (searchMap.get("createTime") != null &&
                        !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(
                            root.get("createTime").as(String.class), "%" +
                                    (String) searchMap.get("createTime") + "%"));
                }
                if (searchMap.get("state") != null &&
                        !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(
                            root.get("state").as(String.class), "%" +
                                    (String) searchMap.get("state") + "%"));
                }if (searchMap.get("label") != null &&
                        !"".equals(searchMap.get("label"))) {
                    predicateList.add(cb.like(
                            root.get("label").as(String.class), "%" +
                                    (String) searchMap.get("label") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
