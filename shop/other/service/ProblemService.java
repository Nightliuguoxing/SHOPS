package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.lgx.dao.ProblemDao;
import top.lgx.entity.Problem;
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
 * @Date: 2022-04-13 10:00
 * @Description:
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:09
     * @Return: java.util.List<top.lgx.entity.Problem>
     * @Description: 查询所有问题
     */
    public List<Problem> findAll() {
        return problemDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:09
     * @Params: id 问题id
     * @Return: top.lgx.entity.Problem
     * @Description: 根据id查询问题
     */
    public Problem findById(String id) {
        return problemDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:09
     * @Params: problem 问题
     * @Return: void
     * @Description: 添加问题
     */
    public void insert(Problem problem) {
        problem.setId(idWorker.nextId() + "");
        problemDao.save(problem);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:10
     * @Params: problem 问题
     * @Return: void
     * @Description: 修改问题
     */
    public void update(Problem problem) {
        problemDao.save(problem);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:10
     * @Params: id 问题id
     * @Return: void
     * @Description: 删除问题
     */
    public void deleteById(String id) {
        problemDao.deleteById(id);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:10
     * @Params: searchMap 查询条件
     * @Return: java.util.List<top.lgx.entity.Problem>
     * @Description: 条件查询
     */
    public List<Problem> findSearch(Map searchMap) {
        Specification<Problem> specification = createSpecification(searchMap);
        return problemDao.findAll(specification);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:10
     * @Params: searchMap 查询条件
     * @Params: page 页码
     * @Params: size 每页大小
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 分页查询
     */
    public Page<Problem> findSearch(Map searchMap, int page, int size) {
        Specification<Problem> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.findAll(specification, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:24
     * @Params: labelId 分类id
     * @Params: page 页码
     * @Params: size 每页大小
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签id查询最新问题
     */
    public Page<Problem> findNewListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.findNewListByLabelId(labelId, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:46
     * @Params: labelId
     * @Params: page
     * @Params: size
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签id查询热门问题
     */
    public Page<Problem> findHotListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.findHotListByLabelId(labelId, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:50
     * @Params: labelId
     * @Params: page
     * @Params: size
     * @Return: org.springframework.data.domain.Page<top.lgx.entity.Problem>
     * @Description: 根据标签id查询等待回答问题
     */
    public Page<Problem> findWaitListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.findWaitListByLabelId(labelId, pageRequest);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-13 10:10
     * @Params: searchMap 查询条件
     * @Return: org.springframework.data.jpa.domain.Specification<top.lgx.entity.Problem>
     * @Description: 构建查询条件
     */
    private Specification<Problem> createSpecification(Map searchMap) {
        return new Specification<Problem>() {
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("title") != null &&
                        !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(
                            root.get("title").as(String.class), "%" +
                                    (String) searchMap.get("title") + "%"));
                }
                if (searchMap.get("solve") != null &&
                        !"".equals(searchMap.get("solve"))) {
                    predicateList.add(cb.equal(
                            root.get("solve").as(String.class), (String) searchMap.get("solve")));
                }
                return cb.and(predicateList.toArray(new
                        Predicate[predicateList.size()]));
            }
        };
    }
}
