package com.glodon.java1.seckill.service.goodsmanagement;

import com.glodon.java1.seckill.domain.User;

public interface LoginService  {
    /**检查用户名和密码
     * auth:liual
     * date:2020/8/10
     */
    boolean CheckUser(User user) throws Exception;

    /**根据用户名查询用户ID
     * auth:liual
     * date:2020/8/10
     */
    Integer queryIdByUsername(User user)throws Exception;
}
