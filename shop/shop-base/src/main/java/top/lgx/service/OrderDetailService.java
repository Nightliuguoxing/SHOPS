package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.OrderDetailDao;
import top.lgx.entity.OrderDetail;
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
 * @Date: 2022-04-20 08:46
 * @Description:
 */
@Service
public class OrderDetailService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public List<OrderDetail> findAll(){
        return orderDetailDao.findAll();
    }

    public OrderDetail findById(String id){
        return orderDetailDao.findById(id).orElse(null);
    }

    public void insert(OrderDetail orderDetail){
        orderDetail.setId(idWorker.nextId()+"");
        orderDetailDao.save(orderDetail);
    }

    public void update(OrderDetail orderDetail){
        orderDetailDao.save(orderDetail);
    }

    public void deleteById(String id){
        orderDetailDao.deleteById(id);
    }

    public List<OrderDetail> findSearch(Map searchMap) {
        Specification<OrderDetail> specification = createSpecification(searchMap);
        return orderDetailDao.findAll(specification);
    }

    public Page<OrderDetail> findSearch(Map searchMap, int page, int size) {
        Specification<OrderDetail> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return orderDetailDao.findAll(specification, pageRequest);
    }

    private Specification<OrderDetail> createSpecification(Map searchMap) {
        return new Specification<OrderDetail>() {
            @Override
            public Predicate toPredicate(Root<OrderDetail> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("title") != null &&
                        !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(
                            root.get("title").as(String.class), "%" +
                                    (String) searchMap.get("title") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
