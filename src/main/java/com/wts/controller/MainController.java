package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * MainController class
 *
 * @author wts
 * @date 2019/1/10
 */
public class MainController extends Controller {

    /**
     * 获取SQL字符串
     * type：搜索类型0逐级查看1部门模糊搜索2人员模糊搜索3下级精准搜索
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * dwmc：单位名称
     * ryxm：人员姓名
     */
    public String getsqlExcptSelect(String type, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwmc, String ryxm) {
        String sqlExcptSelect;
        if (type.equals("0")){
            sqlExcptSelect = " FROM department ";
            if (szcs.equals("")) {
                sqlExcptSelect = sqlExcptSelect + " WHERE szcs LIKE '%" + szcs + "%' ";
            } else {
                sqlExcptSelect = sqlExcptSelect + " WHERE szcs = '" + szcs + "' ";
            }
            if (dwzd.equals("")) {
                sqlExcptSelect = sqlExcptSelect + " AND dwzd LIKE '%" + dwzd + "%' ";
            } else {
                sqlExcptSelect = sqlExcptSelect + " AND dwzd = '" + dwzd + "' ";
            }
            if (dwlb.equals("")) {
                sqlExcptSelect = sqlExcptSelect + " AND dwlb LIKE '%" + dwlb + "%' ";
            } else {
                sqlExcptSelect = sqlExcptSelect + " AND dwlb = '" + dwlb + "' ";
            }
            if (dwlx.equals("")) {
                sqlExcptSelect = sqlExcptSelect + " AND dwlx LIKE '%" + dwlx + "%' ";
            } else {
                sqlExcptSelect = sqlExcptSelect + " AND dwlx = '" + dwlx + "' ";
            }
            sqlExcptSelect = sqlExcptSelect + " AND sjdw = '' ";
        }else if(type.equals("1")){
            sqlExcptSelect = " FROM department WHERE dwmc LIKE '%" + dwmc + "%' ";
        }else if(type.equals("2")){
            sqlExcptSelect = " FROM person LEFT JOIN department ON person.dwbh = department.dwbh WHERE person.ryxm LIKE '%" + ryxm + "%' ";
        }else if(type.equals("3")) {
            sqlExcptSelect = " FROM department WHERE sjdw = '" + sjdw + "' ";
        }else{
            sqlExcptSelect = "";
        }
        return sqlExcptSelect;
    }

    /**
     * 获取单位列表
     * pageCurrent:当前页
     * pageSize：数量
     * type：搜索类型0逐级查看1部门模糊搜索2人员模糊搜索3下级精准搜索
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * dwmc：单位名称
     * ryxm：人员姓名
     */
    public void list() {
        String select = "SELECT DISTINCT department.id, department.dwlx, department.dwmc, department.dwbh";
        String sqlExcptSelect = getsqlExcptSelect(getPara("type"), getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), getPara("sjdw"), getPara("dwmc"), getPara("ryxm"));
        renderJson(Db.paginate(getParaToInt("pageCurrent"), getParaToInt("pageSize"), select, sqlExcptSelect).getList());
    }

    /**
     * 获取单位数量
     * type：搜索类型0逐级查看1部门模糊搜索2人员模糊搜索3下级精准搜索
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * dwmc：单位名称
     * ryxm：人员姓名
     */
    public void total() {
        String sqlExcptSelect = getsqlExcptSelect(getPara("type"), getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), getPara("sjdw"), getPara("dwmc"), getPara("ryxm"));
        Long count = Db.queryLong("SELECT COUNT(*)" + sqlExcptSelect);
        renderText(count.toString());
    }
}
