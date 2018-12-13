package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.wts.entity.model.Department;
import org.apache.log4j.Logger;

/**
 * DepartmentController class
 *
 * @author wts
 * @date 2018/12/13
 */
public class DepartmentController extends Controller {
    private static Logger logger = Logger.getLogger(Department.class);

    public void Get() {
        renderJson(Department.dao.findById(getPara("id")));
    }

    /**
     * 获取单位列表
     */
    public void Query() {
        renderJson(Db.paginate(
                getParaToInt("pageCurrent"),
                getParaToInt("pageSize"),
                "SELECT *",
                "FROM department " +
                        "WHERE szcs LIKE '%" + getPara("szcs") + "%' " +
                        "AND dwzd LIKE '%" + getPara("dwzd") + "%' " +
                        "AND dwlb LIKE '%" + getPara("dwlb") + "%' " +
                        "AND dwlx LIKE '%" + getPara("dwlx") + "%' " +
                        "AND sjdw LIKE '%" + getPara("sjdw") + "%' " +
                        "AND jb LIKE '%" + getPara("jb") + "%' " +
                        "ORDER BY department.id DESC").getList());
    }

    /**
     * 获取单位数量
     */
    public void Totol() {
        Long count = Db.queryLong("SELECT COUNT(*) FROM department " +
                "WHERE szcs LIKE '%" + getPara("szcs") + "%' " +
                "AND dwzd LIKE '%" + getPara("dwzd") + "%' " +
                "AND dwlb LIKE '%" + getPara("dwlb") + "%' " +
                "AND dwlx LIKE '%" + getPara("dwlx") + "%' " +
                "AND sjdw LIKE '%" + getPara("sjdw") + "%' " +
                "AND jb LIKE '%" + getPara("jb") + "%' ");
        renderText(count.toString());
    }

}
