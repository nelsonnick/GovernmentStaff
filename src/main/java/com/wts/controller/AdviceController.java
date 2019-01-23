package com.wts.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.wts.entity.model.Advice;
import com.wts.entity.model.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * AdviceController class
 *
 * @author wts
 * @date 2019/1/23
 */
public class AdviceController extends Controller {
    private static Logger logger = Logger.getLogger(Advice.class);

    /**
     * 获取人员数量
     * dwbh：单位编号
     * bzlx：编制类型
     * ryxb：人员性别
     */
    public void add() {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/sns/jscode2session?appid=wxb9a7b1d61af09a1e&secret=2491719580cb455d3da5753fccfbd78f&js_code="+getPara("code")+"&grant_type=authorization_code")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject= JSON.parseObject(response.body().string());
            Advice advice = new Advice();
            advice.set("openid",jsonObject.getString("openid")).set("advice",getPara("advice")).set("name",getPara("name")).set("phone",getPara("phone")).set("email",getPara("email")).set("time",new Date());
            advice.save();
            renderText("OK");
        }catch (IOException e){
            renderText("ERROE");
        }
    }

}
