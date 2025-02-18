package com.misc.note;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;

public class CodeGenerator {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/note?useUnicode=true&characterEncoding=utf-8";

    private static final String user = "root";

    private static final String password = "root";

    private static final String baseDir = "E:\\projects\\note\\note-account\\src\\main\\java";

    private static final String basePackage = "com.misc.note.account";


    public static void main(String[] args) {


        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);

        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        Generator generator = new Generator(ds, globalConfig);
        generator.generate();
    }

    private static GlobalConfig createGlobalConfigUseStyle1() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.getPackageConfig().setSourceDir(baseDir).setBasePackage(basePackage);
        globalConfig.setTablePrefix("note_");
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        globalConfig.setEntityJdkVersion(21);
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.getMapperXmlConfig().setFileSuffix("Mapper");
        return globalConfig;
    }
}
