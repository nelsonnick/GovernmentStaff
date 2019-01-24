package com.wts.crawler.city;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;

import java.util.Map;

import static com.wts.crawler.Common.getDoc;
import static com.wts.crawler.URL.JiNing;

/**
 * JiNing class
 *
 * @author wts
 * @date 2019/1/24
 */
public class JiNing {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            Map<String, String> map = JiNing();
            for (Map.Entry<String, String> key : map.entrySet()) {
                Document doc = getDoc(key.getValue() + "index.php/Home/Index/get_list.html");
                String jsonStr = doc.text().replace("id", "\"id\"").replace("pId", "\"pid\"")
                        .replace("name", "\"name\"").replace("'", "\"")
                        .replace("\"党委系统\"","\"党委\"").replace("\"人大系统\"","\"人大\"")
                        .replace("\"政府系统\"","\"政府\"").replace("\"政协系统\"","\"政协\"")
                        .replace("\"群众团体系统\"","\"群众团体\"").replace("\"法院系统\"","\"法院\"")
                        .replace("\"检察院系统\"","\"检察院\"").replace("\"街道\"","\"街道办事处\"")
                        .replace("\"乡镇街道办事处\"","\"街道办事处\"").replace(" ","");
                JSONArray arr = JSONArray.parseArray(jsonStr);
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    System.out.println(obj);
                }
//                System.out.println(jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getFile();
    }
}
