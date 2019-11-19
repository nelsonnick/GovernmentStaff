package com.wts.crawler;

import java.util.LinkedHashMap;
import java.util.Map;

public class URL {
    public static final String DIRECTION = "E:\\government_staff\\";
    /**
     * 省直
     */
    public static Map<String, String> ShengZhi(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("省直","http://218.56.49.18:8004/");
        return map;
    }
    /**
     * 济南
     */
    public static Map<String, String> JiNan(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://jnbb.gov.cn/smzgs/");
//        map.put("市中区","http://sz.jnbb.gov.cn/smzgs-sz/");
//        map.put("历下区","http://lx.jnbb.gov.cn/smzgs-lx/");
//        map.put("槐荫区","http://hy.jnbb.gov.cn/smzgs-hy/");
//        map.put("天桥区","http://tq.jnbb.gov.cn/smzgs-tq/");
//        map.put("历城区","http://lc.jnbb.gov.cn/smzgs-lc/");
//        map.put("长清区","http://cq.jnbb.gov.cn/smzgs-cq/");
//        map.put("章丘区","http://zq.jnbb.gov.cn/smzgs-zq/");
//        map.put("济阳区","http://jy.jnbb.gov.cn/smzgs-jy/");
//        map.put("商河县","http://sh.jnbb.gov.cn/smzgs-sh/");
//        map.put("平阴县","http://py.jnbb.gov.cn/smzgs-py/");
//        map.put("莱芜区","http://lw.jnbb.gov.cn/smzgs-lw/");
//        map.put("钢城区","http://gc.jnbb.gov.cn/smzgs-gc/");
        return map;
    }
    /**
     * 青岛
     */
    public static Map<String, String> QingDao(){
        Map<String, String> map = new LinkedHashMap<String, String>();
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
     * 淄博
     */
    public static Map<String, String> ZiBo(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://zbbb.gov.cn/gs/");
        map.put("淄川区","http://zbbb.gov.cn/gs/");
        map.put("张店区","http://zbbb.gov.cn/gs/");
        map.put("博山区","http://zbbb.gov.cn/gs/");
        map.put("临淄区","http://zbbb.gov.cn/gs/");
        map.put("周村区","http://zbbb.gov.cn/gs/");
        map.put("桓台县","http://zbbb.gov.cn/gs/");
        map.put("高青县","http://zbbb.gov.cn/gs/");
        map.put("沂源县","http://zbbb.gov.cn/gs/");
        return map;
    }
    /**
     * 枣庄
     */
    public static Map<String, String> ZaoZhuang(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://smz.sdzzbb.gov.cn/");
        map.put("市中区","http://szsmz.sdzzbb.gov.cn/");
        map.put("薛城区","http://tour.xuecheng.gov.cn/");
        map.put("驿城区","http://ycsmz.sdzzbb.gov.cn/");
        map.put("山亭区","http://stbb.shanting.gov.cn/");
        map.put("台儿庄区","http://tezsmz.sdzzbb.gov.cn/");
        map.put("滕州市","http://sm.tengzhou.gov.cn:8083/");
        return map;
    }
    /**
     * 烟台
     */
    public static Map<String, String> YanTai(){
        Map<String, String> map = new LinkedHashMap<String, String>();
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
     * 潍坊
     */
    public static Map<String, String> WeiFang(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://smz.wfbb.gov.cn/");
        map.put("奎文区","http://smz.wfbb.gov.cn/kw/");
        map.put("潍城区","http://smz.wfbb.gov.cn/wc/");
        map.put("坊子区","http://smz.wfbb.gov.cn/fz/");
        map.put("寒亭区","http://smz.wfbb.gov.cn/ht/");
        map.put("青州市","http://smz.wfbb.gov.cn/qz/");
        map.put("诸城市","http://221.1.106.123:7000/");
        map.put("寿光市","http://smz.wfbb.gov.cn/sg/");
        map.put("安丘市","http://smz.wfbb.gov.cn/aq/");
        map.put("昌邑市","http://smz.wfbb.gov.cn/cy/");
        map.put("高密市","http://smz.wfbb.gov.cn/gm/");
        map.put("临朐县","http://smz.wfbb.gov.cn/lq/");
        map.put("昌乐县","http://smz.wfbb.gov.cn/cl/");
        return map;
    }
    /**
     * 泰安
     */
    public static Map<String, String> TaiAn(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://111.235.181.125/");
        map.put("泰山区","http://111.235.181.125:8081/");
        map.put("岱岳区","http://111.235.181.125:8082/");
        map.put("新泰市","http://111.235.181.125:8083/");
        map.put("肥城市","http://111.235.181.125:8084/");
        map.put("宁阳县","http://111.235.181.125:8085/");
        map.put("东平县","http://111.235.181.125:8086/");
        return map;
    }
    /**
     * 威海
     */
    public static Map<String, String> WeiHai(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://221.2.150.159:7880/");
        map.put("环翠区","http://221.2.150.159:8008/");
        map.put("文登区","http://221.2.150.159:8002/");
        map.put("荣成市","http://221.2.150.159:8001/");
        map.put("乳山市","http://221.2.150.159:8000/");
//        map.put("高区","");
//        map.put("经区","");
//        map.put("临港区","");
        return map;
    }
    /**
     * 日照
     */
    public static Map<String, String> RiZhao(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("东港区","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("岚山区","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("莒县","http://www.rzbb.gov.cn/smzxxnew/");
        map.put("五莲县","http://www.rzbb.gov.cn/smzxxnew/");
        return map;
    }
    /**
     * 聊城
     */
    public static Map<String, String> LiaoCheng(){
        Map<String, String> map = new LinkedHashMap<String, String>();
//        map.put("市直","http://smz.lcbb.gov.cn/");
//        map.put("东昌府区","http://dcfsmz.lcbb.gov.cn/");
//        map.put("临清市","http://lqsmz.lcbb.gov.cn/");
//        map.put("茌平县","http://smz.cpbb.gov.cn/");
////        map.put("东阿县","http://smz.debb.gov.cn/");
//        map.put("高唐县","http://gtsmz.lcbb.gov.cn/");
//        map.put("阳谷县","http://smz.ygbb.gov.cn/");
        map.put("冠县","http://smz.sdgxbb.gov.cn/");
        map.put("莘县","http://smz.sdsxbb.gov.cn/");
        return map;
    }

    /**
     * 东营
     */
    public static Map<String, String> DongYing(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://dysmz.hisofter.com:2021/");
        map.put("东营区","http://www.dyqbb.gov.cn:8056/");
        map.put("河口区","http://smz.hkbb.gov.cn/");
        map.put("广饶县","http://grsmz.5hl.cn:2021/");
//        map.put("垦利区","http://www.klbb.gov.cn/smz/");网址能打开，但是不是标准格式
        map.put("利津县","http://218.58.213.193:8880/");//与东营其他地市的标准格式不一致，需要重新编写代码
        return map;
    }

    /**
     * 滨州
     */
    public static Map<String, String> BinZhou(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://60.215.8.11:7002/");
        map.put("沾化区","http://218.56.22.99:8123/");
        map.put("邹平市","http://zpbb.zpgd.net/");
        map.put("惠民县","http://smz.hmjgbz.gov.cn/"); //到这里出了bug
        map.put("博兴县","http://www.bxbb.gov.cn/smzgs/");
        map.put("阳信县","http://www.yxbb.gov.cn/smzgs/");
//        map.put("滨城区","http://smzgs.bincheng.gov.cn/");
//        map.put("无棣县","http://www.sdwdbb.gov.cn/smzgs/");
        return map;
    }
    /**
     * 德州
     */
    public static Map<String, String> DeZhou(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://222.133.41.27:8081/");
//        map.put("德城区","");
//        map.put("陵城区","");
//        map.put("乐陵市","");
//        map.put("禹城市","");
//        map.put("临邑县","");
//        map.put("平原县","");
//        map.put("夏津县","");
//        map.put("武城县","");
//        map.put("庆云县","");
//        map.put("宁津县","");
//        map.put("齐河县","");
        return map;
    }
    /**
     * 临沂------>有网址无数据
     */
    public static Map<String, String> LinYi(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://61.133.63.4/smzxxgs/");
//        map.put("兰山区","");
//        map.put("河东区","");
//        map.put("罗庄区","");
//        map.put("兰陵县","");
//        map.put("郯城县","");
//        map.put("莒南县","");
//        map.put("沂水县","");
//        map.put("蒙阴县","");
//        map.put("平邑县","");
//        map.put("沂南县","");
//        map.put("临沭县","");
//        map.put("费县","");
        return map;
    }
    /**
     * 菏泽
     */
    public static Map<String, String> HeZe(){
        Map<String, String> map = new LinkedHashMap<String, String>();
//        map.put("市直","http://www.hzbb.gov.cn/smz/");
//        map.put("定陶区","http://www.hzbb.gov.cn/dtbb/smz/");
        map.put("郓城县","http://www.hzbb.gov.cn/ycbb/smz/");
        map.put("成武县","http://www.hzbb.gov.cn/cwbb/smz/");
        map.put("东明县","http://www.hzbb.gov.cn/dmbb/smz/");
        map.put("曹县","http://www.hzbb.gov.cn/cxbb/smz/");
//        map.put("开发区","");
//        map.put("高新区","");
//        map.put("牡丹区","");
//        map.put("鄄城县","");
//        map.put("单县","");
        return map;
    }
    /**
     * 济宁
     * http://www.jnjgbz.gov.cn/sz_list/index.php/Home/Index/get_list.html
     */
    public static Map<String, String> JiNing(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","http://www.jnjgbz.gov.cn/sz_list/");
        map.put("任城区","http://www.jnjgbz.gov.cn/jnrcbb/");
        map.put("兖州区","http://www.jnjgbz.gov.cn/jnyzbb/");
        map.put("曲阜市","http://www.jnjgbz.gov.cn/jnqfbb/");
        map.put("邹城市","http://www.jnjgbz.gov.cn/jnzcbb/");
        map.put("汶上县","http://www.jnjgbz.gov.cn/jnwesbb/");
        map.put("梁山县","http://www.jnjgbz.gov.cn/jnlsbb/");
        map.put("微山县","http://www.jnjgbz.gov.cn/jnwsbb/");
        map.put("鱼台县","http://www.jnjgbz.gov.cn/jnytbb/");
        map.put("金乡县","http://www.jnjgbz.gov.cn/jnjxbb/");
        map.put("泗水县","http://www.jnjgbz.gov.cn/jnssbb/");
//        map.put("嘉祥县","");
        return map;
    }
    /**
     * 济宁
     * http://www.jnjgbz.gov.cn/sz_list/index.php/Home/Index/get_list.html
     */
    public static Map<String, String> JiNingCode(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("市直","037008000");
        map.put("任城区","037008001");
        map.put("兖州区","037008002");
        map.put("曲阜市","037008003");
        map.put("邹城市","037008004");
        map.put("汶上县","037008005");
        map.put("梁山县","037008006");
        map.put("微山县","037008007");
        map.put("鱼台县","037008008");
        map.put("金乡县","037008009");
        map.put("泗水县","037008010");
//        map.put("嘉祥县","037008011");
        return map;
    }
}
