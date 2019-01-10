package com.wts.controller;
import com.jfinal.core.Controller;
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

    public void get(){
        renderJson(Person.dao.find("SELECT id,ssbm,ryxm,ryxb,bzlx,bzqk FROM person WHERE dwbh="+getPara("dwbh")+" AND bzlx='"+getPara("bzlx")+"' ORDER BY ryxb"));
    }

}
