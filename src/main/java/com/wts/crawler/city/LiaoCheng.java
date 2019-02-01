package com.wts.crawler.city;

import java.io.File;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.LiaoCheng;

public class LiaoCheng {
    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "聊城");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = LiaoCheng();
            for (Map.Entry<String, String> key : map.entrySet()) {
                createFile(getStructureStr(key.getValue(), true), "聊城\\" + key.getKey());
                delTab("聊城\\" + key.getKey());
            }
            delTab("聊城\\市直");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = LiaoCheng();
            for (Map.Entry<String, String> key : map.entrySet()) {
                downWithFile(key.getValue(), "聊城\\"+key.getKey(), 9, 12, "YanTai");
            }
        } catch (Exception e) {

        }
    }

}
