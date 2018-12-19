package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wts.entity.model.Department;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * DepartmentController class
 *
 * @author wts
 * @date 2018/12/13
 */
public class DepartmentController extends Controller {
    private static Logger logger = Logger.getLogger(Department.class);

    public void get() {
        renderJson(Department.dao.findById(getPara("id")));
    }

    /**
     * 获取单位列表
     * pageCurrent:当前页
     * pageSize：数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     */
    public void list_query() {
        renderJson(Db.paginate(
                getParaToInt("pageCurrent"),
                getParaToInt("pageSize"),
                "SELECT *",
                "FROM department " +
                        "WHERE szcs = '" + getPara("szcs") + "' " +
                        "AND dwzd = '" + getPara("dwzd") + "' " +
                        "AND dwlb = '" + getPara("dwlb") + "' " +
                        "ORDER BY department.id DESC").getList());
    }

    /**
     * 获取单位数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     */
    public void list_totol() {
        Long count = Db.queryLong("SELECT COUNT(*) FROM department " +
                "WHERE szcs = '" + getPara("szcs") + "' " +
                "AND dwzd = '" + getPara("dwzd") + "' " +
                "AND dwlb = '" + getPara("dwlb") + "' ");
        renderText(count.toString());
    }

    public void go(){
        List<Record> dwzd_content = Db.query("SELECT DISTINCT dwzd FROM department");
        List<Record> dwlb_content = Db.query("SELECT DISTINCT dwlb FROM department");
        List<Record> dwlx_content = Db.query("SELECT DISTINCT dwlx FROM department");
        List<Record> jb_content = Db.query("SELECT DISTINCT jb FROM department");
        renderJson(jb_content);
    }
    public void getSZCS(){
        renderJson(Db.find("SELECT DISTINCT szcs FROM department" ));
    }
    public void getDWZD(){
        renderJson(Db.find("SELECT DISTINCT dwzd FROM department WHERE szcs= '" + getPara("szcs") + "' " ));
    }
    public void getDWLB(){
        renderJson(Db.find("SELECT DISTINCT dwlb FROM department WHERE szcs= '" + getPara("szcs") + "' AND dwzd = '" + getPara("dwzd") + "' "  ));
    }
}
