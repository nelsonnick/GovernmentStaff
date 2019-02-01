package com.wts.crawler.city;

import com.alibaba.fastjson.JSONArray;
import com.wts.entity.model.Jsonstr;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.*;

/**
 * JiNing class
 *
 * @author wts
 * @date 2019/1/24
 */
public class JiNing {

    public static String getCode(String CityNum, String code) {
        if (code.length() == 1) {
            return CityNum + "00" + code;
        } else if (code.length() == 2) {
            return CityNum + "0" + code;
        } else if (code.length() == 3) {
            return CityNum + code;
        } else {
            return CityNum;
        }
    }

    public static String getCode(String code) {
        if (code.length() == 1) {
            return "00" + code;
        } else if (code.length() == 2) {
            return "0" + code;
        } else if (code.length() == 3) {
            return code;
        } else {
            return "";
        }
    }
    /**
     * 获取单位网址
     * baseURL：基础字符串：http://www.jnjgbz.gov.cn/sz_list/
     */
    public static String getDepartmentUrl(String baseURL) {
        return baseURL + "index.php/Home/Index/right_info.html";
    }
    /**
     * 获取网页数据---带表头信息
     * url：网址
     * 链接失败时，抛出IOException
     */
    public static Document getDos(String url,String id) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id",id);
        FormBody body = builder.build();
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
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        return doc;
    }

    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "济宁");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = JiNing();
            Map<String, String> code = JiNingCode();
            for (Map.Entry<String, String> key : map.entrySet()) {
                Document doc = getDoc(key.getValue() + "index.php/Home/Index/get_list.html");
                String jsonStr = doc.text().replace("id", "\"id\"").replace("pId", "\"pid\"")
                        .replace("name", "\"name\"").replace("'", "\"")
                        .replace("\"党委系统\"", "\"党委\"").replace("\"人大系统\"", "\"人大\"").replace("\"经济实体系统\"", "\"经济实体\"")
                        .replace("\"政府系统\"", "\"政府\"").replace("\"政协系统\"", "\"政协\"").replace("\"民主党派系统\"", "\"民主党派\"")
                        .replace("\"群众团体系统\"", "\"群众团体\"").replace("\"法院系统\"", "\"法院\"")
                        .replace("\"检察院系统\"", "\"检察院\"").replace("\"街道\"", "\"街道办事处\"").replace("\"乡镇\"", "\"镇\"")
                        .replace("\"乡镇街道办事处\"", "\"街道办事处\"").replace(" ", "");
                JSONArray arr = JSONArray.parseArray(jsonStr);
                String str = "";
                if (arr.getJSONObject(0).getString("pid").equals("0")) {
                    str = str + "\t" + getCode(code.get(key.getKey()), arr.getJSONObject(0).getString("id")) + "-" + arr.getJSONObject(0).getString("name") + "\n";
                    arr.getJSONObject(0).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(0).getString("id")));
                    for (int a = 0; a < arr.size(); a++) {
                        if (arr.getJSONObject(a).getString("pid").equals(arr.getJSONObject(0).getString("id"))) {
                            str = str + "\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(0).getString("id"))
                                    + getCode(arr.getJSONObject(a).getString("id")) + "-"
                                    + arr.getJSONObject(a).getString("name") + "\n";
                            arr.getJSONObject(a).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(0).getString("id"))
                                    + getCode(arr.getJSONObject(a).getString("id")));
                            for (int b = 0; b < arr.size(); b++) {
                                if (arr.getJSONObject(b).getString("pid").equals(arr.getJSONObject(a).getString("id"))) {
                                    str = str + "\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(a).getString("id"))
                                            + getCode(arr.getJSONObject(b).getString("id")) + "-"
                                            + arr.getJSONObject(b).getString("name") + "\n";
                                    arr.getJSONObject(b).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(a).getString("id"))
                                            + getCode(arr.getJSONObject(b).getString("id")));
                                    for (int c = 0; c < arr.size(); c++) {
                                        if (arr.getJSONObject(c).getString("pid").equals(arr.getJSONObject(b).getString("id"))) {
                                            str = str + "\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(b).getString("id"))
                                                    + getCode(arr.getJSONObject(c).getString("id")) + "-"
                                                    + arr.getJSONObject(c).getString("name") + "\n";
                                            arr.getJSONObject(c).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(b).getString("id"))
                                                    + getCode(arr.getJSONObject(c).getString("id")));
                                            for (int d = 0; d < arr.size(); d++) {
                                                if (arr.getJSONObject(d).getString("pid").equals(arr.getJSONObject(c).getString("id"))) {
                                                    str = str + "\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(c).getString("id"))
                                                            + getCode(arr.getJSONObject(d).getString("id")) + "-"
                                                            + arr.getJSONObject(d).getString("name") + "\n";
                                                    arr.getJSONObject(d).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(c).getString("id"))
                                                            + getCode(arr.getJSONObject(d).getString("id")));
                                                    for (int e = 0; e < arr.size(); e++) {
                                                        if (arr.getJSONObject(e).getString("pid").equals(arr.getJSONObject(d).getString("id"))) {
                                                            str = str + "\t\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(d).getString("id"))
                                                                    + getCode(arr.getJSONObject(e).getString("id")) + "-"
                                                                    + arr.getJSONObject(e).getString("name") + "\n";
                                                            arr.getJSONObject(e).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(d).getString("id"))
                                                                    + getCode(arr.getJSONObject(e).getString("id")));
                                                            for (int f = 0; f < arr.size(); f++) {
                                                                if (arr.getJSONObject(f).getString("pid").equals(arr.getJSONObject(e).getString("id"))) {
                                                                    str = str + "\t\t\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(e).getString("id"))
                                                                            + getCode(arr.getJSONObject(f).getString("id")) + "-"
                                                                            + arr.getJSONObject(f).getString("name") + "\n";
                                                                    arr.getJSONObject(f).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(e).getString("id"))
                                                                            + getCode(arr.getJSONObject(f).getString("id")));
                                                                    for (int g = 0; g < arr.size(); g++) {
                                                                        if (arr.getJSONObject(g).getString("pid").equals(arr.getJSONObject(f).getString("id"))) {
                                                                            str = str + "\t\t\t\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(f).getString("id"))
                                                                                    + getCode(arr.getJSONObject(g).getString("id")) + "-"
                                                                                    + arr.getJSONObject(g).getString("name") + "\n";
                                                                            arr.getJSONObject(g).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(f).getString("id"))
                                                                                    + getCode(arr.getJSONObject(g).getString("id")));
                                                                            for (int h = 0; h < arr.size(); h++) {
                                                                                if (arr.getJSONObject(h).getString("pid").equals(arr.getJSONObject(g).getString("id"))) {
                                                                                    str = str + "\t\t\t\t\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(g).getString("id"))
                                                                                            + getCode(arr.getJSONObject(h).getString("id")) + "-"
                                                                                            + arr.getJSONObject(h).getString("name") + "\n";
                                                                                    arr.getJSONObject(h).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(g).getString("id"))
                                                                                            + getCode(arr.getJSONObject(h).getString("id")));
                                                                                    for (int i = 0; i < arr.size(); i++) {
                                                                                        if (arr.getJSONObject(h).getString("pid").equals(arr.getJSONObject(h).getString("id"))) {
                                                                                            str = str + "\t\t\t\t\t\t\t\t\t\t" + getCode(code.get(key.getKey()), arr.getJSONObject(h).getString("id"))
                                                                                                    + getCode(arr.getJSONObject(i).getString("id")) + "-"
                                                                                                    + arr.getJSONObject(i).getString("name") + "\n";
                                                                                            arr.getJSONObject(i).put("dwbh", getCode(code.get(key.getKey()), arr.getJSONObject(h).getString("id"))
                                                                                                    + getCode(arr.getJSONObject(i).getString("id")));
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
                    }
                }
                createFile(str, "济宁\\" + key.getKey());
                transFile("济宁\\" + key.getKey());
                addTab("济宁\\" + key.getKey());
                addCity("济宁\\" + key.getKey(),"济宁");
