package com.phoenix.stremingmenyvid.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service

public class VideoLoaderService {
    private ResourceLoader resourceLoader;

    public VideoLoaderService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getVideo(String title){
        return resourceLoader.getResource(String.format("file:videos/%s.mp4",title));
    }
}
