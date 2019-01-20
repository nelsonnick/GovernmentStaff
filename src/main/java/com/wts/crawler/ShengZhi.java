package com.wts.crawler;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ShengZhi {
    private static void send() throws Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://218.56.49.18/UnitDetails.aspx?unitId=037412")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("DNT", "1")
                .addHeader("Host", "218.56.49.18")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        Document doc = Jsoup.parse(response.body().string());
        System.out.println(doc);
    }


    private static void tree() throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.get("http://jnbb.gov.cn/smzgs/TreeViewPage.aspx");
        driver.manage().window().setSize(new Dimension(300, 200));
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(driver.getPageSource());
        Element e =doc.getElementsByTag("body").first().getElementsByTag("form").first().getElementsByTag("table").first().getElementsByTag("tbody")
                .first().getElementsByTag("tr").first().getElementsByTag("td").first().getElementsByTag("div").first();
        e.removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("div").removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("a").removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("table").removeAttr("cellpadding").removeAttr("cellspacing").removeAttr("style").removeAttr("class");
        e.select("td").removeAttr("id").removeAttr("style").removeAttr("class");
        e.select("img").remove();
        String t = e.toString();
        t.replace("<div>", "").replace("</div>", "")
                .replace("<tr>", "").replace("</tr>", "")
                .replace("<table>", "").replace("</table>", "")
                .replace("<tbody>", "").replace("</tbody>", "")
                .replace(" href=\"JavaScript:doNothing();\"", "").replace("<a></a>", "");

        System.out.println(t);
        driver.quit();//退出浏览器
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://jnbb.gov.cn/smzgs/TreeViewPage.aspx").build();
//        Response response = client.newCall(request).execute();
//        Document doc = Jsoup.parse(response.body().string());
//        System.out.println(doc);
    }
    public static void main(String[] args) throws Exception{
//        send();
        tree();
    }
}
