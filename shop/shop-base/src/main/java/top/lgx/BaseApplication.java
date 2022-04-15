package top.lgx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.lgx.utils.JwtUtil;
import utils.IdWorker;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-12 23:03
 * @Description:
 */
@EnableCaching
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-15 09:22
     * @Return: utils.IdWorker
     * @Description: 分布式ID生成器
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-15 09:22
     * @Return: org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     * @Description: 密码加密
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-15 09:22
     * @Return: top.lgx.utils.JwtUtil
     * @Description: JWT工具类
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
