package com.wts.crawler.city;

import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.ZiBo;

public class ZiBo {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        Map<String, String> map = ZiBo();
        createFile(getStructureStr(map.get("市直"), true),"淄博");
        retractFile("淄博");
        transFile("淄博");
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = ZiBo();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "淄博", 7, 12, "YanTai");
            }
        } catch (Exception e) {

        }
    }
}
