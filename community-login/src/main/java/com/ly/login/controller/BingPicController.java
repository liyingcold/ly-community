package com.ly.login.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.login.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.ly.login.common.Const.BingPicUrl;
import static com.ly.login.common.Const.BingPicUrl_BASE;

@RestController//返回json/xml数据发送到前台页面
@RequestMapping("/bing")
public class BingPicController {

    @Autowired//发送http请求
    private RestTemplate restTemplate;

    /**
     * 获取bing每日一图
     */
    @GetMapping("/pic")
    public ServerResponse<String> getBingPic(){
        ResponseEntity<JSONObject> responseEntity=restTemplate.exchange(BingPicUrl, HttpMethod.GET,null, JSONObject.class);
        JSONObject jresp=responseEntity.getBody();
        if (responseEntity.getStatusCodeValue() == 200 && jresp !=null){
            JSONArray jsonArray=jresp.getJSONArray("images");
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            String bingPicStr=jsonObject.getString("url");
            return ServerResponse.createBySuccess(BingPicUrl_BASE+bingPicStr);
        }
        else {
            return ServerResponse.createByErrorMsg("必应每日一图请求失败");
        }
    }
}
