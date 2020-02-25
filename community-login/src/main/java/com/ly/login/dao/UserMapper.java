package com.ly.login.dao;

import com.ly.login.pojo.User;
import com.ly.login.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //    获取所有用户
    @Select("select * from public.pg_community_user")
    List<User> getAllUserInfo();

    /**
     * 用户登录注册信息
     */
//    插入新用户
    @Insert("insert into public.pg_community_user (name,password) values (#{name},#{password})")
    int register(User user);
//    修改密码
    @Update("update public.pa_community_uer set password = #{password} where account_id = #{account_id}")
    int changePasw(User user);
//   查询用户名密码
    @Select("select name,password from public.pg_community_user where name = #{name} and password = #{password}")
    User selectLogin(@Param("name")String name,@Param("password")String password);
//    检查用户名
    @Select("select count(id) from public.pg_community_user where name =#{name}")
    int checkUserName(@Param("name")String name);
//    检查account_id
    @Select("select count(id) from public.pg_community_user where account_id =#{account_id}")
    int checkAccountId(@Param("account_id")String account_id);

    /**
     * 用户信息
     */
//    修改
    @Update("update public.pg_community_user_info set account_id = #{account_id} ,sex = #{sex} ,birth = #{birth} ,email = #{email} ,signature = #{signature}")
    int changeUserInfo(UserInfo userInfo);
//    查看
//    UserInfo selectUserInfo()


}
