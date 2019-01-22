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

    public void get() {
        RiZhao.getFile();
        renderNull();
    }
    public void get2() {
        ShengZhi.getFile();
        renderNull();
    }
    public void down() {
        RiZhao.down();
        renderNull();
    }
    public void down1() {
        JiNan.down();
        renderNull();
    }
    public void down2() {
        ShengZhi.down();
        renderNull();
    }
}
