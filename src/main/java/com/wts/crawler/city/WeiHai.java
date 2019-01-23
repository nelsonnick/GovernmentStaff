package com.wts.crawler.city;

import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.Common.downWithFile;
import static com.wts.crawler.URL.WeiHai;

public class WeiHai {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            Map<String, String> map = WeiHai();
            for (Map.Entry<String, String> key : map.entrySet()){
                createFile(getStructureStr(key.getValue(), true),key.getKey());
                transFile(key.getKey());
            }
            retractFile("市直");
            mergeFile("威海",WeiHai());
        }catch (Exception e){

        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = WeiHai();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "威海", 9, 12,"JiNan");
            }
        } catch (Exception e) {

        }
    }

}