//              需要手动将济宁\\市直.txt文件中的第二行改成“市直”

                for (int m = 0; m < arr.size(); m++) {
                    Jsonstr jsonstr = new Jsonstr();
                    jsonstr.set("id", arr.getJSONObject(m).get("id")).set("name", arr.getJSONObject(m).get("name")).set("pid", arr.getJSONObject(m).get("pid")).set("dwbh", arr.getJSONObject(m).get("dwbh")).save();
                }
                System.out.println(key.getKey() + "：下载完成！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = JiNing();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "济宁\\"+key.getKey(), 9, 12, "JiNing");
            }
        } catch (Exception e) {

        }
    }

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
            String id = Jsonstr.dao.findFirst("SELECT id FROM jsonstr WHERE dwbh="+dwbh).getId();
            String url = getDepartmentUrl(base);
            Document doc = getDos(url,id);
        } catch (IOException e) {
//            saveDepartmentErr(base, szcs, dwzd, dwlb, dwlx, sjdw, dwbh, dwmc_1, time);
        }
    }

    public static void main(String[] args) throws Exception{
        String url = getDepartmentUrl("http://www.jnjgbz.gov.cn/sz_list/");
        Document doc = getDos(url,"29");
        Element e = doc.getElementsByTag("table").first();
        System.out.println(e);
    }
}
