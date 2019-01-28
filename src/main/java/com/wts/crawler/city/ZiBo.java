package com.wts.crawler.city;

import java.io.File;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.ZiBo;

public class ZiBo {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try{
            File file = new File(DIRECTION + "淄博");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = ZiBo();
            createFile(getStructureStr(map.get("市直"), true),"淄博\\淄博");
            retractFile("淄博\\淄博");
            transFile("淄博\\淄博");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = ZiBo();
            downWithFile(map.get("市直"), "淄博\\淄博", 9, 12, "YanTai");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
