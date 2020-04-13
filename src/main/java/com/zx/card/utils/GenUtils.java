package com.zx.card.utils;


import com.zx.card.common.Constants;
import com.zx.card.system.model.vo.ColumnDO;
import com.zx.card.system.model.vo.TableDO;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.JDBCType;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 */
public class GenUtils {

    private final static String temple_directory = "src/main/resources/templates/system/generator";

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("Mapper.xml.ftl");
        templates.add("Model.java.ftl");
        templates.add("Dao.java.ftl");
        templates.add("Service.java.ftl");
        templates.add("ServiceImpl.java.ftl");
        templates.add("Controller.java.ftl");
        templates.add("List.html.ftl");
        templates.add("Add.html.ftl");
        templates.add("Edit.html.ftl");
        templates.add("List.js.ftl");
        return templates;
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("config/generator.properties");
        } catch (ConfigurationException e) {
            System.out.println("获取配置文件失败" + e.getMessage());
//            throw new BDException("获取配置文件失败，", e);
        }
        return null;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        Configuration config = getConfig();
        //表信息
        TableDO tableDO = new TableDO();
        tableDO.setTableName(table.get("tableName"));
        tableDO.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableDO.getTableName(), config.getString("tablePrefix"), config.getString("autoRemovePre"));
        tableDO.setClassName(className);
        tableDO.setClassname(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnDO> columnList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnDO columnDO = new ColumnDO();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(getJdbcTypeSame(column.get("dataType")));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnDO.getDataType(), "unknowType");
            columnDO.setAttrType(attrType);

            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }
            //是否有时间类型
            if("Date".equalsIgnoreCase(columnDO.getAttrType())){
                tableDO.setHasDate(true);
            }
            //是否有BigDecimal类型
            if ("BigDecimal".equalsIgnoreCase(columnDO.getAttrType())) {
                tableDO.setHasBigDecimal(true);
            }
            columnList.add(columnDO);
        }

        tableDO.setColumns(columnList);
        //没主键，则第一个字段为主键
        if (tableDO.getPk() == null) {
            tableDO.setPk(tableDO.getColumns().get(0));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pk",tableDO.getPk());
        map.put("hasDate",tableDO.isHasDate());
        map.put("hasBigDecimal",tableDO.isHasBigDecimal());
        map.put("tableName", tableDO.getTableName());
        map.put("classname", tableDO.getClassname());
        map.put("className", tableDO.getClassName());
        map.put("comments", tableDO.getComments());
        map.put("columns", tableDO.getColumns());

        map.put("author", config.getString("author"));
        map.put("email",config.getString("email"));
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        map.put("version", "1.0.0");
        map.put("basePackage", config.getString("package"));
        map.put("pathName", config.getString("moduleName"));


        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            freemarker.template.Configuration cfg = getConfiguration(temple_directory);
            try {
                //渲染模板
                StringWriter sw = new StringWriter();
                cfg.getTemplate(template).process(map, sw);
                //添加到zip
                String fileName = getFileName(template, tableDO.getClassname(), tableDO.getClassName(), map.get("basePackage").toString(),map.get("pathName").toString());
                zip.putNextEntry(new ZipEntry(fileName));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                System.out.println("渲染模板失败，表名：" + tableDO.getTableName() + e.getMessage());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
        if (Constants.AUTO_REMOVE_PRE.equals(autoRemovePre)) {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }

        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * DATE DATETIME统一定义TIMESTAMP，CLOB转VARCHAR
     *
     * @param jdbcType
     * @return
     */
    public static String getJdbcTypeSame(String jdbcType) {
        String temp = jdbcType;
        if (jdbcType.equals(JDBCType.DATE.getName())) {
            temp = JDBCType.TIMESTAMP.getName();
        }
        if (jdbcType.equals(JDBCType.CLOB.getName())) {
            temp = JDBCType.VARCHAR.getName();
        }
        if("int".equals(jdbcType)){
            temp = JDBCType.INTEGER.getName();
        }
        return temp.toUpperCase();
    }

    /**
     * 获取freemarker cfg
     *
     * @return
     */
    public static freemarker.template.Configuration getConfiguration(String temple) {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_20);
        try {
            cfg.setDirectoryForTemplateLoading(new File(temple));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String classname, String className, String packageName,String moduleName) {
        String packagePath = "main"+ File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Mapper.xml.ftl")) {
            return "main/resources/mapper/" + moduleName
                    + File.separator + className + "Mapper.xml";
        }

        if (template.contains("Model.java.ftl")) {
            return packagePath + "model" + File.separator + className + ".java";
        }

        if (template.contains("Dao.java.ftl")) {
            return packagePath + "dao" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Service.java.ftl")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.ftl")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.ftl")) {
            return packagePath + "controller" + File.separator + moduleName + File.separator  + className + "Controller.java";
        }


        if (template.contains("List.html.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
                    + moduleName + File.separator + classname + ".html";
        }
        if (template.contains("Add.html.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
                    + moduleName + File.separator + "add.html";
        }
        if (template.contains("Edit.html.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
                    + moduleName + File.separator + "edit.html";
        }

        if (template.contains("List.js.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "layuiadmin" + File.separator
                    + "modules" + File.separator + moduleName + File.separator + classname + "list.js";
        }
//        if (template.contains("add.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
//                    + "appjs" + File.separator + packageName + File.separator + classname + File.separator + "add.js";
//        }
//        if (template.contains("edit.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
//                    + "appjs" + File.separator + packageName + File.separator + classname + File.separator + "edit.js";
//        }
        return null;
    }

}
