package com.wts.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common class
 *
 * @author wts
 * @date 2019/1/20
 */
public class Common {
    /**
     * 获取结构字符串
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * isHtml：是否HTML：济南、青岛、淄博、枣庄、东营、潍坊、泰安、威海、滨州、德州、聊城、临沂、菏泽TRUE,省直、烟台、日照FALSE
     */
    private static String get_structure_str(String baseURL, Boolean isHtml) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.get(baseURL + "TreeViewPage.aspx");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Document doc = Jsoup.parse(driver.getPageSource());
        if (isHtml){
        Element e =doc.getElementsByTag("body").first().getElementsByTag("form").first().getElementsByTag("table").first().getElementsByTag("tbody")
                .first().getElementsByTag("tr").first().getElementsByTag("td").first().getElementsByTag("div").first();
        e.removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("div").removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("a").removeAttr("id").removeAttr("style").removeAttr("class").removeAttr("title");
        e.select("table").removeAttr("cellpadding").removeAttr("cellspacing").removeAttr("style").removeAttr("class");
        e.select("td").removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("img").remove();
        Elements elements = e.select("a");
        for (Element element:elements) {
            if (element.attr("href").contains("JavaScript:doNothing(") || element.attr("href").contains("TreeView_ToggleNode")){
                element.removeAttr("href");
            }
            if (element.attr("href").contains("JavaScript:f('")){
                String h = element.attr("href").split("\'")[1]+ '-';
                element.removeAttr("href");
                element.attr("href",h);
            }
        }
        String str = e.toString();
        driver.quit();//退出浏览器
        String s = str.replace("\n", "").replace("\t", "").replace(" ", "")
                .replace("<a></a>", "").replace("<div>", "").replace("</div>", "")
                .replace("<td></td>", "\t").replace("<td><a", "\t").replace("</a></td>", "\n")
                .replace("<tr>", "").replace("</tr>", "")
                .replace("<table>", "").replace("</table>", "")
                .replace("<tbody>", "").replace("</tbody>", "")
                .replace("href=\"", "").replace("\">", "").replace(">", "");
        return s;}
        else{
            driver.quit();//退出浏览器
            String pattern = "var zNodes =.+?;\n";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(doc.toString());
            if (m.find( )) {
                String st = m.group(0).replace(",\"icon\":\"image/Department/1.png\"","").replace(",\"icon\":\"image/Department/2.png\"","")
                        .replace(",\"icon\":\"image/Department/3.png\"","").replace(",\"icon\":\"image/Department/4.png\"","")
                        .replace(",\"icon\":\"image/Department/5.png\"","").replace(",\"icon\":\"image/Department/6.png\"","")
                        .replace(",\"icon\":\"image/Department/7.png\"","").replace(",\"icon\":\"image/Department/8.png\"","")
                        .replace(",\"icon\":\"image/Department/9.png\"","").replace(",\"icon\":\"image/Department/10.png\"","")
                        .replace(",\"icon\":\"image/Department/11.png\"","").replace(",\"icon\":\"image/Department/12.png\"","")
                        .replace(",\"icon\":\"image/Department/13.png\"","").replace(",\"icon\":\"image/Department/14.png\"","")
                        .replace(",\"icon\":\"image/Department/15.png\"","").replace("var zNodes = ","")
                        .replace(",\"rn\":\"0\"","").replace(",\"rn\":\"1\"","").replace(",\"rn\":\"2\"","");
                String tt =st.substring(1,st.length()-3);
                JSONObject js = JSON.parseObject(tt);
                String out = "\t"+js.getString("id")+"-"+js.getString("name");
                JSONArray arrayA = js.getJSONArray("children");
                for (int a = 0; a < arrayA.size(); a++) {
                    out = out + "\n\t\t" + arrayA.getJSONObject(a).getString("id") + "-" + arrayA.getJSONObject(a).getString("name");
                    JSONArray arrayB = arrayA.getJSONObject(a).getJSONArray("children");
                    if (arrayB !=null){
                        for (int b = 0; b < arrayB.size(); b++) {
                            out = out + "\n\t\t\t" + arrayB.getJSONObject(b).getString("id") + "-" + arrayB.getJSONObject(b).getString("name");
                            JSONArray arrayC = arrayB.getJSONObject(b).getJSONArray("children");
                            if (arrayC !=null){
                                for (int c = 0; c < arrayC.size(); c++) {
                                    out = out + "\n\t\t\t\t" + arrayC.getJSONObject(c).getString("id") + "-" + arrayC.getJSONObject(c).getString("name");
                                    JSONArray arrayD = arrayC.getJSONObject(c).getJSONArray("children");
                                    if (arrayD !=null){
                                        for (int d = 0; d < arrayD.size(); d++) {
                                            out = out + "\n\t\t\t\t\t" + arrayD.getJSONObject(d).getString("id") + "-" + arrayD.getJSONObject(d).getString("name");
                                            JSONArray arrayE = arrayD.getJSONObject(d).getJSONArray("children");
                                            if (arrayE !=null){
                                                for (int e = 0; e < arrayE.size(); e++) {
                                                    out = out + "\n\t\t\t\t\t\t" + arrayE.getJSONObject(e).getString("id") + "-" + arrayE.getJSONObject(e).getString("name");
                                                    JSONArray arrayF = arrayE.getJSONObject(e).getJSONArray("children");
                                                    if (arrayF !=null){
                                                        for (int f = 0; f < arrayF.size(); f++) {
                                                            out = out + "\n\t\t\t\t\t\t\t" + arrayF.getJSONObject(f).getString("id") + "-" + arrayF.getJSONObject(f).getString("name");
                                                            JSONArray arrayG = arrayF.getJSONObject(f).getJSONArray("children");
                                                            if (arrayG !=null){
                                                                for (int g = 0; g < arrayG.size(); g++) {
                                                                    out = out + "\n\t\t\t\t\t\t\t\t" + arrayG.getJSONObject(g).getString("id") + "-" + arrayG.getJSONObject(g).getString("name");
                                                                    JSONArray arrayH = arrayG.getJSONObject(g).getJSONArray("children");
                                                                    if (arrayH !=null){
                                                                        for (int h = 0; h < arrayH.size(); h++) {
                                                                            out = out + "\n\t\t\t\t\t\t\t\t\t" + arrayH.getJSONObject(h).getString("id") + "-" + arrayH.getJSONObject(h).getString("name");
                                                                            JSONArray arrayI = arrayH.getJSONObject(h).getJSONArray("children");
                                                                            if (arrayI !=null){
                                                                                for (int j = 0; j < arrayH.size(); j++) {
                                                                                    out = out + "\n\t\t\t\t\t\t\t\t\t\t" + arrayI.getJSONObject(j).getString("id") + "-" + arrayI.getJSONObject(j).getString("name");

                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                return out;
            } else {
                return "";
            }
        }

    }
    /**
     * 获取单位网址
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * dwbh：单位编号
     */
    private static String get_department_url(String baseURL, String dwbh) {
        return baseURL + "UnitDetails.aspx?unitId=" + dwbh;
    }

    /**
     * 获取人员网址
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * dwbh：单位编号
     * bzlx：编制类型
     * isCode：编制是否代码
     */
    private static String get_person_url(String baseURL, String dwbh, String bzlx, Boolean isCode) {
        if (isCode) {
            if (bzlx.equals("行政编制")) {
                return baseURL + "PersonList.aspx?unitId=" + dwbh + "&BZLX=%D0%D0%D5%FE%B1%E0%D6%C6";
            } else if (bzlx.equals("事业编制")) {
                return baseURL + "PersonList.aspx?unitId=" + dwbh + "&BZLX=%CA%C2%D2%B5%B1%E0%D6%C6";
            } else if (bzlx.equals("工勤编制")) {
                return baseURL + "PersonList.aspx?unitId=" + dwbh + "&BZLX=%B9%A4%C7%DA%B1%E0%D6%C6";
            } else {
                return "";
            }
        } else {
            return baseURL + "PersonList.aspx?unitId=" + dwbh + "&BZLX=" + bzlx;
        }
    }

    /**
     * 获取更新时间
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * num：从第num个字符开始截取：一般是9，青岛是7
     */
    private static String get_time(String baseURL,Integer num) throws Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseURL)
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        return doc.getElementById("SPAN1").text().substring(num);
    }

    /**
     * 生成before文件
     * structure_str：结构字符串
     * filename：文件名
     */
    public static void create_before(String structure_str,String filename){
        try {
            File file = new File("D:\\结构代码\\"+filename+"-before.txt");
            //文件不存在时候，主动穿件文件。
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(structure_str);
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 生成after文件
     * filename：文件名
     * 需要转换：青岛
     * 不需要转换：济南
     */
    public static void create_after(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:/结构代码/" + filename + "-before.txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:/结构代码/" + filename + ".txt", true)));
            String line = null;
            int character = 0;
            int g = 0;
            while ((line = br.readLine()) != null) {
                character = 0;
                for (int i = 0; i < line.length(); i++) {
                    {
                        if (line.charAt(i) == "\t".charAt(0)) {
                            character++;
                        }
                    }
                }
                if (character == 1) {
                    out.println("\t" + line.split("-")[1]);
                } else if (character == 2) {
                    out.println("\t\t" + line.split("-")[1]);
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        {
                            if (line.charAt(i) == "-".charAt(0)) {
                                g++;
                            }
                        }
                    }
                    if (g == 0) {
                        out.println(line);
                    } else {
                        if (line.split("-")[1] == "党委\n" || line.split("-")[1] == "人大\n" || line.split("-")[1] == "政府\n" || line.split("-")[1] == "政协\n"
                                || line.split("-")[1] == "民主党派\n" || line.split("-")[1] == "群众团体\n" || line.split("-")[1] == "法院\n" || line.split("-")[1] == "检察院\n"
                                || line.split("-")[1] == "经济实体\n" || line.split("-")[1] == "其他\n" || line.split("-")[1] == "街道办事处\n" || line.split("-")[1] == "乡\n"
                                || line.split("-")[1] == "镇\n" || line.split("-")[1] == "行政机关\n" || line.split("-")[1] == "直属事业单位\n" || line.split("-")[1] == "下设机构\n"
                                || line.split("-")[1] == "事业单位\n") {
                            for (int i = 1; i < character; i++) {
                                out.println("\t");
                            }
                            out.println(line.split("-")[1]);
                        } else {
                            out.println(line);
                        }
                        g=0;
                    }
                }
            }


            if (br != null) {
                br.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        create_before(get_structure_str("http://120.221.95.1:1888/",true),"青岛");
        create_after("济南");
    }


}
