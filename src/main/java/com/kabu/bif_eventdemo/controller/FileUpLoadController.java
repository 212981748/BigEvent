package com.kabu.bif_eventdemo.controller;

import com.kabu.bif_eventdemo.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUpLoadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String s = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));

        file.transferTo(new File("D:\\桌面\\Study\\file\\"+s));
        return Result.success("url地址");
    }
}
