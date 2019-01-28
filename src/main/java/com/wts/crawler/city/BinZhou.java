package com.wts.crawler.city;

import java.io.File;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.*;

public class BinZhou {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "滨州");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = BinZhou();
            for (Map.Entry<String, String> key : map.entrySet()) {
                createFile(getStructureStr(key.getValue(), true), "滨州\\" + key.getKey());
                transFile("滨州\\" + key.getKey());
            }
            retractFile("滨州\\市直");
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                downWithFile(key.getValue(), "滨州\\"+key.getKey(), 9, 12, "JiNan");
            }
        } catch (Exception e) {

        }
    }

}
