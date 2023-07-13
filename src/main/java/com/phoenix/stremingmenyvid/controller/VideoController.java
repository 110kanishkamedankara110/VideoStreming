package com.phoenix.stremingmenyvid.controller;

import com.google.gson.Gson;
import com.phoenix.stremingmenyvid.model.Video;
import com.phoenix.stremingmenyvid.service.VideoLoaderService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@RestController
@RequestMapping("/videoPlayer")
@CrossOrigin
public class VideoController {

    private VideoLoaderService videoLoaderService;

    public VideoController(VideoLoaderService videoLoaderService) {
        this.videoLoaderService = videoLoaderService;
    }

    @GetMapping(value = "/{title}", produces = "video/mp4")
    public Resource getVideo(@PathVariable String title) {
        return videoLoaderService.getVideo(title);
    }

    @PostMapping(value = "/upload", produces = "text/json")
    public String upload(@RequestHeader("file") MultipartFile file) {
        try {
            File f1 = new File("videos");

            if (!f1.exists()) {
                f1.mkdir();
            }

            String fileName = "Vid" + System.currentTimeMillis();
            File f = new File("videos/" + fileName + ".mp4");
            f.createNewFile();

            BufferedInputStream is = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(f));

            int bytesRead;
            byte[] byt = new byte[(int) file.getSize()];

            while ((bytesRead = is.read(byt)) != -1) {
                fos.write(byt);

            }
            fos.close();
            is.close();


            return new Gson().toJson(new Video(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }

}

