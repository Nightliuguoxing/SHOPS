package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.BrandDao;
import top.lgx.entity.Brand;
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
 * @Date: 2022-04-18 20:31
 * @Description:
 */
@Service
public class BrandService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Return: java.util.List<top.lgx.entity.Brand>
     * @Description: 查询所有品牌
     */
    public List<Brand> findAll(){
        return brandDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: id
     * @Return: top.lgx.entity.Brand
     * @Description: 根据id查询品牌
     */
    public Brand findById(String id){
        return brandDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: brand
     * @Return: void
     * @Description: 新增品牌
     */
    public void insert(Brand brand){
        brand.setId(idWorker.nextId()+"");
        brandDao.save(brand);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: brand
     * @Return: void
     * @Description: 修改品牌 TODO Have Some Problem
     */
    public void update(Brand brand){
        brandDao.save(brand);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: id
     * @Return: void
     * @Description: 删除品牌
     */
    public void deleteById(String id){
        brandDao.deleteById(id);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: searchMap
     * @Return: java.util.List<top.lgx.entity.Brand>
     * @Description: 多条件搜索品牌
     */
    public List<Brand> findSearch(Map searchMap) {
        Specification<Brand> specification = createSpecification(searchMap);
        return brandDao.findAll(specification);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: searchMap
     * @Params: page
     * @Params: size
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Brand>
     * @Description: 多条件搜索分页
     */
    public Page<Brand> findSearch(Map searchMap, int page, int size) {
        Specification<Brand> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return brandDao.findAll(specification, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 21:06
     * @Params: searchMap
     * @Return: org.springframework.data.jpa.domain.Specification<top.lgx.entity.Brand>
     * @Description: 多条件构建查询条件
     */
    private Specification<Brand> createSpecification(Map searchMap) {
        return new Specification<Brand>() {
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("name") != null &&
                        !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(
                            root.get("name").as(String.class), "%" +
                                    (String) searchMap.get("name") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
