package com.wts.controller;

import com.jfinal.core.Controller;

/**
 * ChartController class
 *
 * @author wts
 * @date 2019/1/16
 */
public class ChartController  extends Controller {


    public void t() {
        String data1 = "['100', '110', '120']";
        String data2 = "['200', '210', '220']";
        String str = "{\"tooltip\":{trigger:\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"data\":[\"男\",\"女\"]},\"xAxis\":{\"type\":\"value\"},\"yAxis\":{\"type\":\"category\",\"data\":[\"行政\",\"事业\",\"工勤\"]},\"series\":[{\"name\":\"男\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":true,\"formatter\":\"{@score}\"}},\"data\":"
                + data1 + "},{\"name\":\"女\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":true,\"formatter\":\"{@score}\"}},\"data\":" + data2 + "}]}";
        renderJson(str);
    }
}
