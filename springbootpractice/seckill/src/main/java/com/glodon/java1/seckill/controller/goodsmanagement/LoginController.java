package com.glodon.java1.seckill.controller.goodsmanagement;

import com.glodon.java1.seckill.domain.User;
import com.glodon.java1.seckill.result.Meta;
import com.glodon.java1.seckill.result.Result;
import com.glodon.java1.seckill.service.goodsmanagement.LoginService;
import com.glodon.java1.seckill.utils.TokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /** 登录验证
     * auth:liual
     * date:2020/8/9
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user) {
        HashMap<String, Object> hs = new HashMap<>();
        try {
            if (loginService.CheckUser(user)) {
                String token = TokenUtil.sign(user.getUsername());
                //根据用户名去查找用户ID
                hs.put("id", loginService.queryIdByUsername(user));
                hs.put("username", user.getUsername());
                hs.put("token", token);
                return new Result(Meta.SUCCESS_LOGIN, hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Meta.SERVER_ERROR, hs);
        }

        return new Result(Meta.ERROR_LOGIN, hs);
    }


}