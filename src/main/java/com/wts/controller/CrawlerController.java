package com.wts.controller;

import com.jfinal.core.Controller;
import com.wts.crawler.city.*;


/**
 * CrawlerController class
 *
 * @author wts
 * @date 2019/1/22
 */
public class CrawlerController extends Controller {

    public void g() {
        LiaoCheng.getFile();
        renderNull();
    }

    public void d() {
        LiaoCheng.down();
        renderNull();
    }
    public void i() {
        renderText("服务已正常开启！");
    }
}
