package com.wts.crawler.city;

import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.ZaoZhuang;

public class ZaoZhuang {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            Map<String, String> map = ZaoZhuang();
            for (Map.Entry<String, String> key : map.entrySet()){
                createFile(getStructureStr(key.getValue(), true),key.getKey());
                transFile(key.getKey());
            }
            retractFile("市直");
            changeFile("市直","枣庄");
            mergeFile("枣庄",ZaoZhuang());
        }catch (Exception e){

        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = ZaoZhuang();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "枣庄", 9, 12,"JiNan");
            }
        } catch (Exception e) {

        }
    }

}
