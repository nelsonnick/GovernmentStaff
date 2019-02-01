package com.wts.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wts.entity.model.Department;
import com.wts.entity.model.DepartmentErr;
import com.wts.entity.model.Person;
import com.wts.entity.model.PersonErr;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.city.JiNan.downDepartmentDetails;
import static com.wts.crawler.city.JiNan.downPersonList;

/**
 * Common class
 *
 * @author wts
 * @date 2019/1/20
 */
public class Common {
    private static Logger logger = Logger.getLogger(Common.class);

    /**
     * 获取结构字符串
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * isHtml：是否HTML：济南、青岛、淄博、枣庄、东营、潍坊、泰安、威海、滨州、德州、聊城、临沂、菏泽TRUE,省直、烟台、日照FALSE
     */
    public static String getStructureStr(String baseURL, Boolean isHtml) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(baseURL + "TreeViewPage.aspx");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Document doc = Jsoup.parse(driver.getPageSource());
        if (isHtml) {
            Element e = doc.getElementsByTag("body").first().getElementsByTag("form").first().getElementsByTag("table").first().getElementsByTag("tbody")
                    .first().getElementsByTag("tr").first().getElementsByTag("td").first().getElementsByTag("div").first();
            e.removeAttr("id").removeAttr("style").removeAttr("class");
            e.select("div").removeAttr("id").removeAttr("style").removeAttr("class");
            e.select("a").removeAttr("id").removeAttr("style").removeAttr("class").removeAttr("title");
            e.select("table").removeAttr("cellpadding").removeAttr("cellspacing").removeAttr("style").removeAttr("class");
            e.select("td").removeAttr("id").removeAttr("style").removeAttr("class");
            e.select("img").remove();
            Elements elements = e.select("a");
            for (Element element : elements) {
                if (element.attr("href").contains("JavaScript:doNothing(") || element.attr("href").contains("TreeView_ToggleNode")) {
                    element.removeAttr("href");
                }
                if (element.attr("href").contains("JavaScript:f('")) {
                    String h = element.attr("href").split("\'")[1] + '-';
                    element.removeAttr("href");
                    element.attr("href", h);
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
            return s;
        } else {
            driver.quit();//退出浏览器
            String pattern = "var zNodes =.+?;\n";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(doc.toString());
            if (m.find()) {
                String st = m.group(0).replace(",\"icon\":\"image/Department/1.png\"", "").replace(",\"icon\":\"image/Department/2.png\"", "")
                        .replace(",\"icon\":\"image/Department/3.png\"", "").replace(",\"icon\":\"image/Department/4.png\"", "")
                        .replace(",\"icon\":\"image/Department/5.png\"", "").replace(",\"icon\":\"image/Department/6.png\"", "")
                        .replace(",\"icon\":\"image/Department/7.png\"", "").replace(",\"icon\":\"image/Department/8.png\"", "")
                        .replace(",\"icon\":\"image/Department/9.png\"", "").replace(",\"icon\":\"image/Department/10.png\"", "")
                        .replace(",\"icon\":\"image/Department/11.png\"", "").replace(",\"icon\":\"image/Department/12.png\"", "")
                        .replace(",\"icon\":\"image/Department/13.png\"", "").replace(",\"icon\":\"image/Department/14.png\"", "")
                        .replace(",\"icon\":\"image/Department/15.png\"", "").replace("var zNodes = ", "")
                        .replace(",\"rn\":\"0\"", "").replace(",\"rn\":\"1\"", "").replace(",\"rn\":\"2\"", "");
                String tt = st.substring(1, st.length() - 3);
                JSONObject js = JSON.parseObject(tt);
                String out = "\t" + js.getString("id") + "-" + js.getString("name");
                JSONArray arrayA = js.getJSONArray("children");
                for (int a = 0; a < arrayA.size(); a++) {
                    out = out + "\n\t\t" + arrayA.getJSONObject(a).getString("id") + "-" + arrayA.getJSONObject(a).getString("name");
                    JSONArray arrayB = arrayA.getJSONObject(a).getJSONArray("children");
                    if (arrayB != null) {
                        for (int b = 0; b < arrayB.size(); b++) {
                            out = out + "\n\t\t\t" + arrayB.getJSONObject(b).getString("id") + "-" + arrayB.getJSONObject(b).getString("name");
                            JSONArray arrayC = arrayB.getJSONObject(b).getJSONArray("children");
                            if (arrayC != null) {
                                for (int c = 0; c < arrayC.size(); c++) {
                                    out = out + "\n\t\t\t\t" + arrayC.getJSONObject(c).getString("id") + "-" + arrayC.getJSONObject(c).getString("name");
                                    JSONArray arrayD = arrayC.getJSONObject(c).getJSONArray("children");
                                    if (arrayD != null) {
                                        for (int d = 0; d < arrayD.size(); d++) {
                                            out = out + "\n\t\t\t\t\t" + arrayD.getJSONObject(d).getString("id") + "-" + arrayD.getJSONObject(d).getString("name");
                                            JSONArray arrayE = arrayD.getJSONObject(d).getJSONArray("children");
                                            if (arrayE != null) {
                                                for (int e = 0; e < arrayE.size(); e++) {
                                                    out = out + "\n\t\t\t\t\t\t" + arrayE.getJSONObject(e).getString("id") + "-" + arrayE.getJSONObject(e).getString("name");
                                                    JSONArray arrayF = arrayE.getJSONObject(e).getJSONArray("children");
                                                    if (arrayF != null) {
                                                        for (int f = 0; f < arrayF.size(); f++) {
                                                            out = out + "\n\t\t\t\t\t\t\t" + arrayF.getJSONObject(f).getString("id") + "-" + arrayF.getJSONObject(f).getString("name");
                                                            JSONArray arrayG = arrayF.getJSONObject(f).getJSONArray("children");
                                                            if (arrayG != null) {
                                                                for (int g = 0; g < arrayG.size(); g++) {
                                                                    out = out + "\n\t\t\t\t\t\t\t\t" + arrayG.getJSONObject(g).getString("id") + "-" + arrayG.getJSONObject(g).getString("name");
                                                                    JSONArray arrayH = arrayG.getJSONObject(g).getJSONArray("children");
                                                                    if (arrayH != null) {
                                                                        for (int h = 0; h < arrayH.size(); h++) {
                                                                            out = out + "\n\t\t\t\t\t\t\t\t\t" + arrayH.getJSONObject(h).getString("id") + "-" + arrayH.getJSONObject(h).getString("name");
                                                                            JSONArray arrayI = arrayH.getJSONObject(h).getJSONArray("children");
                                                                            if (arrayI != null) {
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
     * 获取网页数据---带表头信息
     * url：网址
     * 链接失败时，抛出IOException
     */
    public static Document getDoc(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("DNT", "1")
                .addHeader("HOST", url.split("/")[2])
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        return doc;
    }

    /**
     * 获取网页数据---不带表头信息
     * url：网址
     * 链接失败时，抛出IOException
     */
    public static Document getDocWithNot(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        return doc;
    }
    /**
     * 获取单位网址
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * dwbh：单位编号
     */
    public static String getDepartmentUrl(String baseURL, String dwbh) {
        return baseURL + "UnitDetails.aspx?unitId=" + dwbh;
    }

    /**
     * 获取人员网址
     * baseURL：基础字符串：http://jnbb.gov.cn/smzgs/
     * dwbh：单位编号
     * bzlx：编制类型
     * isCode：编制是否代码
     */
    public static String getPersonUrl(String baseURL, String dwbh, String bzlx, Boolean isCode) {
        if (isCode) {
//            return baseURL + "PersonList.aspx?unitId=" + dwbh + "&BZLX=" + java.net.URLEncoder.encode(bzlx, "GB2312");
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
     * baseURL：基础字符串
     * num：从第num个字符开始截取：一般是9，青岛是7
     */
    public static String getTime(String baseURL, Integer num) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseURL)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Document doc = Jsoup.parse(response.body().string());
            return doc.getElementById("SPAN1").text().substring(num);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 获取人员
     * cols：列名
     * row：行
     */
    public static Person getPerson(List<String> cols, List<String> row) {
        String dwmc = "";
        String ryxm = "";
        String ryxb = "";
        String ssbm = "";
        String zybzqk = "";
        if (cols.contains("单位")) {
            dwmc = row.get(cols.indexOf("单位")).replace(" ", "");
        }
        if (cols.contains("姓名")) {
            ryxm = row.get(cols.indexOf("姓名")).replace(" ", "");
        }
        if (cols.contains("性别")) {
            ryxb = row.get(cols.indexOf("性别")).replace(" ", "");
        }
        if (cols.contains("部门")) {
            ssbm = row.get(cols.indexOf("部门")).replace(" ", "");
        }
        if (cols.contains("占用编制情况")) {
            zybzqk = row.get(cols.indexOf("占用编制情况")).replace(" ", "");
        }
        return new Person().set("dwmc", dwmc).set("ryxm", ryxm).set("ryxb", ryxb).set("ssbm", ssbm).set("bzqk", zybzqk);
    }

    /**
     * 生成before文件
     * structure_str：结构字符串
     * filename：文件名
     */
    public static void createFile(String structure_str, String filename) {
        try {
            File oldfile = new File(DIRECTION + filename + ".txt");
            if (oldfile.exists()) {
                oldfile.delete();
            }
            File file = new File(DIRECTION + filename + "-before.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(structure_str);
            bw.close();
            fw.close();
            file.renameTo(oldfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算出现次数
     * line：字符串
     * count：要计算的字符
     */
    public static Integer countNum(String line, String count) {
        Integer character = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == count.charAt(0)) {
                character++;
            }
        }
        return character;
    }

    /**
     * 生成文件，并缩进一个tab
     * filename：文件名
     * 需要转换：省直、青岛、烟台、济宁
     * 不需要转换：济南
     */
    public static void transFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = null;
            int character = 0;
            int g = 0;
            line = br.readLine();
            if (!line.contains("-")) {
                while (line != null) {
                    out.println(line.substring(1));
                    line = br.readLine();
                }
            } else {
                while (line != null) {
                    character = countNum(line, "\t");
                    if (character == 1) {
                        out.println(line.split("-")[1]);
                    } else if (character == 2) {
                        out.println("\t" + line.split("-")[1]);
                    } else {
                        g = countNum(line, "-");
                        if (g == 0) {
                            out.println(line.substring(1));
                        } else {
                            if (line.split("-")[1].equals("党委") || line.split("-")[1].equals("人大") || line.split("-")[1].equals("政府") || line.split("-")[1].equals("政协")
                                    || line.split("-")[1].equals("民主党派") || line.split("-")[1].equals("群众团体") || line.split("-")[1].equals("法院") || line.split("-")[1].equals("检察院")
                                    || line.split("-")[1].equals("经济实体") || line.split("-")[1].equals("其他") || line.split("-")[1].equals("街道办事处") || line.split("-")[1].equals("乡")
                                    || line.split("-")[1].equals("镇") || line.split("-")[1].equals("行政机关") || line.split("-")[1].equals("直属事业单位") || line.split("-")[1].equals("下设机构")
                                    || line.split("-")[1].equals("事业单位")|| line.split("-")[1].equals("直属单位")) {
                                for (int i = 1; i < character; i++) {
                                    out.print("\t");
                                }
                                out.print(line.split("-")[1]);
                                out.print("\n");
                            } else {
                                out.println(line.substring(1));
                            }
                            g = 0;
                        }
                    }
                    line = br.readLine();
                }
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        } catch (IOException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }

    /**
     * 将“XX市市直”变为“市直”
     * filename：文件名
     * cityname：城市名
     */
    public static void changeFile(String filename, String cityname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = br.readLine();
            while (line != null) {
                if (line.equals("\t" + cityname + "市市直")) {
                    out.println("\t市直");
                } else {
                    out.println(line);
                }
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        } catch (IOException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }

    /**
     * 整个缩进一个tab
     * filename：文件名
     */
    public static void retractFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = br.readLine();
            while (line != null) {
                out.println(line.substring(1));
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        } catch (IOException e) {
            logger.error("转换文件--->" + DIRECTION + filename + ".txt--->失败！");
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }

    /**
     * 整个添加一个tab
     * filename：文件名
     */
    public static void addTab(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = br.readLine();
            while (line != null) {
                out.println("\t" + line);
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }

    /**
     * 第一行添加城市名
     * filename：文件名
     * cityname：城市名
     */
    public static void addCity(String filename, String cityname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = br.readLine();
            out.println(cityname+"市");
            while (line != null) {
                out.println(line);
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }
    /**
     * 删除第一行的城市
     * filename：文件名
     */
    public static void delCity(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "1.txt", true)));
            String line = null;
            br.readLine();
            line = br.readLine();
            while (line != null) {
                out.println(line);
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File file = new File(DIRECTION + filename + "1.txt");
            oldfile.delete();
            file.renameTo(oldfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("转换文件--->" + DIRECTION + filename + ".txt--->成功！");
    }

    /**
     * 合并文件
     * filename：文件名
     * map：字典
     */
    public static void mergeFile(String filename, Map<String, String> map) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + ".txt", true)));
            for (Map.Entry<String, String> key : map.entrySet()) {
                BufferedReader br = new BufferedReader(new FileReader(DIRECTION + key.getKey() + ".txt"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    out.println(line);
                }
                br.close();
                File file = new File(DIRECTION + key.getKey() + ".txt");
                file.delete();
            }
            out.close();
        } catch (Exception e) {

        }
    }

    /**
     * 根据结构字符串下载全部数据
     * baseURL:基础字符串
     * filename：文件名
     * timeNum：时间截取字符串：一般是9，青岛是7
     * codeNum：上级单位截取字符串，一般是12，省直是6
     */
    public static void downWithFile(String baseURL, String filename, Integer timeNum, Integer codeNum, String city) {
        try {
            String szcs = "";
            String dwzd = "";
            String dwlb = "";
            String dwlx = "";
            String sjdw = "";
            String dwbh = "";
            String dwmc = "";
            String time = "";
            String line = "";
            String row = "";
            Integer tab = 0;
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                if (Pattern.matches("[^\t].+?", line)) {
                    szcs = line.replace("\t", "").replace("\n", "");
                    continue;
                }
                if (Pattern.matches("\t[^\t].+?", line)) {
                    dwzd = line.replace("\t", "").replace("\n", "");
                    time = getTime(baseURL, timeNum);
                    continue;
                }
                if (Pattern.matches("\t\t[^\t].+?", line) || Pattern.matches("\t\t[^\t]", line)) {
                    dwlb = line.replace("\t", "").replace("\n", "");
                    continue;
                }
                if (Pattern.matches("\t\t经济实体", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t行政机关", line)) {
                    dwlx = "行政机关";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t直属事业单位", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t其他单位", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t下设机构", line)) {
                    dwlx = "行政机关";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t事业单位", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t\t下设机构", line)) {
                    dwlx = "行政机关";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t\t事业单位", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t\t\t下设机构", line)) {
                    dwlx = "行政机关";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t\t\t\t\t\t事业单位", line)) {
                    dwlx = "事业单位";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (Pattern.matches("\t\t街道办事处", line)) {
                    dwlx = "行政机关";
                    tab = countNum(line, "\t");
                    continue;
                }
                if (countNum(line, "\t") < tab) {
                    dwlx = "行政机关";
                }
                if ((dwlb.equals("街道办事处") || dwlb.equals("乡") || dwlb.equals("镇")) && Pattern.matches("\t\t\t\t[^\t].+?", line)) {
                    dwlx = "事业单位";
                }
                tab = countNum(line, "\t");
                row = line.replace("\t", "").replace("\n", "");
                if ( row.split("-").length==1){
                    continue;
                }
                dwbh = row.split("-")[0];
                dwmc = row.split("-")[1];
                if (dwbh.length() > codeNum) {
                    sjdw = dwbh.substring(0, dwbh.length() - 3);
                } else {
                    sjdw = "";
                }
//                System.out.println(szcs + "-" + dwzd + "-" + dwlb + "-" + dwlx + "-" + dwbh + "-" + dwmc);
                Class c = Class.forName("com.wts.crawler.city." + city);
                Method method = c.getMethod("downDepartmentDetails", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class);
                method.invoke("", baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, time);
//                downDepartmentDetails(baseURL, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查编制数格式
     */
    public static String checkNum(String Num) {
        if (Num.equals("&nbsp;") || Num.equals("")) {
            return "0";
        } else {
            return Num;
        }
    }

    /**
     * 保存department
     * xzPlanNum：行政计划数
     * xzRealNum：行政实际数
     * xzLoneNum：行政单列数
     * syPlanNum：事业计划数
     * syRealNum：事业实际数
     * syLoneNum：事业单列数
     * gqPlanNum：工勤计划数
     * gqRealNum：工勤实际数
     * gqLoneNum：工勤单列数
     * szcs：所在城市
     * dwzd：单位驻地
     * dwlb：单位类别
     * dwlx：单位类型
     * sjdw：上级单位
     * dwbh：单位编号
     * dwmc：单位名称
     * qtmc：其他名称
     * ldzs：领导职数
     * nsjg：内设机构
     * zyzz：主要职责
     * js：级别
     * url：访问网址
     * time：更新时间
     */
    public static void saveDepartment(String xzPlanNum, String xzRealNum, String xzLoneNum, String syPlanNum, String syRealNum, String syLoneNum, String gqPlanNum, String gqRealNum, String gqLoneNum,
                                      String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc, String qtmc, String ldzs, String nsjg, String zyzz, String jb,
                                      String url, String time) {
        if (qtmc.equals("空")) {
            qtmc = "";
        }
        if (nsjg.equals("\'")) {
            nsjg = "";
        }
        if (zyzz.equals("\'")) {
            zyzz = "";
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
                .set("xz_plan_num", checkNum(xzPlanNum))
                .set("xz_real_num", checkNum(xzRealNum))
                .set("xz_lone_num", checkNum(xzLoneNum))
                .set("sy_plan_num", checkNum(syPlanNum))
                .set("sy_real_num", checkNum(syRealNum))
                .set("sy_lone_num", checkNum(syLoneNum))
                .set("gq_plan_num", checkNum(gqPlanNum))
                .set("gq_real_num", checkNum(gqRealNum))
                .set("gq_lone_num", checkNum(gqLoneNum))
                .set("url", url)
                .set("time", time);
        department.save();
        System.out.println(szcs + "-" + dwzd + "-" + dwlb + "-" + dwlx + "-" + dwbh + "-" + dwmc + "-->下载完成！");
    }

    /**
     * 保存departmentErr
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
    public static void saveDepartmentErr(String base, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc, String time) {
        DepartmentErr departmentErr = new DepartmentErr();
        departmentErr.set("szcs", szcs).set("dwzd", dwzd).set("dwlb", dwlb).set("dwlx", dwlx).set("dwbh", dwbh).set("sjdw", sjdw).set("dwmc", dwmc).set("base", base).set("time", time).save();
        System.out.println(szcs + "-" + dwzd + "-" + dwlb + "-" + dwlx + "-" + dwbh + "-" + dwmc + "-->下载失败！");
    }

    /**
     * 保存personErr
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
    public static void savePersonErr(String base, String szcs, String dwzd, String dwlb, String dwlx, String sjdw, String dwbh, String dwmc, String bzlx) {
        PersonErr personErr = new PersonErr();
        personErr.set("szcs", szcs).set("dwzd", dwzd).set("dwlb", dwlb).set("dwlx", dwlx).set("dwbh", dwbh).set("sjdw", sjdw).set("dwmc", dwmc).set("bzlx", bzlx).set("base", base).save();

    }

    /**
     * 下载department错误数据
     */
    public static void downDepartmentError() {
        List<DepartmentErr> departmentErrs = DepartmentErr.dao.find("SELECT * FROM department_err");
        for (DepartmentErr departmentErr : departmentErrs) {
            downDepartmentDetails(departmentErr.getBase(), departmentErr.getSzcs(), departmentErr.getDwzd(), departmentErr.getDwlb(), departmentErr.getDwlx(), departmentErr.getSjdw(), departmentErr.getDwbh(), departmentErr.getDwmc(), departmentErr.getTime());
            DepartmentErr.dao.deleteById(departmentErr.getId());
        }
    }

    /**
     * 下载person错误数据
     */
    public static void downPersonError() {
        List<PersonErr> personErrs = PersonErr.dao.find("SELECT * FROM person_err");
        for (PersonErr personErr : personErrs) {
            downPersonList(personErr.getBase(), personErr.getSzcs(), personErr.getDwzd(), personErr.getDwlb(), personErr.getDwlx(), personErr.getSjdw(), personErr.getDwbh(), personErr.getDwmc(), personErr.getBzlx());
            PersonErr.dao.deleteById(personErr.getId());
        }
    }
}
