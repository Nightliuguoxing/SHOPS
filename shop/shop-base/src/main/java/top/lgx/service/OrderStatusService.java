package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.OrderStatusDao;
import top.lgx.entity.OrderStatus;
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
 * @Date: 2022-04-20 08:25
 * @Description:
 */
@Service
public class OrderStatusService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderStatusDao orderStatusDao;

    public List<OrderStatus> findAll(){
        return orderStatusDao.findAll();
    }

    public OrderStatus findById(String id){
        return orderStatusDao.findById(id).orElse(null);
    }

    public void insert(OrderStatus orderStatus){
        orderStatus.setOrderId(idWorker.nextId()+"");
        orderStatusDao.save(orderStatus);
    }

    public void update(OrderStatus orderStatus){
        orderStatusDao.save(orderStatus);
    }

    public void deleteById(String id){
        orderStatusDao.deleteById(id);
    }

    public List<OrderStatus> findSearch(Map searchMap) {
        Specification<OrderStatus> specification = createSpecification(searchMap);
        return orderStatusDao.findAll(specification);
    }

    public Page<OrderStatus> findSearch(Map searchMap, int page, int size) {
        Specification<OrderStatus> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return orderStatusDao.findAll(specification, pageRequest);
    }

    private Specification<OrderStatus> createSpecification(Map searchMap) {
        return new Specification<OrderStatus>() {
            @Override
            public Predicate toPredicate(Root<OrderStatus> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("status") != null &&
                        !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(
                            root.get("status").as(String.class), "%" +
                                    (String) searchMap.get("status") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
