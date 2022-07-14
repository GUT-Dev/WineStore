package com.winestore.service.storage.impl;

import com.winestore.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final S3Client client;

    @Value("${aws.s3.bucket-name}")
    private String bucket;

    public String uploadFile(String key, String contentType, InputStream inputStream, Long size) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, size));

        return getUrl(key);
    }

    @Override
    public void deleteFile(String path) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .key(getKeyFromPath(path))
                .build();

        client.deleteObject(deleteObjectRequest);
    }

    private String getKeyFromPath(String path) {
        return path.substring(path.lastIndexOf("/"));
    }

    private String getUrl(String key) {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        URL url = client.utilities().getUrl(getUrlRequest);
        return url.toString();
    }
}
