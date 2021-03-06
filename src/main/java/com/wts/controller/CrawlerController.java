package com.wts.controller;

import com.jfinal.core.Controller;
import com.wts.crawler.city.*;

import static com.wts.crawler.Common.downDepartmentError;
import static com.wts.crawler.Common.downPersonError;


/**
 * CrawlerController class
 *
 * @author wts
 * @date 2019/1/22
 */
public class CrawlerController extends Controller {
    /**
     * 下载结构文件
     */
    public void a() {
        JiNan.getFile();
        renderNull();
    }
    /**
     * 根据结构文件下载信息
     */
    public void b() {
        JiNan.down();
        renderNull();
    }
    /**
     * 再次下载未成功下载的数据
     */
    public void c() {
        downDepartmentError("JiNan");
        downPersonError();
    }
    /**
     * 检测服务是否正常开启
     */
    public void i() {
        renderText("服务已正常开启！");
    }
}
