package com.wts.crawler.city;

import java.io.File;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.Common.downWithFile;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.WeiHai;

public class WeiHai {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "威海");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = WeiHai();
            for (Map.Entry<String, String> key : map.entrySet()) {
                createFile(getStructureStr(key.getValue(), true), "威海\\" + key.getKey());
                transFile("威海\\" + key.getKey());
            }
            delTab("威海\\市直");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = WeiHai();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "威海\\"+key.getKey(), 9, 12, "JiNan");
            }
        } catch (Exception e) {

        }
    }

}
