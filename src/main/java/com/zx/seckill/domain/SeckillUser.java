package com.zx.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 10:58
 * @description：秒杀用户
 * @modified By：
 * @version: $
 */
@Data
public class SeckillUser {
    private Long id;                // id
    private String nickname;        // 秒杀用户昵称
    private String password;        // 密码
    private String salt;            // MD5盐
    private String head;            // ？？？
    private Date registerDate;      // 注册时间
    private Date lastLoginDate;     // 上次登录时间
    private Integer loginCount;     // 登录次数

    @Override
    public String toString() {
        return "SeckillUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", tegisterDate=" + registerDate +
                ", lastLoginDate=" + lastLoginDate +
                ", loginCount=" + loginCount +
                '}';
    }
}
