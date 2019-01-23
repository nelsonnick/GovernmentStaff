package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.wts.entity.model.Person;
import org.apache.log4j.Logger;

/**
 * PersonController class
 *
 * @author wts
 * @date 2018/12/13
 */
public class PersonController extends Controller {
    private static Logger logger = Logger.getLogger(Person.class);

    /**
     * 获取人员总数
     */
    public void allTotal() {
        renderText(Db.queryLong("SELECT COUNT(*) FROM person").toString());
    }
    /**
     * 获取人员数量
     * dwbh：单位编号
     * bzlx：编制类型
     * ryxb：人员性别
     */
    public Long total(String dwbh, String bzlx, String ryxb) {
        return Db.queryLong("SELECT COUNT(*) FROM person WHERE dwbh = '" + dwbh + "' AND bzlx='" + bzlx + "' AND ryxb='" + ryxb +"'");
    }
    public void get() {
        renderJson(Person.dao.find("SELECT id,ssbm,ryxm,ryxb,bzlx,bzqk FROM person WHERE dwbh=" + getPara("dwbh") + " AND bzlx='" + getPara("bzlx") + "' ORDER BY ryxb"));
    }

    /**
     * 获取描述信息
     * dwbh：单位编号
     * bzlx：编制类型
     */
    public void getDesc() {
        Long male = total(getPara("dwbh"), getPara("bzlx"), "男");
        Long female = total(getPara("dwbh"), getPara("bzlx"), "女");
        Long noSex = total(getPara("dwbh"), getPara("bzlx"), "");
        Long total = male + female ;
        if(male==0 && female==0){
            renderText(getPara("bzlx") + "：共计" + noSex + "人");
        }else{
            renderText(getPara("bzlx") + "：共计" + total + "人，其中男性" + male + "人，女性" + female + "人");
        }
    }
}
