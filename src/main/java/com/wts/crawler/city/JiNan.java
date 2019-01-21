package com.wts.crawler.city;

import com.wts.entity.model.Department;
import com.wts.entity.model.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.wts.crawler.Common.getDepartmentUrl;
import static com.wts.crawler.Common.getPersonUrl;
/**
 * JiNan class
 *
 * @author wts
 * @date 2019/1/21
 */
public class JiNan {

    /**
     * 获取网页数据
     * url：网址
     */
    public static Document getDoc(String url) throws Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("DNT", "1")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        return doc;
    }
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
    public static void downDepartmentDetails(String baseURL, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc_1, String time) throws Exception{
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
        String url = getDepartmentUrl(baseURL,dwbh);
        Document doc = getDoc(url);
        Element e = doc.getElementsByAttributeValue("style","width: 757; height: 582; background-color: #EFF8FF;").first()
                .getElementsByTag("table").first().getElementsByTag("tr").first().nextElementSibling()
                .getElementsByTag("td").first().getElementsByTag("table").first();
        dwmc = e.getElementsByTag("tr").first().getElementsByTag("td").first().nextElementSibling().text();
        qtmc = e.getElementsByTag("tr").first().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
        ldzs = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().text();
        jb = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
        nsjg = e.getElementById("lblNeiSheJG").text();
        zyzz = e.getElementById("lblMainDuty").text();
        if (qtmc.equals("空")){
            qtmc = "";
        }
        if (nsjg.equals("\'")){
            nsjg = "";
        }
        if (zyzz.equals("\'")){
            zyzz = "";
        }
        Element tbody = e.getElementsByTag("tr").first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
                .getElementsByTag("td").first().getElementsByTag("div").first().getElementsByTag("table").first().getElementsByTag("tbody").first();
        Elements elements = tbody.getElementsByTag("tr");
        for (Element element:elements) {
            if (element.getElementsByTag("td").first().text().contains("行政")){
                xzPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                Element xzReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                if (xzReal.getElementsByTag("a").size()==2){
                    xzRealNum = xzReal.getElementsByTag("a").first().text();
                    xzLoneNum = xzReal.getElementsByTag("a").last().text();
                }else{
                    xzRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                }
                bzlx = "行政编制";
            }else if(element.getElementsByTag("td").first().text().contains("事业")){
                syPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                Element syReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                if (syReal.getElementsByTag("a").size()==2){
                    syRealNum = syReal.getElementsByTag("a").first().text();
                    syLoneNum = syReal.getElementsByTag("a").last().text();
                }else{
                    syRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                }
                bzlx = "事业编制";
            }else if(element.getElementsByTag("td").first().text().contains("工勤")){
                gqPlanNum = element.getElementsByTag("td").first().nextElementSibling().text();
                Element gqReal = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling();
                if (gqReal.getElementsByTag("a").size()==2){
                    gqRealNum = gqReal.getElementsByTag("a").first().text();
                    gqLoneNum = gqReal.getElementsByTag("a").last().text();
                }else{
                    gqRealNum = element.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
                }
                bzlx = "工勤编制";
            }else{

            }
            downPersonList(baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, bzlx);
        }
        if (xzPlanNum.equals("&nbsp;") || xzPlanNum.equals("")){
            xzPlanNum = "0";
        }
        if (xzRealNum.equals("&nbsp;") || xzRealNum.equals("")){
            xzRealNum = "0";
        }
        if (xzLoneNum.equals("&nbsp;") || xzLoneNum.equals("")){
            xzLoneNum = "0";
        }
        if (syPlanNum.equals("&nbsp;") || syPlanNum.equals("")){
            syPlanNum = "0";
        }
        if (syRealNum.equals("&nbsp;") || syRealNum.equals("")){
            syRealNum = "0";
        }
        if (syLoneNum.equals("&nbsp;") || syLoneNum.equals("")){
            syLoneNum = "0";
        }
        if (gqPlanNum.equals("&nbsp;") || gqPlanNum.equals("")){
            gqPlanNum = "0";
        }
        if (gqRealNum.equals("&nbsp;") || gqRealNum.equals("")){
            gqRealNum = "0";
        }
        if (gqLoneNum.equals("&nbsp;") || gqLoneNum.equals("")){
            gqLoneNum = "0";
        }
        new Department()
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
                .set("time", time)
                .save();
    }
    /**
     * 获取人员
     * cols：列名
     * row：行
     */
    public static Person getPerson(List<String> cols,List<String> row){
        String dwmc = "";
        String ryxm = "";
        String ryxb = "";
        String ssbm = "";
        String zybzqk = "";
        if (cols.contains("单位")){
            dwmc = row.get(cols.indexOf("单位"));
        }
        if (cols.contains("姓名")){
            ryxm = row.get(cols.indexOf("姓名"));
        }
        if (cols.contains("性别")){
            ryxb = row.get(cols.indexOf("性别"));
        }
        if (cols.contains("部门")){
            ssbm = row.get(cols.indexOf("部门"));
        }
        if (cols.contains("占用编制情况")){
            zybzqk = row.get(cols.indexOf("占用编制情况"));
        }
        return new Person().set("dwmc",dwmc).set("ryxm",ryxm).set("ryxb",ryxb).set("ssbm",ssbm).set("zybzqk",zybzqk);
    }
    /**
     * 下载人员详情
     * baseURL：基础网址
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwbh：单位编号
     * dwmc：单位名称
     * bzlx：编制类型
     */
    public static void downPersonList(String baseURL, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc, String bzlx) throws Exception{

        String url = getPersonUrl(baseURL, dwbh, bzlx, false);
        System.out.println(url);
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
            if (cols.size()==4){
                row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text());
            }else if(cols.size()==5){
                row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().text());
                row.add(tr.getElementsByTag("td").first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
            }else{

            }
            Person person = getPerson(cols,row);
            person.set("szcs",szcs).set("dwzd",dwzd).set("dwlb",dwlb).set("dwlx",dwlx).set("sjdw",sjdw).set("bzlx",bzlx);
            System.out.println(person.toString());
        }



    }

    public static void main(String[] args) throws Exception{
        downPersonList("http://jnbb.gov.cn/smzgs/","","","","","","037001000402418","","事业编制");
    }
}
