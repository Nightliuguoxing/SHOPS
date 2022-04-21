package top.lgx.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.lgx.dao.UserDao;
import top.lgx.entity.User;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-14 10:34
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:48
     * @Params:
     * @Return: java.util.List<top.lgx.entity.User>
     * @Description: 查询所有用户
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:48
     * @Params: id
     * @Return: top.lgx.entity.User
     * @Description: 根据id查询用户
     */
    public User findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 23:26
     * @Params: tel
     * @Params: password
     * @Return: top.lgx.entity.User
     * @Description: 用户登录
     */
    public User findUserByTelAndPassword(String tel, String password) {
        User user = userDao.findByTel(tel);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:48
     * @Params: user
     * @Return: void
     * @Description: 用户注册
     */
    public void register(User user, String code) {
        String smscode = (String) redisTemplate.opsForValue().get("sms_code_" + user.getTel());
        if (smscode == null) {
            throw new RuntimeException("请点击获取短信验证码");
        }
        if (!smscode.equals(code)) {
            throw new RuntimeException("短信验证码错误");
        }
        user.setId(idWorker.nextId() + "");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setFollowCount(0);
        user.setFansCount(0);
        user.setOnline(0L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setLastTime(new Date());
        userDao.save(user);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:49
     * @Params: user
     * @Return: void
     * @Description: 修改用户
     */
    public void update(User user) {
        User u = userDao.findById(user.getId()).orElse(null);
        if(user.getPassword() != null && !user.getPassword().equals("")){
            user.setPassword(encoder.encode(user.getPassword()));
        }else {
            user.setPassword(u.getPassword());
        }
        userDao.save(user);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 17:01
     * @Params: id
     * @Params: x
     * @Return: void
     * @Description: 更新粉丝数
     */
    @Transactional(rollbackOn = Exception.class)
    public void incFansCount(String id, int x) {
        userDao.incFansCount(id, x);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 17:05
     * @Params: id
     * @Params: x
     * @Return: void
     * @Description: 更新关注数
     */
    @Transactional(rollbackOn = Exception.class)
    public void incFollowCount(String id, int x) {
        userDao.incFollowCount(id, x);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:49
     * @Params: id
     * @Return: void
     * @Description: 删除用户
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-14 10:49
     * @Params: mobile
     * @Return: void
     * @Description: 发送短信验证码
     */
    public void sendSms(String tel) {
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int code = random.nextInt(max);
        if (code < min) {
            code = code + min;
        }
        redisTemplate.opsForValue().set("sms_code_" + tel, code + "", 5, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap();
        map.put("mobile", tel);
        map.put("code", code + "");
        rabbitTemplate.convertAndSend("sms", map);
    }

    public List<User> findSearch(Map searchMap) {
        Specification<User> specification = createSpecification(searchMap);
        return userDao.findAll(specification);
    }

    public Page<User> findSearch(Map searchMap, int page, int size) {
        Specification<User> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }

    private Specification<User> createSpecification(Map searchMap) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("username") != null &&
                        !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(
                            root.get("username").as(String.class), "%" +
                                    (String) searchMap.get("username") + "%"));
                }
                if (searchMap.get("tel") != null &&
                        !"".equals(searchMap.get("tel"))) {
                    predicateList.add(cb.like(
                            root.get("tel").as(String.class), "%" +
                                    (String) searchMap.get("tel") + "%"));
                }
                if (searchMap.get("sex") != null &&
                        !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(
                            root.get("sex").as(String.class), "%" +
                                    (String) searchMap.get("sex") + "%"));
                }
                if (searchMap.get("email") != null &&
                        !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(
                            root.get("email").as(String.class), "%" +
                                    (String) searchMap.get("email") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
