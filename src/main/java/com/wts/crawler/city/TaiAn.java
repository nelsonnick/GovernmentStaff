package com.wts.crawler.city;

import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.TaiAn;

public class TaiAn {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            Map<String, String> map = TaiAn();
            for (Map.Entry<String, String> key : map.entrySet()){
                createFile(getStructureStr(key.getValue(), true),key.getKey());
                transFile(key.getKey());
            }
            retractFile("市直");
            mergeFile("泰安",TaiAn());
        }catch (Exception e){

        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = TaiAn();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "泰安", 9, 12,"JiNan");
            }
        } catch (Exception e) {

        }
    }

}
