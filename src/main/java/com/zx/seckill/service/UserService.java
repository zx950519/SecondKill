package com.zx.seckill.service;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:20
 * @description：用户服务
 * @modified By：
 * @version: $
 */

import com.zx.seckill.dao.UserDao;
import com.zx.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class UserService {

    @Autowired
    private UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("22222");
        userDao.insert(u1);

        return true;
    }
}
