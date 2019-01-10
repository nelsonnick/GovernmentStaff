package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

/**
 * MainController class
 *
 * @author wts
 * @date 2019/1/10
 */
public class MainController extends Controller {

    /**
     * 获取SQL字符串
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * ryxm：人员姓名
     */
    public String getsqlExcptSelect(String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String ryxm){
        String sqlExcptSelect = "";
        if (ryxm.equals("")) {
            sqlExcptSelect = "FROM department ";
            if(sjdw.equals("")){
                if (szcs.equals("")) {
                    sqlExcptSelect = sqlExcptSelect + "WHERE szcs LIKE '%" + szcs + "%' ";
                } else {
                    sqlExcptSelect = sqlExcptSelect + "WHERE szcs = '" + szcs + "' ";
                }
                if (dwzd.equals("")) {
                    sqlExcptSelect = sqlExcptSelect + "AND dwzd LIKE '%" + dwzd + "%' ";
                } else {
                    sqlExcptSelect = sqlExcptSelect + "AND dwzd = '" + dwzd + "' ";
                }
                if (dwlb.equals("")) {
                    sqlExcptSelect = sqlExcptSelect + "AND dwlb LIKE '%" + dwlb + "%' ";
                } else {
                    sqlExcptSelect = sqlExcptSelect + "AND dwlb = '" + dwlb + "' ";
                }
                if (dwlx.equals("")) {
                    sqlExcptSelect = sqlExcptSelect + "AND dwlx LIKE '%" + dwlx + "%' ";
                } else {
                    sqlExcptSelect = sqlExcptSelect + "AND dwlx = '" + dwlx + "' ";
                }
                sqlExcptSelect = sqlExcptSelect + " AND sjdw = '' ";
            }else{
                sqlExcptSelect = sqlExcptSelect + " WHERE sjdw = '" + sjdw + "' ";
            }

        }else{
            sqlExcptSelect = " FROM person LEFT JOIN department ON person.dwbh = department.dwbh ";
            sqlExcptSelect = sqlExcptSelect + "WHERE person.ryxm LIKE '%" + ryxm + "%' ";
        }
        return sqlExcptSelect;
    }
    /**
     * 获取单位列表
     * pageCurrent:当前页
     * pageSize：数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * ryxm：人员姓名
     */
    public void list() {
        String select = "SELECT DISTINCT department.id, department.dwlx, department.dwmc, department.dwbh";
        String sqlExcptSelect = getsqlExcptSelect(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),getPara("sjdw"),getPara("ryxm"));
        renderJson(Db.paginate(getParaToInt("pageCurrent"),getParaToInt("pageSize"),select, sqlExcptSelect).getList());
    }
    /**
     * 获取单位数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类别
     * sjdw：上级单位
     * ryxm：人员姓名
     */
    public void total() {
        String sqlExcptSelect = getsqlExcptSelect(getPara("szcs"),getPara("dwzd"),getPara("dwlb"),getPara("dwlx"),getPara("sjdw"),getPara("ryxm"));
        Long count = Db.queryLong("SELECT COUNT(*)" + sqlExcptSelect);
        renderText(count.toString());
    }
}
