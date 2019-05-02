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
    private final String pathForFullsize = currentDir + "/src/main/resources/static/assets/img/";
    private final String pathForThumbnail = currentDir + "/src/main/resources/static/assets/img/thumbnail/";

    @PostConstruct
    void createUploadDir(){
        File[] paths = {new File(pathForFullsize), new File(pathForThumbnail)};
        for(File path : paths){
            if (!path.exists()) path.mkdirs();
        }
    }

    @PostMapping
    String uploadPicture(@RequestParam("fullsize") MultipartFile fullsize, @RequestParam("thumbnail") MultipartFile thumbnail){

        String filename = UUID.randomUUID().toString().
                concat(fullsize.getOriginalFilename().replaceAll("^.*(\\.[\\w]{3,4})$", "$1"));
        File pathFullsize = new File(pathForFullsize + filename);
        File pathThumbnail = new File(pathForThumbnail + filename);
        try{
            fullsize.transferTo(pathFullsize);
            thumbnail.transferTo(pathThumbnail);
        }catch (Exception e){ return null; }
        return filename;
    }
}
