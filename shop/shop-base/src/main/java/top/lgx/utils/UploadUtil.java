package top.lgx.utils;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 21:40
 * @Description:
 */
public class UploadUtil {

    @Autowired
    private OSS ossClient;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 22:01
     * @Params: file
     * @Return: java.lang.String
     * @Description: 上传图片到阿里云
     */
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

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-18 22:00
     * @Params: file
     * @Return: java.lang.String
     * @Description: 上传图片到本地
     */
    public String uploadToLocal(MultipartFile file) {
        return "不支持上传到本地~";
    }

}
