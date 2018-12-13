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
    public void Query() {
        renderJson(Db.paginate(
                getParaToInt("pageCurrent"),
                getParaToInt("pageSize"),
                "SELECT person.ryxm, person.ryxb, person.bzlx, person.bzqk",
                "FROM person LEFT JOIN department ON person.dwbh = department.dwbh " +
                        "WHERE person.ryxm LIKE '%" + getPara("ryxm") + "%' " +
                        "And person.ryxb LIKE '%" + getPara("ryxb") + "%' " +
                        "And person.bzlx LIKE '%" + getPara("bzlx") + "%' " +
                        "And person.bzqk LIKE '%" + getPara("bzqk") + "%' " +
                        "AND department.szcs LIKE '%" + getPara("szcs") + "%' " +
                        "AND department.dwzd LIKE '%" + getPara("dwzd") + "%' " +
                        "AND department.dwlb LIKE '%" + getPara("dwlb") + "%' " +
                        "AND department.dwlx LIKE '%" + getPara("dwlx") + "%' " +
                        "AND department.jb LIKE '%" + getPara("jb") + "%' " +
                        "AND department.dwbh LIKE '%" + getPara("dwbh") + "%' " +
                        "ORDER BY person.id DESC").getList());
    }

    /**
     * 获取人员数量
     */
    public void Total() {
        Long count = Db.queryLong("SELECT COUNT(*) FROM person LEFT JOIN department ON person.dwbh = department.dwbh " +
                "WHERE person.ryxm LIKE '%" + getPara("ryxm") + "%' " +
                "And person.ryxb LIKE '%" + getPara("ryxb") + "%' " +
                "And person.bzlx LIKE '%" + getPara("bzlx") + "%' " +
                "And person.bzqk LIKE '%" + getPara("bzqk") + "%' " +
                "AND department.szcs LIKE '%" + getPara("szcs") + "%' " +
                "AND department.dwzd LIKE '%" + getPara("dwzd") + "%' " +
                "AND department.dwlb LIKE '%" + getPara("dwlb") + "%' " +
                "AND department.dwlx LIKE '%" + getPara("dwlx") + "%' " +
                "AND department.jb LIKE '%" + getPara("jb") + "%' " +
                "AND department.dwbh LIKE '%" + getPara("dwbh") + "%' ");
        renderText(count.toString());
    }

}
