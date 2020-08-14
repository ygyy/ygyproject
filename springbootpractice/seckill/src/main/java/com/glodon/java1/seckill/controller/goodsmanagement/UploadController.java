package com.glodon.java1.seckill.controller.goodsmanagement;


import com.glodon.java1.seckill.service.goodsmanagement.UploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;

    /**图片上传
     * auth:liual
     * date:2020/8/7
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public Map uploadImg(@RequestParam(value = "file",required = false) MultipartFile file){
        return uploadService.uploadImg(file);
    }
}
