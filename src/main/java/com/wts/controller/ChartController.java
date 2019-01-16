package com.wts.controller;

import com.jfinal.core.Controller;

import static com.wts.controller.MainController.getPersonNum;
import static com.wts.controller.MainController.getTotalType;

/**
 * ChartController class
 *
 * @author wts
 * @date 2019/1/16
 */
public class ChartController extends Controller {


    public void t() {
        String subtext = "";
        if (!getPara("szcs").equals("")) {
            subtext = getPara("szcs");
            if (!getPara("dwzd").equals("")) {
                subtext = subtext + "/" + getPara("dwzd");
            }
            if (!getPara("dwlb").equals("")) {
                subtext = subtext + "/" + getPara("dwlb");
            }
            if (!getPara("dwlx").equals("")) {
                subtext = subtext + "/" + getPara("dwlx");
            }
        }
        String text = "";
        subtext = "";
        String str="";
        if (getPara("type").equals("0")) {
            String a1 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "男", "行政编制").toString();
            String a2 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "男", "事业编制").toString();
            String a3 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "男", "工勤编制").toString();
            String b1 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "女", "行政编制").toString();
            String b2 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "女", "事业编制").toString();
            String b3 = getPersonNum(getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), "女", "工勤编制").toString();
//            text = "编制类型";
            String data1 = "[" + a1 + ", " + a2 + ", " + a3 + "]";
            String data2 = "[" + b1 + ", " + b2 + ", " + b3 + "]";
            str = "{\"title\":{\"text\":\"" + text + "\",\"subtext\":\"" + subtext + "\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"data\":[\"男\",\"女\"]},\"grid\":{\"left\":\"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\":true},\"yAxis\":{\"type\":\"value\"},\"xAxis\":{\"type\":\"category\",\"data\":[\"行政\",\"事业\",\"工勤\"]},\"series\":[{\"name\":\"男\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":false,\"position\":\"insideRight\"}},\"data\":" + data1 + "},{\"name\":\"女\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":false,\"position\":\"insideRight\"}},\"data\":" + data2 + "}]}";
        }else if (getPara("type").equals("1")) {
            String a1 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"xz_plan_num","xz_real_num",">").toString();
            String a2 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"sy_plan_num","sy_real_num",">").toString();
            String a3 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"gq_plan_num","gq_real_num",">").toString();
            String b1 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"xz_plan_num","xz_real_num","=").toString();
            String b2 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"sy_plan_num","sy_real_num","=").toString();
            String b3 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"gq_plan_num","gq_real_num","=").toString();
            String c1 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"xz_plan_num","xz_real_num","<").toString();
            String c2 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"sy_plan_num","sy_real_num","<").toString();
            String c3 = getTotalType(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),"gq_plan_num","gq_real_num","<").toString();

//            text = "编制情况";
            String data1 = "["+a1+", "+a2+", "+a3+"]";
            String data2 = "["+b1+", "+b2+", "+b3+"]";
            String data3 = "["+c1+", "+c2+", "+c3+"]";
            str = "{\"title\":{\"text\":\"" + text + "\",\"subtext\":\"" + subtext + "\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"data\":[\"超编\",\"空编\",\"满编\"]},\"grid\":{\"left\":\"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\":true},\"yAxis\":{\"type\":\"value\"},\"xAxis\":{\"type\":\"category\",\"data\":[\"行政\",\"事业\",\"工勤\"]},\"series\":[{\"name\":\"超编\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":false,\"position\":\"insideRight\"}},\"data\":" + data1 + "},{\"name\":\"空编\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":false,\"position\":\"insideRight\"}},\"data\":" + data2 + "},{\"name\":\"满编\",\"type\":\"bar\",\"stack\":\"总量\",\"label\":{\"normal\":{\"show\":false,\"position\":\"insideRight\"}},\"data\":" + data3 + "}]}";
        }else{

        }
        renderJson(str);
    }

}
