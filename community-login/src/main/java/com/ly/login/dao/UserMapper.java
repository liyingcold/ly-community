package com.ly.login.dao;

import com.ly.login.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into public.pg_community_user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    int insert(User user);

    @Insert("insert into public.pg_community_user (name,password) values (#{name},#{password})")
    int register(User user);

    @Select("select * from public.pg_community_user")
     List<User> getAllUserInfo();

//   用户名密码
    @Select("select name,password from public.pg_community_user where name = #{name} and password = #{password}")
    User selectLogin(@Param("name")String name,@Param("password")String password);

//    检查用户名是否存在
    @Select("select count(id) from public.pg_community_user where name =#{name}")
    int checkUserName(@Param("name")String name);


//    检查token
    @Select("select count(id) from public.pg_community_user where token =#{token}")
    int checkToken(@Param("token")String token);

}
