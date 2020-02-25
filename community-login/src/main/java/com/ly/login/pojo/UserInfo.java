package com.ly.login.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
@Accessors(chain = true)//链式调用
public class UserInfo {
    private Integer id;
    private String account_id;
    private String sex;
    private Long birth;
    private String email;
    private String signature;
}
