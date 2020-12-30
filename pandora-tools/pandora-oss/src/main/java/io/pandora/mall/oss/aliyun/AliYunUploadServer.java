package io.pandora.mall.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import io.pandora.mall.exception.CustomException;
import io.pandora.mall.oss.UploadServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * @see
 * <a href="https://help.aliyun.com/document_detail/84781.html?spm=a2c4g.11186623.6.690.2101c06dKNaOeu">
 *     阿里云文件上传文档
 * </a>
 */
@Slf4j
public class AliYunUploadServer implements UploadServer {

    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String endpoint;

    public AliYunUploadServer(AliYunOSSProperties properties) {
        accessKeyId = properties.getAccessKeyId();
        accessKeySecret = properties.getAccessKeySecret();
        bucketName = properties.getBucketName();
        endpoint = properties.getEndpoint();
    }

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        String key = generateKey(fileName);
        return upload(bucketName, key, new ByteArrayInputStream(uploadBytes));
    }

    public static String generateKey(String fileName) {
        //路径格式：yyyymm/name.now.ext，key可以考虑存入sys_file
        return new StringBuilder(DateFormatUtils.format(new Date(), "yyyyMM"))
                .append("/").append(FilenameUtils.getBaseName(fileName))
                .append(".").append(SystemClock.now())
                .append(".").append(FilenameUtils.getExtension(fileName))
                .toString();
    }

    public static String upload(String bucketName, String key, InputStream input) {
        if (StringUtils.isBlank(bucketName) || StringUtils.isBlank(key) || input == null) {
            throw new CustomException("阿里云文件上传错误");
        }
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
            ossClient.putObject(bucketName, key, input);
            // 设置URL过期时间为10年
            Date expiration = DateUtils.addYears(new Date(), 10);
            String url = ossClient.generatePresignedUrl(bucketName, key, expiration).toString();
            log.info("阿里云上传文件成功，bucketName:{},key:{},url:{}", bucketName, key, url);
            String[] split = url.split("\\?");
            return split[0];
        } catch (Exception e) {
            log.warn("阿里云上传文件失败：{},bucketName:{},key:{}", e.getMessage(), bucketName, key);
            throw new CustomException("阿里云上传文件失败");
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

}
