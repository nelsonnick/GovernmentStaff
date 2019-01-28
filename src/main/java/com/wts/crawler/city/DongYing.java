package com.wts.crawler.city;

import com.wts.entity.model.Person;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.*;

/**
 * DongYing class
 * 东营的网页结构十分特别，分为行政编制数+单列行政编制数，要特别处理
 * @author wts
 * @date 2019/1/23
 */
public class DongYing {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "东营");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = DongYing();
            for (Map.Entry<String, String> key : map.entrySet()) {
                createFile(getStructureStr(key.getValue(), true), "东营\\" + key.getKey());
                transFile("东营\\" + key.getKey());
            }
            addCity("东营");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = DongYing();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "东营\\"+key.getKey(), 9, 12, "DongYing");
            }
        } catch (Exception e) {

        }
    }
    /**
     * 下载部门详情
     * 特别说明：东营没有其他名称、领导职数和人员性别
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
        String qtmc = "";
        String ldzs = "";
        try {
            String url = getDepartmentUrl(base, dwbh);
            Document doc = getDoc(url);
            String dwmc = doc.getElementById("lblUnitName").text();
            String jb = doc.getElementById("lblUnitGuiGe").text();
            String nsjg = doc.getElementById("lblNeiSheJG").text();
            String zyzz = doc.getElementById("lblMainDuty").text();
            Element table = doc.getElementById("GridView1");
            if (table !=null) {
                Elements elements = table.getElementsByTag("tr");
                for (Element element : elements) {
                    if (element.getElementsByTag("td").first().text().equals("行政编制数")) {
                        xzPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        xzRealNum = element.getElementsByTag("td").last().text();
                    }
                    if (element.getElementsByTag("td").first().text().equals("单列行政编制数")) {
                        xzPlanNum = Integer.parseInt(element.getElementsByTag("td").first().nextElementSibling().text()) + Integer.parseInt(xzPlanNum) + "";
                        xzLoneNum = element.getElementsByTag("td").last().text();
                    }
                    if (element.getElementsByTag("td").first().text().equals("事业编制数")) {
                        syPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        syRealNum = element.getElementsByTag("td").last().text();
                    }
                    if (element.getElementsByTag("td").first().text().equals("单列事业编制数")) {
                        syPlanNum = Integer.parseInt(element.getElementsByTag("td").first().nextElementSibling().text()) + Integer.parseInt(syPlanNum) + "";
                        syLoneNum = element.getElementsByTag("td").last().text();
                    }

                    if (element.getElementsByTag("td").first().text().equals("工勤编制数")) {
                        gqPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        gqRealNum = element.getElementsByTag("td").last().text();
                    }
                    if (element.getElementsByTag("td").first().text().equals("单列工勤编制数")) {
                        gqPlanNum = Integer.parseInt(element.getElementsByTag("td").first().nextElementSibling().text()) + Integer.parseInt(gqPlanNum) + "";
                        gqLoneNum = element.getElementsByTag("td").last().text();
                    }
                    if (!checkNum(xzRealNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "行政编制");
                    }
                    if (!checkNum(xzLoneNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "单列行政编制");
                    }
                    if (!checkNum(syRealNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "事业编制");
                    }
                    if (!checkNum(syLoneNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "单列事业编制");
                    }
                    if (!checkNum(gqRealNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "工勤编制");
                    }
                    if (!checkNum(gqLoneNum).equals("0")) {
                        downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "单列工勤编制");
                    }
                }
            }
            saveDepartment(xzPlanNum, xzRealNum, xzLoneNum, syPlanNum, syRealNum, syLoneNum, gqPlanNum, gqRealNum, gqLoneNum,
                    szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, qtmc, ldzs, nsjg, zyzz, jb, url, time);
        } catch (IOException e) {
            saveDepartmentErr(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc_1, time);
        }
    }
    /**
     * 下载人员详情
     * base：基础网址
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwbh：单位编号
     * dwmc：单位名称
     * bzlx：编制类型
     */
    public  static void downPersonList(String base, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc, String bzlx) {
        try {
            String url = getPersonUrl(base, dwbh, bzlx, false);
            String bz = "";
            if (bzlx.contains("单列")){
                bz = bzlx.substring(2,bzlx.length());
            }else{
                bz = bzlx;
            }
            Document doc = getDoc(url);
            Element e = doc.getElementById("GVPersonList");
            Elements ths = e.getElementsByAttributeValue("class", "ListHead").first().getElementsByTag("th");
            List<String> cols = new ArrayList<>();
            for (Element th : ths) {
                cols.add(th.text());
            }
            e.getElementsByAttributeValue("class", "ListHead").first().remove();
            Element tbody = e.getElementsByTag("tbody").first();
            Elements trs = tbody.getElementsByTag("tr");
            for (Element tr : trs) {
                List<String> row = new ArrayList<>();
                row.add(tr.getElementsByTag("td").first().text());
                row.add(tr.getElementsByTag("td").first().nextElementSibling().text());
                row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().text());
                if (cols.size() == 4) {
                    row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text());
                } else if (cols.size() == 5) {
                    row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text());
                    row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
                } else {

                }
                Person person = getPerson(cols, row);
                person.set("szcs", szcs).set("dwzd", dwzd).set("dwlb", dwlb).set("dwlx", dwlx).set("sjdw", sjdw).set("dwbh", dwbh).set("bzlx", bz).save();
            }
        } catch (IOException e) {
            savePersonErr(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, bzlx);
        }
    }
    public static void main(String[] args) {
        downDepartmentDetails("http://dysmz.hisofter.com:2021/","东营","市直","政府","行政机关","","037005000527004","市委巡察组一至五组","2018年11月28日");
    }
}
