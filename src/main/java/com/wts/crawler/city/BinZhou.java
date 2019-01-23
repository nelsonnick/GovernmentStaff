package com.wts.crawler.city;

import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.BinZhou;

public class BinZhou {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            Map<String, String> map = BinZhou();
            for (Map.Entry<String, String> key : map.entrySet()){
                createFile(getStructureStr(key.getValue(), true),key.getKey());
                transFile(key.getKey());
            }
            retractFile("市直");
            mergeFile("滨州",BinZhou());
        }catch (Exception e){

        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = BinZhou();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "滨州", 9, 12,"JiNan");
            }
        } catch (Exception e) {

        }
    }

}
