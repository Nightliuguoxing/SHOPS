package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.lgx.dao.AdminDao;
import top.lgx.entity.Admin;
import utils.IdWorker;

import java.util.List;

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
}
