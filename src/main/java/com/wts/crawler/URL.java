package com.wts.crawler;

import java.util.HashMap;
import java.util.Map;

public class URL {
    /**
     * 省直
     */
    public static Map<String, String> ShengZhi(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("省直","http://218.56.49.18/");
        return map;
    }
    /**
     * 济南
     */
    public static Map<String, String> JiNan(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("市直","http://jnbb.gov.cn/smzgs/");
        map.put("市中区","http://sz.jnbb.gov.cn/smzgs/");
        map.put("历下区","http://lx.jnbb.gov.cn/smzgs/");
        map.put("槐荫区","http://hy.jnbb.gov.cn/smzgs/");
        map.put("天桥区","http://tq.jnbb.gov.cn/smzgs/");
        map.put("历城区","http://lc.jnbb.gov.cn/smzgs/");
        map.put("长清区","http://cq.jnbb.gov.cn/smzgs/");
        map.put("章丘区","http://zq.jnbb.gov.cn/smzgs/");
        map.put("济阳县","http://jy.jnbb.gov.cn/smzgs/");
        map.put("商河县","http://sh.jnbb.gov.cn/smzgs/");
        map.put("平阴县","http://py.jnbb.gov.cn/smzgs/");
        return map;
    }
    /**
     * 青岛
     */
    public static Map<String, String> QingDao(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("市直","http://120.221.95.1:1888/");
        map.put("市南区","http://120.221.95.1:1888/");
        map.put("市北区","http://120.221.95.1:1888/");
        map.put("李沧区","http://120.221.95.1:1888/");
        map.put("崂山区","http://120.221.95.1:1888/");
        map.put("城阳区","http://120.221.95.1:1888/");
        map.put("西海岸新区、黄岛区","http://120.221.95.1:1888/");
        map.put("即墨区","http://120.221.95.1:1888/");
        map.put("胶州市","http://120.221.95.1:1888/");
        map.put("平度市","http://120.221.95.1:1888/");
        map.put("莱西市","http://120.221.95.1:1888/");
        return map;
    }
    /**
     * 烟台
     */
    public static Map<String, String> YanTai(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("市直","http://smz.yantai.gov.cn/");
        map.put("开发区","http://smz.yantai.gov.cn/");
        map.put("高新区","http://smz.yantai.gov.cn/");
        map.put("保税港","http://smz.yantai.gov.cn/");
        map.put("昆嵛山保护区","http://smz.yantai.gov.cn/");
        map.put("芝罘区","http://smz.yantai.gov.cn/");
        map.put("福山区","http://smz.yantai.gov.cn/");
        map.put("莱山区","http://smz.yantai.gov.cn/");
        map.put("牟平区","http://smz.yantai.gov.cn/");
        map.put("莱州区","http://smz.yantai.gov.cn/");
        map.put("龙口区","http://smz.yantai.gov.cn/");
        map.put("莱阳市","http://smz.yantai.gov.cn/");
        map.put("蓬莱市","http://smz.yantai.gov.cn/");
        map.put("招远市","http://smz.yantai.gov.cn/");
        map.put("栖霞市","http://smz.yantai.gov.cn/");
        map.put("海阳市","http://smz.yantai.gov.cn/");
        map.put("长岛县","http://smz.yantai.gov.cn/");
        return map;
    }
    /**
     * 日照
     */
    public static Map<String, String> RiZhao(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("市直","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("东港区","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("岚山区","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("莒县","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("五莲县","http://www.rzbb.gov.cn/smzxxnew/");
        return map;
    }

}
