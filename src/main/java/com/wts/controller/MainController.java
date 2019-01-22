package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

import static com.wts.crawler.city.JiNan.downDepartmentDetails;


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
            sqlExcptSelect = " FROM department " +getBaseSQL(szcs, dwzd,dwlb,dwlx) + " AND sjdw = '' ";
        }else if(type.equals("1")){
            sqlExcptSelect = " FROM department WHERE dwmc LIKE '%" + dwmc + "%' ";
        }else if(type.equals("2")){
            sqlExcptSelect = " FROM person LEFT JOIN department ON person.dwbh = department.dwbh WHERE person.ryxm LIKE '%" + ryxm + "%' ";
        }else if(type.equals("3")) {
            sqlExcptSelect = " FROM department WHERE department.sjdw = '" + sjdw + "' ";
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
     * dwlx：单位类型
     * sjdw：上级单位
     * dwmc：单位名称
     * ryxm：人员姓名
     */
    public void list() {
        String select = "SELECT DISTINCT department.id, department.dwlx, department.dwmc, department.dwbh";
        String sqlExcptSelect = getsqlExcptSelect(getPara("type"), getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), getPara("sjdw"), getPara("dwmc"), getPara("ryxm"))+" ORDER BY department.dwlx DESC";
        renderJson(Db.paginate(getParaToInt("pageCurrent"), getParaToInt("pageSize"), select, sqlExcptSelect).getList());
    }
    public void l() {
        try{
            downDepartmentDetails("http://218.56.49.18/","山东","省直","政府","行政机关","","037412475","省纪委驻省委办公厅纪检组","20190121");
//            downDepartmentDetails("http://sz.jnbb.gov.cn/smzgs/","济南","市中区","政府","行政机关","","037001003419","济南市市中区人民政府办公室","20190121");
//            downDepartmentDetails("http://120.221.95.1:1888/", "青岛", "市直", "政府", "行政机关", "", "037002000129", "青岛市人民政府办公厅", "2019年1月21日");
        }catch (Exception e){
            System.out.println(e);
        }
        renderText("111111111111");
    }
    /**
     * 获取单位数量
     * type：搜索类型0逐级查看1部门模糊搜索2人员模糊搜索3下级精准搜索
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwmc：单位名称
     * ryxm：人员姓名
     */
    public void total() {
        String sqlExcptSelect = getsqlExcptSelect(getPara("type"), getPara("szcs"), getPara("dwzd"), getPara("dwlb"), getPara("dwlx"), getPara("sjdw"), getPara("dwmc"), getPara("ryxm"));
        Long count = Db.queryLong("SELECT COUNT(*)" + sqlExcptSelect);
        renderText(count.toString());
    }

    /**
     * 获取基础SQL字符串
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     */
    public static String getBaseSQL(String szcs, String dwzd, String dwlb, String dwlx){
        String sqlExcptSelect = " WHERE ";
        if (szcs.equals("")) {
            sqlExcptSelect = sqlExcptSelect + "szcs LIKE '%" + szcs + "%' ";
        } else {
            sqlExcptSelect = sqlExcptSelect + "szcs = '" + szcs + "' ";
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
        return sqlExcptSelect;
    }

    /**
     * 获取单位数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     */
    public static Long getDepartmentNum(String szcs, String dwzd, String dwlb, String dwlx){
        return Db.queryLong("SELECT COUNT(*) FROM department" + getBaseSQL(szcs, dwzd,dwlb,dwlx));
    }
    /**
     * 获取单位人员数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     */
    public static Long getDepartmentPersonNum(String szcs, String dwzd, String dwlb, String dwlx){
        return Db.queryLong("SELECT COUNT(*) FROM person " + getBaseSQL(szcs, dwzd,dwlb,dwlx));
    }
    /**
     * 获取人员数量
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * ryxb：人员性别：空白、男、女
     * bzlx：编制类型：空白、行政编制、事业编制、工勤编制
     */
    public static Long getPersonNum(String szcs, String dwzd, String dwlb, String dwlx, String ryxb, String bzlx){
        String sql = "";
        if (!ryxb.equals("")) {
            sql = sql + " AND person.ryxb = '" + ryxb + "' ";
        }
        if (!bzlx.equals("")) {
            sql = sql + " AND person.bzlx = '" + bzlx + "' ";
        }
        return Db.queryLong("SELECT COUNT(*) FROM person " + getBaseSQL(szcs, dwzd,dwlb,dwlx)+sql);
    }
    /**
     * 根据列名获取单位计数
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * column：列名：ldzs、xz_plan_num、xz_real_num、xz_lone_num、sy_plan_num、sy_real_num、sy_lone_num、gq_plan_num、gq_real_num、gq_lone_num
     */
    public String getTotal_column(String szcs, String dwzd, String dwlb, String dwlx, String column){
        return Db.queryLong("SELECT SUM(" + column + ") FROM department" + getBaseSQL(szcs, dwzd,dwlb,dwlx)).toString();
    }
    /**
     * 根据类型获取单位计数
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * column_plan：列名：xz_plan_num、sy_plan_num、gq_plan_num
     * column_real：列名：xz_real_num、sy_real_num、gq_real_num
     * type：类型：<已超编、>有空编、=编制满
     */
    public static String getTotalType(String szcs, String dwzd, String dwlb, String dwlx, String column_plan, String column_real, String type){
        String a = "";
        if (type.equals("=")){
            a=" AND "+ column_plan+ "!=0 ";
        }
        return Db.queryLong("SELECT SUM(" + column_plan + "-" + column_real + type+"0" + a + ") FROM department" + getBaseSQL(szcs, dwzd,dwlb,dwlx)).toString();
    }

}
