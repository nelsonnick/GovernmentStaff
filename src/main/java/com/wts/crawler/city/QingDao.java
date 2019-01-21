package com.wts.crawler.city;

import com.wts.entity.model.Department;
import com.wts.entity.model.DepartmentErr;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import static com.wts.crawler.Common.getDepartmentUrl;
import static com.wts.crawler.Common.getDoc;
import static com.wts.crawler.city.JiNan.downPersonList;
/**
 * QingDao class
 *
 * @author wts
 * @date 2019/1/21
 */
public class QingDao {
    /**
     * 下载部门详情
     * baseURL：基础网址
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwbh：单位编号
     * dwmc：单位名称
     * time：更新时间
     */
    public static void downDepartmentDetails(String baseURL, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc_1, String time) {
        String xzPlanNum = "0";
        String xzRealNum = "0";
        String xzLoneNum = "0";
        String syPlanNum = "0";
        String syRealNum = "0";
        String syLoneNum = "0";
        String gqPlanNum = "0";
        String gqRealNum = "0";
        String gqLoneNum = "0";
        String dwmc = "";
        String qtmc = "";
        String ldzs = "";
        String jb = "";
        String nsjg = "";
        String zyzz = "";
        String bzlx = "";
        try {
            String url = getDepartmentUrl(baseURL, dwbh);
            Document doc = getDoc(url);
            Element e = doc.getElementsByAttributeValue("id","Hidden1").first().nextElementSibling().getElementsByTag("table").first().getElementsByTag("tbody")
                    .first().getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").first().getElementsByTag("table").first().getElementsByTag("tbody")
                    .first();
            dwmc = e.getElementsByTag("tr").first().getElementsByTag("td").last().text();
            qtmc = e.getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").last().text();
            ldzs = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
            jb = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
            nsjg = e.getElementById("lblNeiSheJG").text();
            zyzz = e.getElementById("lblMainDuty").text();
            if (qtmc.equals("空")) {
                qtmc = "";
            }
            if (nsjg.equals("\'")) {
                nsjg = "";
            }
            if (zyzz.equals("\'")) {
                zyzz = "";
            }
            if(e.getElementById("LabelXZ") != null){
                xzPlanNum = e.getElementById("LabelXZ").text();
            }
            if(e.getElementById("LabelSY") != null){
                syPlanNum = e.getElementById("LabelSY").text();
            }
            if(e.getElementById("LabelGQ") != null){
                gqPlanNum = e.getElementById("LabelGQ").text();
            }
            if(e.getElementById("RealXZ") != null){
                xzRealNum = e.getElementById("RealXZ").getElementsByTag("a").first().text();
                downPersonList(baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "行政编制");
                if(e.getElementById("RealXZ").wholeText().contains("(")) {
                    xzLoneNum = e.getElementById("RealXZ").ownText().substring(4,e.getElementById("RealXZ").ownText().length()-2);
                }
            }
            if(e.getElementById("RealSY") != null){
                syRealNum = e.getElementById("RealSY").getElementsByTag("a").first().text();
                downPersonList(baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "事业编制");
                if(e.getElementById("RealSY").wholeText().contains("(")) {
                    syLoneNum = e.getElementById("RealSY").ownText().substring(4,e.getElementById("RealSY").ownText().length()-2);
                }
            }
            if(e.getElementById("RealGQ") != null){
                gqRealNum = e.getElementById("RealGQ").getElementsByTag("a").first().text();
                downPersonList(baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "工勤编制");
                if(e.getElementById("RealGQ").wholeText().contains("(")) {
                    gqRealNum = e.getElementById("RealGQ").ownText().substring(4,e.getElementById("RealGQ").ownText().length()-2);
                }
            }
            if (xzPlanNum.equals("&nbsp;") || xzPlanNum.equals("")) {
                xzPlanNum = "0";
            }
            if (xzRealNum.equals("&nbsp;") || xzRealNum.equals("")) {
                xzRealNum = "0";
            }
            if (xzLoneNum.equals("&nbsp;") || xzLoneNum.equals("")) {
                xzLoneNum = "0";
            }
            if (syPlanNum.equals("&nbsp;") || syPlanNum.equals("")) {
                syPlanNum = "0";
            }
            if (syRealNum.equals("&nbsp;") || syRealNum.equals("")) {
                syRealNum = "0";
            }
            if (syLoneNum.equals("&nbsp;") || syLoneNum.equals("")) {
                syLoneNum = "0";
            }
            if (gqPlanNum.equals("&nbsp;") || gqPlanNum.equals("")) {
                gqPlanNum = "0";
            }
            if (gqRealNum.equals("&nbsp;") || gqRealNum.equals("")) {
                gqRealNum = "0";
            }
            if (gqLoneNum.equals("&nbsp;") || gqLoneNum.equals("")) {
                gqLoneNum = "0";
            }
            Department department = new Department()
                    .set("szcs", szcs)
                    .set("dwzd", dwzd)
                    .set("dwlb", dwlb)
                    .set("dwlx", dwlx)
                    .set("sjdw", sjdw)
                    .set("dwbh", dwbh)
                    .set("dwmc", dwmc)
                    .set("qtmc", qtmc)
                    .set("ldzs", ldzs)
                    .set("jb", jb)
                    .set("nsjg", nsjg)
                    .set("zyzz", zyzz)
                    .set("xz_plan_num", xzPlanNum)
                    .set("xz_real_num", xzRealNum)
                    .set("xz_lone_num", xzLoneNum)
                    .set("sy_plan_num", syPlanNum)
                    .set("sy_real_num", syRealNum)
                    .set("sy_lone_num", syLoneNum)
                    .set("gq_plan_num", gqPlanNum)
                    .set("gq_real_num", gqRealNum)
                    .set("gq_lone_num", gqLoneNum)
                    .set("url", url)
                    .set("time", time);
            department.save();
        } catch (IOException e) {
            DepartmentErr departmentErr = new DepartmentErr();
            departmentErr.set("szcs", szcs).set("dwzd", dwzd).set("dwlb", dwlb).set("dwlx", dwlx).set("dwbh", dwbh).set("sjdw", sjdw).set("dwmc", dwmc_1).set("base", baseURL).set("time", time).save();
        }
    }

}
