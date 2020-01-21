package com.ly.login.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
@Accessors(chain = true)//链式调用
public class User {
    private Integer id;
    private String name;
    private String password;

    private String account_id;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;

}
