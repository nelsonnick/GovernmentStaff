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

    public void Get() {
        renderJson(Person.dao.findById(getPara("id")));
    }

    /**
     * 获取人员列表
     */
    public void list_query() {
        renderJson(Db.paginate(
                getParaToInt("pageCurrent"),
                getParaToInt("pageSize"),
                "SELECT person.ryxm, person.ryxb, person.bzlx, person.bzqk, department.id, department.dwmc",
                "FROM person LEFT JOIN department ON person.dwbh = department.dwbh " +
                        "WHERE person.ryxm = '" + getPara("ryxm") + "' " +
                        "ORDER BY person.id DESC").getList());
    }

    /**
     * 获取人员数量
     */
    public void list_total() {
        Long count = Db.queryLong("SELECT COUNT(*) FROM person " +
                "WHERE person.ryxm = '" + getPara("ryxm") + "'");
        renderText(count.toString());
    }

}
