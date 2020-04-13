package com.zx.card.system.controller;

import com.zx.card.controller.base.BaseController;
import com.zx.card.system.service.GenService;
import com.zx.card.utils.GenUtils;
import com.zx.card.utils.Result;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/system/generator")
public class GenController extends BaseController {

    @Autowired
    private GenService genService;


    /**
     * 数据表页面
     * @return
     */
    @GetMapping
    public String gen() {
        return "system/generator/gen";
    }


    /**
     * 表列表
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    public Result list() {
        return genService.list();
    }

    /**
     * 根据表生成代码
     * @param request
     * @param response
     * @param tableName
     * @throws IOException
     */
    @RequestMapping(value = "/code/{tableName}")
    public void code(HttpServletRequest request, HttpServletResponse response,
                     @PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[] { tableName };
        byte[] data = genService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"gencode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }


    /**
     * 编辑配置策略页面
     * @param model
     * @return
     */
    @GetMapping(value = "/edit")
    public String edit(Model model) {
        Configuration conf = GenUtils.getConfig();
        Map<String, Object> property = new HashMap<>(16);
        property.put("author", conf.getProperty("author"));
        property.put("email", conf.getProperty("email"));
        property.put("package", conf.getProperty("package"));
        property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
        property.put("tablePrefix", conf.getProperty("tablePrefix"));
        property.put("moduleName",conf.getProperty("moduleName"));
        model.addAttribute("property", property);
        return "system/generator/edit";
    }

    /**
     * 修改配置策略
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@RequestParam Map<String, Object> map) {
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("config/generator.properties");
            conf.setProperty("author", map.get("author"));
            conf.setProperty("email", map.get("email"));
            conf.setProperty("package", map.get("package"));
            conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
            conf.setProperty("tablePrefix", map.get("tablePrefix"));
            conf.setProperty("moduleName",map.get("moduleName"));
            conf.save();
        } catch (ConfigurationException e) {
            return Result.error("保存配置文件出错");
        }
        return Result.ok();
    }


}
