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
        JiNing.getFile();
        renderNull();
    }
    public void d() {
        ZiBo.down();
        renderNull();
    }
}
