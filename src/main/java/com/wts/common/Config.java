package com.wts.common;

import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.wts.controller.*;
import com.wts.entity.model._MappingKit;
import com.wts.util.OptionTask;
/**
 * Config class
 *
 * @author wts
 * @date 2018/12/13
 */
public class Config extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(Config.class, 80, false);
    }

    @Override
    public void configConstant(Constants me) {
        PropKit.use("a_little_config.txt");
        me.setDevMode(false);
    }

    @Override
    public void configRoute(Routes me) {
        String base = "GovernmentStaff";
//        String base = "";
        me.add(base + "/main", MainController.class);
        me.add(base + "/person", PersonController.class);
        me.add(base + "/department", DepartmentController.class);
        me.add(base + "/chart", ChartController.class);
        me.add(base + "/advice", AdviceController.class);
        me.add(base + "/", CrawlerController.class);
    }

    @Override
    public void configEngine(Engine me) {
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        dp.setDriverClass("com.mysql.cj.jdbc.Driver");
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setShowSql(false);
        me.add(arp);
        _MappingKit.mapping(arp);
        Cron4jPlugin cp = new Cron4jPlugin();
        cp.addTask("0 1 * * *", new OptionTask());
//        cp.addTask("* * * * *", new TestTask());
        me.add(cp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new TxByMethods("save","update"));
    }

    @Override
    public void configHandler(Handlers me) {
        //设置上下文路径
        me.add(new ContextPathHandler("contextPath"));
    }
}