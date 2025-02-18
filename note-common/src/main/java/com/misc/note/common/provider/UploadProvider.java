package com.misc.note.common.provider;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
@Component
public class UploadProvider {

    @Value("${minio.end-point}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    private static MinioClient minioClient;

    public static MinioClient getClient() {
        return minioClient;
    }

    @PostConstruct
    public MinioClient getMinioClient() {
        log.info("初始化minio,创建minio客户端！");
        try {
            minioClient = MinioClient.builder()
                    .endpoint(endpoint).credentials(accessKey, secretKey).build();
        }catch (Exception e){
            log.info("初始化minio失败！{}", e.getMessage());
        }
        log.info("初始化minio成功！");
        return minioClient;
    }

    public static boolean isBucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }
}
