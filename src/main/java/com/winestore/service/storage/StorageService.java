package com.winestore.service.storage;

import java.io.InputStream;

public interface StorageService {

    String uploadFile(String key, String contentType, InputStream inputStream, Long size);

    void deleteFile(String path);
}
