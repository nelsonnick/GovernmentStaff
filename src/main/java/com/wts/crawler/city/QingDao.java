package com.wts.crawler.city;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.city.JiNan.downPersonList;
import static com.wts.crawler.URL.QingDao;
/**
 * QingDao class
 *
 * @author wts
 * @date 2019/1/21
 */
public class QingDao {
    /**
     * 下载部门详情
     * base：基础网址
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwbh：单位编号
     * dwmc：单位名称
     * time：更新时间
     */
    public static void downDepartmentDetails(String base, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc_1, String time) {
        String xzPlanNum = "0";
        String xzRealNum = "0";
        String xzLoneNum = "0";
        String syPlanNum = "0";
        String syRealNum = "0";
        String syLoneNum = "0";
        String gqPlanNum = "0";
        String gqRealNum = "0";
        String gqLoneNum = "0";
        try {
            String url = getDepartmentUrl(base, dwbh);
            Document doc = getDoc(url);
            Element e = doc.getElementsByAttributeValue("id","Hidden1").first().nextElementSibling().getElementsByTag("table").first().getElementsByTag("tbody")
                    .first().getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").first().getElementsByTag("table").first().getElementsByTag("tbody")
                    .first();
            String dwmc = e.getElementsByTag("tr").first().getElementsByTag("td").last().text();
            String qtmc = e.getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").last().text();
            String ldzs = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
            String jb = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
            String nsjg = e.getElementById("lblNeiSheJG").text();
            String zyzz = e.getElementById("lblMainDuty").text();

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
                downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "行政编制");
                if(e.getElementById("RealXZ").wholeText().contains("(")) {
                    xzLoneNum = e.getElementById("RealXZ").ownText().substring(4,e.getElementById("RealXZ").ownText().length()-2);
                }
            }
            if(e.getElementById("RealSY") != null){
                syRealNum = e.getElementById("RealSY").getElementsByTag("a").first().text();
                downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "事业编制");
                if(e.getElementById("RealSY").wholeText().contains("(")) {
                    syLoneNum = e.getElementById("RealSY").ownText().substring(4,e.getElementById("RealSY").ownText().length()-2);
                }
            }
            if(e.getElementById("RealGQ") != null){
                gqRealNum = e.getElementById("RealGQ").getElementsByTag("a").first().text();
                downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "工勤编制");
                if(e.getElementById("RealGQ").wholeText().contains("(")) {
                    gqRealNum = e.getElementById("RealGQ").ownText().substring(4,e.getElementById("RealGQ").ownText().length()-2);
                }
            }
            saveDepartment(xzPlanNum, xzRealNum, xzLoneNum, syPlanNum, syRealNum, syLoneNum, gqPlanNum, gqRealNum, gqLoneNum,
                    szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, qtmc, ldzs, nsjg, zyzz, jb, url, time);
        } catch (IOException e) {
            saveDepartmentErr(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc_1, time);
        }
    }
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try{
            File file = new File(DIRECTION + "青岛");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = QingDao();
            createFile(getStructureStr(map.get("市直"), true),"青岛\\青岛");
            retractFile("青岛\\青岛");
            transFile("青岛\\青岛");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = QingDao();
            downWithFile(map.get("市直"), "青岛\\青岛", 7, 12, "QingDao");
        } catch (Exception e) {

        }
    }
}
