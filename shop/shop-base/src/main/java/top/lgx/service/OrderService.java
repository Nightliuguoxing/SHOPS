package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.OrderDao;
import top.lgx.entity.Order;
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
 * @Date: 2022-04-19 23:00
 * @Description:
 */
@Service
public class OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderDao orderDao;

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public Order findById(String id){
        return orderDao.findById(id).orElse(null);
    }

    public void insert(Order order){
        order.setOrderId(idWorker.nextId()+"");
        orderDao.save(order);
    }

    public void update(Order order){
        orderDao.save(order);
    }

    public void deleteById(String id){
        orderDao.deleteById(id);
    }

    public List<Order> findSearch(Map searchMap) {
        Specification<Order> specification = createSpecification(searchMap);
        return orderDao.findAll(specification);
    }

    public Page<Order> findSearch(Map searchMap, int page, int size) {
        Specification<Order> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return orderDao.findAll(specification, pageRequest);
    }

    private Specification<Order> createSpecification(Map searchMap) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("createTime") != null &&
                        !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(
                            root.get("createTime").as(String.class), "%" +
                                    (String) searchMap.get("createTime") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
