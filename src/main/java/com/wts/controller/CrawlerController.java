package com.wts.controller;

import com.jfinal.core.Controller;
import com.wts.crawler.city.ShengZhi;


/**
 * CrawlerController class
 *
 * @author wts
 * @date 2019/1/22
 */
public class CrawlerController extends Controller {

    public void get() {
        ShengZhi.getFile();
        renderText("11111111111111");
    }

    public void down() {
        ShengZhi.down();
        renderNull();
    }
}
