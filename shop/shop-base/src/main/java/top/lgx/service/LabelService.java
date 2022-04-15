package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.LabelDao;
import top.lgx.entity.Label;
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
 * @Date: 2022-04-12 23:19
 * @Description:
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:02
     * @Return: java.util.List<top.lgx.entity.Label>
     * @Description: 查询所有标签
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:03
     * @Params: id
     * @Return: top.lgx.entity.Label
     * @Description: 根据id查询标签
     */
    public Label findById(String id) {
        return labelDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:03
     * @Params: label
     * @Return: void
     * @Description: 添加标签
     */
    public void insert(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:03
     * @Params: label
     * @Return: void
     * @Description: 修改标签
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:03
     * @Params: id
     * @Return: void
     * @Description: 删除标签
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:13
     * @Params: searchMap
     * @Return: java.util.List<top.lgx.entity.Label>
     * @Description: 根据条件查询标签
     */
    public List<Label> findSearch(Map searchMap) {
        Specification<Label> specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:17
     * @Params: searchMap
     * @Params: page
     * @Params: size
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Label>
     * @Description: 根据条件查询标签（分页）
     */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification<Label> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(specification, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 09:13
     * @Params: searchMap
     * @Return: org.springframework.data.jpa.domain.Specification<top.lgx.entity.Label>
     * @Description: 根据条件构建查询条件
     */
    private Specification<Label> createSpecification(Map searchMap) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelName") != null &&
                        !"".equals(searchMap.get("labelName"))) {
                    predicateList.add(cb.like(
                            root.get("labelName").as(String.class), "%" +
                                    (String) searchMap.get("labelName") + "%"));
                }
                if (searchMap.get("state") != null &&
                        !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.equal(
                            root.get("state").as(String.class), (String) searchMap.get("state")));
                }
                if (searchMap.get("recommend") != null &&
                        !"".equals(searchMap.get("recommend"))) {
                    predicateList.add(cb.equal(
                            root.get("recommend").as(String.class),
                            (String) searchMap.get("recommend")));
                }
                return cb.and(predicateList.toArray(new
                        Predicate[predicateList.size()]));
            }
        };
    }
}
