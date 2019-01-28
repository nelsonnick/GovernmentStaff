package com.wts.crawler.city;


import java.io.*;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.DIRECTION;
import static com.wts.crawler.URL.ShengZhi;

public class ShengZhi {
    /**
     * 转化文件
     * filename：文件名
     */
    public static void changeFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTION + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTION + filename + "-new.txt", true)));
            String line = null;
            out.println(br.readLine());
            out.println("\t省直");
            line = br.readLine();
            while (line != null) {
                out.println("\t" + line);
                line = br.readLine();
            }
            br.close();
            out.close();
            File oldfile = new File(DIRECTION + filename + ".txt");
            File newfile = new File(DIRECTION + filename + "-new.txt");
            oldfile.delete();
            newfile.renameTo(oldfile);
        } catch (Exception e) {

        }
    }

    /**
     * 获取结构文件
     */
    public static void getFile() {
        try {
            File file = new File(DIRECTION + "省直");
            if (!file.exists()) {
                file.mkdir();
            }
            Map<String, String> map = ShengZhi();
            createFile(getStructureStr(map.get("省直"), false), "省直\\省直");
            transFile("省直\\省直");
            changeFile("省直\\省直");
        } catch (Exception e) {

        }
    }

    /**
     * 下载
     */
    public static void down() {
        try {
            Map<String, String> map = ShengZhi();
            downWithFile(map.get("省直"), "省直\\省直", 9, 6, "JiNan");
        } catch (Exception e) {

        }
    }
}
