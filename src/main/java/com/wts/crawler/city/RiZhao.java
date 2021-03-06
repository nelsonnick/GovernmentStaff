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
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.RiZhao;

/**
 * RiZhao class
 * 跟济南基本相似，但是person表里面用的是代码格式 ，不是文本！！！
 * @author wts
 * @date 2019/1/22
 */
public class RiZhao {
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
            Element e = doc.getElementsByAttributeValue("style", "width: 757; height: 582; background-color: #EFF8FF;").first()
                    .getElementsByTag("table").first().getElementsByTag("tr").first().nextElementSibling()
                    .getElementsByTag("td").first().getElementsByTag("table").first();
            String dwmc = e.getElementsByTag("tr").first().getElementsByTag("td").first().nextElementSibling().text();
            String qtmc = e.getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
            String ldzs = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
            String jb = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
            String nsjg = e.getElementById("lblNeiSheJG").text();
            String zyzz = e.getElementById("lblMainDuty").text();
            Element div = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
                    .getElementsByTag("td").first().getElementsByTag("div").first();
            if (div.getElementsByTag("table").size()!=0) {
                Element tbody = div.getElementsByTag("table").first().getElementsByTag("tbody").first();
                Elements elements = tbody.getElementsByTag("tr");
                for (Element element : elements) {
                    if (element.getElementsByTag("td").first().text().contains("行政")) {
                        xzPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        Element xzReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                        if (xzReal.getElementsByTag("a").size() == 2) {
                            xzRealNum = xzReal.getElementsByTag("a").first().text();
                            xzLoneNum = xzReal.getElementsByTag("a").last().text();
                        } else {
                            xzRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                        }
                        if (!checkNum(xzRealNum).equals("0")) {
                            downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "行政编制");
                        }
                    } else if (element.getElementsByTag("td").first().text().contains("事业")) {
                        syPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        Element syReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                        if (syReal.getElementsByTag("a").size() == 2) {
                            syRealNum = syReal.getElementsByTag("a").first().text();
                            syLoneNum = syReal.getElementsByTag("a").last().text();
                        } else {
                            syRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                        }
                        if (!checkNum(syRealNum).equals("0")) {
                            downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "事业编制");
                        }
                    } else if (element.getElementsByTag("td").first().text().contains("工勤")) {
                        gqPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                        Element gqReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                        if (gqReal.getElementsByTag("a").size() == 2) {
                            gqRealNum = gqReal.getElementsByTag("a").first().text();
                            gqLoneNum = gqReal.getElementsByTag("a").last().text();
                        } else {
                            gqRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                        }
                        if (!checkNum(gqRealNum).equals("0")) {
                            downPersonList(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, "工勤编制");
                        }
                    } else {

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
            String url = getPersonUrl(base, dwbh, bzlx, true);
            Document doc = getDocWithNot(url);
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
                person.set("szcs", szcs).set("dwzd", dwzd).set("dwlb", dwlb).set("dwlx", dwlx).set("sjdw", sjdw).set("dwbh", dwbh).set("bzlx", bzlx).save();
            }
        } catch (IOException e) {
            savePersonErr(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, bzlx);
        }
    }

    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "日照");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = RiZhao();
            createFile(getStructureStr(map.get("市直"), false),"日照\\日照");
            transFile("日照\\日照");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = RiZhao();
            downWithFile(map.get("市直"), "日照\\日照", 9, 12, "RiZhao");
        } catch (Exception e) {

        }
    }
}
