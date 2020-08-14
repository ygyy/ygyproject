package com.glodon.java1.seckill.service.goodsmanagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface UploadService {

    /**图片上传
     * auth:liual
     * date:2020/8/10
     */
    Map<String, String> uploadImg(MultipartFile file);
}
