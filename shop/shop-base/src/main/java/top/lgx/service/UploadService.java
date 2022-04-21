package top.lgx.service;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-20 22:43
 * @Description:
 */
@Service
public class UploadService {

    @Autowired
    private OSS ossClient;

    public String uploadToOSS(MultipartFile file) throws IOException {
        //获取文件后缀名
        //String originalFilename = file.getOriginalFilename();
        //int i = originalFilename.lastIndexOf(".");
        //String png = originalFilename.substring(i);

        //存储空间的名字
        String bucketName = "lgx-bf";
        //生成新的文件名 brand/：在bucketName里面创建的分组
        String fileName = "test/"+ file.getOriginalFilename();
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（fileName）。
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        //文件访问地址。
        String url = "https://" + bucketName + ".oss-cn-beijing.aliyuncs.com" + "/" + fileName;
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }

}
