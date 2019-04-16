package com.worldsbestauctions.auctionsite.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload/")
public class UploadController {

    private final String currentDir = System.getProperty("user.dir");
    private final String uploadDir = currentDir + "/src/main/resources/static/assets/img/";

    @PostConstruct
    void createUploadDir(){
        File path = new File(uploadDir);
        if (!path.exists()) path.mkdirs();
    }

    @PostMapping
    String uploadPicture(@RequestParam("file") MultipartFile file){
        System.out.println("new file got sent to us");
        System.out.println(file.getContentType());
//        String[] allowedContent = {"image/png"};
//        if(!file.getContentType().matches("^"+String.join("|", allowedContent)+".*$"));
        String filename = UUID.randomUUID().toString().
                concat(file.getOriginalFilename().replaceAll("^.*(\\.[\\w]{3,4})$", "$1"));
        File path = new File(uploadDir + filename);
        try{
            file.transferTo(path);
        }catch (Exception e){ return null; }
        return filename;
    }

    @GetMapping
    String testPath(){
        return "WOrkd";
    }
}
