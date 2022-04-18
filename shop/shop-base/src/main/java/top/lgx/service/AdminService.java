package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.lgx.dao.AdminDao;
import top.lgx.entity.Admin;
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
 * @Date: 2022-04-14 19:20
 * @Description:
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:25
     * @Params:
     * @Return: java.util.List<top.lgx.entity.Admin>
     * @Description: 查询所有管理员
     */
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:25
     * @Params: id
     * @Return: top.lgx.entity.Admin
     * @Description: 根据id查询管理员
     */
    public Admin findById(String id) {
        return adminDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:31
     * @Params: username
     * @Params: password
     * @Return: top.lgx.entity.Admin
     * @Description: 根据登陆名和密码查询
     */
    public Admin findByNameAndPassword(String username, String password) {
        Admin admin = adminDao.findByUsername(username);
        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            return null;
        }
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:25
     * @Params: admin
     * @Return: void
     * @Description: 添加管理员
     */
    public void insert(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setState("0");
        adminDao.save(admin);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:26
     * @Params: admin
     * @Return: void
     * @Description: 修改管理员
     */
    public void update(Admin admin) {
        Admin a = adminDao.findById(admin.getId()).orElse(null);
        if(admin.getPassword() != null && !admin.getPassword().equals("")){
            admin.setPassword(encoder.encode(admin.getPassword()));
        }else {
            admin.setPassword(a.getPassword());
        }
        adminDao.save(admin);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 19:26
     * @Params: id
     * @Return: void
     * @Description: 删除管理员
     */
    public void deleteById(String id) {
        adminDao.deleteById(id);
    }

    public List<Admin> findSearch(Map searchMap) {
        Specification<Admin> specification = createSpecification(searchMap);
        return adminDao.findAll(specification);
    }

    public Page<Admin> findSearch(Map searchMap, int page, int size) {
        Specification<Admin> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return adminDao.findAll(specification, pageRequest);
    }

    private Specification<Admin> createSpecification(Map searchMap) {
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("username") != null &&
                        !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(
                            root.get("username").as(String.class), "%" +
                                    (String) searchMap.get("username") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
