package com.ly.login.service;

import com.ly.login.common.ServerResponse;
import com.ly.login.dao.UserMapper;
import com.ly.login.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;



import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<List<User>> findAll() {
        List<User> user=userMapper.getAllUserInfo();
        return ServerResponse.createBySuccess(user);
    }

    /**
     *
     * @param name
     * @param password
     * @return
     */
//    登录
    @Override
    public ServerResponse<User> login(String name, String password) {
        int resultCount = userMapper.checkUserName(name);
        if (resultCount == 0){
            return ServerResponse.createByErrorMsg("用户名不存在");
        }
        String md5Pws= DigestUtils.md5DigestAsHex(password.getBytes());
        User user= userMapper.selectLogin(name,md5Pws);
        if (user == null){
            return  ServerResponse.createByErrorMsg("密码错误");
        }
//        密码置空
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功",user);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> register(User user) {
//        判断name是否存在
        int resultCount =userMapper.checkUserName(user.getName());
        if (resultCount > 0){
            return  ServerResponse.createByErrorMsg("用户名已存在");
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setAccount_id(UUID.randomUUID().toString().replaceAll("-",""));

        int resultcount = userMapper.register(user);
        if (resultcount == 0){
            return ServerResponse.createByErrorMsg("注册失败");
        }

        return ServerResponse.createBySuccessMsg("注册成功");
    }
}
