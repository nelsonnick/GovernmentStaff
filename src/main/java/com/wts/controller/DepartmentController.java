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

    public void g(){
        String str ="[";
        List<Record> szcs = Db.find("SELECT DISTINCT szcs FROM department" );
        for (Record s :szcs){
            str = str +"{'label':'"+s.getStr("szcs")+"','value':'"+s.getStr("szcs")+"','children': [";
            List<Record> dwzd = Db.find("SELECT DISTINCT dwzd FROM department WHERE szcs= '" + s.getStr("szcs") + "' " );
            for (Record d :dwzd){
                str = str +"{'label':'"+d.getStr("dwzd")+"','value':'"+d.getStr("dwzd")+"','children': [";
                List<Record> dwlb =Db.find("SELECT DISTINCT dwlb FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' "  );
                for (Record b :dwlb){
//                    str = str +"{'label':'"+b.getStr("dwlb")+"','value':'"+b.getStr("dwlb")+"'},";
                    str = str +"{'label':'"+b.getStr("dwlb")+"','value':'"+b.getStr("dwlb")+"','children': [";
                    List<Record> dwlx =Db.find("SELECT DISTINCT dwlx FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' AND dwlb = '" + b.getStr("dwlb") + "' " );
                    System.out.println("SELECT DISTINCT dwlx FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' AND dwlb = '" + b.getStr("dwlb") + "' " );
                    for (Record x :dwlx){
                        str = str +"{'label':'"+x.getStr("dwlx")+"','value':'"+x.getStr("dwlx")+"'},";
                    }
                    str = str.substring(0,str.length()-1);
                    str = str + "]},";
                }
                str = str.substring(0,str.length()-1);
                str = str + "]},";
            }
            str = str.substring(0,str.length()-1);
            str = str + "]},";
        }
        str = str.substring(0,str.length()-1);
        str = str + "]";
        String s = str.replace("'党委'", "a").replace("'人大'", "b").replace("'政府'", "c")
                .replace("'政协'", "d").replace("'民主党派'", "e").replace("'群众团体'", "f")
                .replace("'法院'", "g").replace("'检察院'", "h").replace("'经济实体'", "i")
                .replace("'街道办事处'", "j").replace("'乡'", "j").replace("'镇'", "l");
        System.out.println(str.replace("'","\"").replace(" ",""));
        renderText(str.replace("'","\"").replace(" ",""));
    }

    public void gg(){
        String str ="[";
        List<Record> szcs = Db.find("SELECT DISTINCT szcs FROM department" );
        for (Record s :szcs){
            str = str +"{'label':'"+s.getStr("szcs")+"','children': [";
            List<Record> dwzd = Db.find("SELECT DISTINCT dwzd FROM department WHERE szcs= '" + s.getStr("szcs") + "' " );
            for (Record d :dwzd){
                str = str +"{'label':'"+d.getStr("dwzd")+"','children': [";
                List<Record> dwlb =Db.find("SELECT DISTINCT dwlb FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' "  );
                for (Record b :dwlb){
                    str = str +"{'label':'"+b.getStr("dwlb")+"'},";
                }
                str = str + "],},";
            }
            str = str + "],},";
        }
        str = str + "]";
        System.out.println(str);
    }
}
