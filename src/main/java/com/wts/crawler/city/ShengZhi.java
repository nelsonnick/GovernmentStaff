package com.wts.crawler.city;


import java.io.*;
import java.util.Map;

import static com.wts.crawler.Common.*;
import static com.wts.crawler.URL.ShengZhi;

public class ShengZhi {

    /**
     * 转化文件
     * filename：文件名
     */
    public static void changeFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:/结构代码/" + filename + ".txt"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:/结构代码/" + filename + "-new.txt", true)));
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
            File oldfile=new File("D:/结构代码/" + filename + ".txt");
            File newfile=new File("D:/结构代码/" + filename + "-new.txt");
            File beforefile=new File("D:/结构代码/" + filename + "-before.txt");
            oldfile.delete();
            beforefile.delete();
            newfile.renameTo(oldfile);
        } catch (Exception e) {

        }
    }

    /**
     * 获取结构文件
     */
    public static void getFile(){
        try {

            Map<String, String> map = ShengZhi();
            for (Map.Entry<String, String> key : map.entrySet()){
                File file = new File("D:/结构代码/" + key.getKey() + ".txt");
                file.delete();
                createFile(getStructureStr(key.getValue(), false),key.getKey());
                transFile(key.getKey());
                changeFile(key.getKey());
            }
        }catch (Exception e){

        }
    }

    /**
     * 下载
     */
    public static void down(){
        try {
            Map<String, String> map = ShengZhi();
            for (Map.Entry<String, String> key : map.entrySet()){
                downDetail(key.getKey(),key.getValue(),9,9);
            }
        }catch (Exception e){

        }
    }
}
