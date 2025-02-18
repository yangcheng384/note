package com.misc.note.common.util;

import com.misc.note.common.provider.UploadProvider;
import io.minio.MakeBucketArgs;

public class UploadUtil {

    public static boolean isBucketExists(String bucketName) throws Exception {
        return UploadProvider.isBucketExists(bucketName);
    }

    public static void makeBucket(String bucketName) throws Exception {
        if (!isBucketExists(bucketName)) {
            UploadProvider.getClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}
