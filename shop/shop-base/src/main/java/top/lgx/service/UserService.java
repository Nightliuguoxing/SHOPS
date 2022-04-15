package top.lgx.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.lgx.dao.UserDao;
import top.lgx.entity.User;
import utils.IdWorker;

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
        userDao.save(user);
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


}
