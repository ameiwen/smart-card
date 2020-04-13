package ${basePackage}.service.impl;

import ${basePackage}.dao.${className}Mapper;
import ${basePackage}.model.${className};
import ${basePackage}.service.${className}Service;
import com.base.boot.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Mapper ${classname}Mapper;

    @Override
    public Result select${className}ByPage(Page<${className}> pageInfo, ${className} ${classname}) {
        Map<String,Object> search = new HashMap<>();
        if(${classname}.get${pk.attrName}() != null){
            search.put("${pk.attrname}",${classname}.get${pk.attrName}());
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<${className}> list = ${classname}Mapper.select${className}ListWhere(search);
        PageInfo<${className}> infos = new PageInfo<${className}>(list);
        Result result = Result.ok("操作成功");
        result.put("data",infos);
        return result;
    }

    @Override
    public ${className} select${className}ByID(${pk.attrType} ${pk.attrname}) {
       return ${classname}Mapper.select${className}ByPrimaryKey(${pk.attrname});
    }

    @Override
    public Result save${className}(${className} ${classname}) {
        if(${classname}Mapper.insert${className}Selective(${classname}) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result update${className}(${className} ${classname}) {
        if(${classname}Mapper.update${className}ByPrimaryKeySelective(${classname}) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result remove${className}(${pk.attrType} ${pk.attrname}) {
        if(${classname}Mapper.delete${className}ByPrimaryKey(${pk.attrname})>0) {
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

}
