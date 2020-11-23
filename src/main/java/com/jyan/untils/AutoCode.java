package com.jyan.untils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码自动生成
 */
public class AutoCode {public static void main(String[] args) {
    //代码自动生成器对象
    AutoGenerator jcode = new AutoGenerator();
    //全局配置
    GlobalConfig jconfig = new GlobalConfig();
    String jpath = System.getProperty("user.dir");//作用域
    jconfig.setOutputDir(jpath+"/src/main/java");//生成包的路径
    jconfig.setAuthor("江延");//作者名字
    jconfig.setOpen(false);
    jconfig.setFileOverride(false);
    jconfig.setIdType(IdType.AUTO);//数据库主键自增
    jconfig.setDateType(DateType.ONLY_DATE);//日期格式
    jconfig.setSwagger2(true);//启用Swagger，自动生成swagger风格的注释
    jcode.setGlobalConfig(jconfig);
    //设置数据源
    DataSourceConfig jdata = new DataSourceConfig();
    jdata.setUrl("jdbc:mysql://127.0.0.1:3306/jyanself?characterEncoding=UTF-8&" +
            "useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai");
    jdata.setDriverName("com.mysql.cj.jdbc.Driver");
    jdata.setUsername("root");
    jdata.setPassword("123456");
    jdata.setDbType(DbType.MYSQL);
    jcode.setDataSource(jdata);
    //配置生成包，自动生成包的名字，下面是我用的
    PackageConfig jpackage = new PackageConfig();
    jpackage.setParent("com.jyan");
    jpackage.setEntity("entity");
    jpackage.setMapper("mapper");
    jpackage.setService("service");
    jpackage.setServiceImpl("service/impl");
    jpackage.setController("controller");
    jcode.setPackageInfo(jpackage);
    //策略配置
    StrategyConfig jstrategy = new StrategyConfig();
    String[] n={"log","article","category","comment","user","role"};
    jstrategy.setInclude(n);//这个n就是你要自动生成的实体
    jstrategy.setNaming(NamingStrategy.underline_to_camel);//驼峰命名
    jstrategy.setColumnNaming(NamingStrategy.underline_to_camel);
    jstrategy.setEntityLombokModel(true);
    //自动填充配置
//    TableFill jct = new TableFill("createdTime", FieldFill.INSERT);//数据库插入修改时时间自动填充
//    TableFill jUt = new TableFill("updateTime", FieldFill.UPDATE);
//    List<TableFill> jt = new ArrayList<>();
//    jt.add(jct);
//    jt.add(jUt);
//    jstrategy.setTableFillList(jt);
    jstrategy.setRestControllerStyle(true);//restful接口
    jstrategy.setControllerMappingHyphenStyle(true);
    jcode.setStrategy(jstrategy);
    //执行
    jcode.execute();
}
}
