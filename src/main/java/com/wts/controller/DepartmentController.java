package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mchange.v2.cfg.PropertiesConfig;
import com.wts.entity.model.Department;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * DepartmentController class
 *
 * @author wts
 * @date 2018/12/13
 */
public class DepartmentController extends Controller {
    private static Logger logger = Logger.getLogger(Department.class);

    /**
     * 获取单位信息
     * dwbh：单位编号
     */
    public void get() {
        renderJson(Department.dao.findFirst("SELECT * FROM department WHERE dwbh=" + getPara("dwbh")));
    }

    /**
     * 获取下级部门数量
     * dwbh：单位编号
     */
    public void getUnderNum() {
        Long count = Db.queryLong("SELECT COUNT(*) FROM department WHERE sjdw = '" + getPara("dwbh") + "'");
        renderText(count.toString());
    }

    /**
     * 获取层级目录字符串
     */
    public static String getCascaderOptionsStr() {
        String str = "[";
        List<Record> szcs = Db.find("SELECT DISTINCT szcs FROM department");
        for (Record s : szcs) {
            str = str + "{'label':'" + s.getStr("szcs") + "','value':'" + s.getStr("szcs") + "','children': [";
            List<Record> dwzd = Db.find("SELECT DISTINCT dwzd FROM department WHERE szcs= '" + s.getStr("szcs") + "' ");
            for (Record d : dwzd) {
                str = str + "{'label':'" + d.getStr("dwzd") + "','value':'" + d.getStr("dwzd") + "','children': [";
                List<Record> dwlb = Db.find("SELECT DISTINCT dwlb FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' ");
                for (Record b : dwlb) {
                    str = str + "{'label':'" + b.getStr("dwlb") + "','value':'" + b.getStr("dwlb") + "','children': [";
                    List<Record> dwlx = Db.find("SELECT DISTINCT dwlx FROM department WHERE szcs= '" + s.getStr("szcs") + "' AND dwzd = '" + d.getStr("dwzd") + "' AND dwlb = '" + b.getStr("dwlb") + "' ");
                    for (Record x : dwlx) {
                        str = str + "{'label':'" + x.getStr("dwlx") + "','value':'" + x.getStr("dwlx") + "'},";
                    }
                    str = str.substring(0, str.length() - 1);
                    str = str + "]},";
                }
                str = str.substring(0, str.length() - 1);
                str = str + "]},";
            }
            str = str.substring(0, str.length() - 1);
            str = str + "]},";
        }
        str = str.substring(0, str.length() - 1);
        str = str + "]";
//        String s = str.replace("'党委'", "a").replace("'人大'", "b").replace("'政府'", "c")
//                .replace("'政协'", "d").replace("'民主党派'", "e").replace("'群众团体'", "f")
//                .replace("'法院'", "g").replace("'检察院'", "h").replace("'经济实体'", "i")
//                .replace("'街道办事处'", "j").replace("'乡'", "j").replace("'镇'", "l");


        return str.replace("'", "\"").replace(" ", "");

    }

    /**
     * 创建层级目录文件
     */
    public static void createCascaderOptions(){
        File file = new File("d:\\op.txt");
        if (!file.exists()) {
            try{
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("options=" + getCascaderOptionsStr());
                bw.flush();
                bw.close();
                fw.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    /**
     * 获取层级目录
     */
    public void getCascaderOptions() {
        File file = new File("d:\\op.txt");
        if (!file.exists()) {
            createCascaderOptions();
        }
        renderText(PropKit.use(file).get("options"));
    }

}
