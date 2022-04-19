package top.lgx.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 21:53
 * @Description: 阿里云OSS配置
 */
@Configuration
public class OSSConfig {

    @Bean
    public OSS ossClient(){
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String keyId = "<your-access-key-id>";
        String keySecret = "<your-access-key-secret>";
        return new OSSClientBuilder().build(endpoint, keyId, keySecret);
    }
}
