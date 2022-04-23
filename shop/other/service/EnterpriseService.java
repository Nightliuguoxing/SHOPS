package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.EnterpriseDao;
import top.lgx.entity.Enterprise;
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
 * @Date: 2022-04-19 09:32
 * @Description:
 */
@Service
public class EnterpriseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private EnterpriseDao enterpriseDao;

    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    public List<Enterprise> findByIsHot(){
        return enterpriseDao.findByIsHot("1");
    }

    public Enterprise findById(String id){
        return enterpriseDao.findById(id).orElse(null);
    }

    public void insert(Enterprise enterprise){
        enterprise.setId(idWorker.nextId()+"");
        enterpriseDao.save(enterprise);
    }

    public void update(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    public List<Enterprise> findSearch(Map searchMap) {
        Specification<Enterprise> specification = createSpecification(searchMap);
        return enterpriseDao.findAll(specification);
    }

    public Page<Enterprise> findSearch(Map searchMap, int page, int size) {
        Specification<Enterprise> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseDao.findAll(specification, pageRequest);
    }

    private Specification<Enterprise> createSpecification(Map searchMap) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("name") != null &&
                        !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(
                            root.get("name").as(String.class), "%" +
                                    (String) searchMap.get("name") + "%"));
                }
                if (searchMap.get("summary") != null &&
                        !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(
                            root.get("summary").as(String.class), "%" +
                                    (String) searchMap.get("summary") + "%"));
                }
                if (searchMap.get("labels") != null &&
                        !"".equals(searchMap.get("labels"))) {
                    predicateList.add(cb.like(
                            root.get("labels").as(String.class), "%" +
                                    (String) searchMap.get("labels") + "%"));
                }
                if (searchMap.get("isHot") != null &&
                        !"".equals(searchMap.get("isHot"))) {
                    predicateList.add(cb.like(
                            root.get("isHot").as(String.class), "%" +
                                    (String) searchMap.get("isHot") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
