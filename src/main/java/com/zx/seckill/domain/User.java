package com.zx.seckill.domain;

import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 10:48
 * @description：用户
 * @modified By：
 * @version: $
 */

@Data
public class User {
    private int id;         // 用户id
    private String name;    // 用户名

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
