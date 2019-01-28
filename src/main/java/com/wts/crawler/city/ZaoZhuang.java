package com.wts.crawler.city;

import java.io.File;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.ZaoZhuang;

public class ZaoZhuang {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "枣庄");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = ZaoZhuang();
            for (Map.Entry<String, String> key : map.entrySet()) {
                createFile(getStructureStr(key.getValue(), true), "枣庄\\" + key.getKey());
                retractFile("枣庄\\" + key.getKey());
            }
            retractFile("枣庄\\市直");
            changeFile("枣庄\\市直", "枣庄");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = ZaoZhuang();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "枣庄\\"+key.getKey(), 9, 12, "JiNan");
            }
        } catch (Exception e) {

        }
    }

}
