package ${basePackage}.service;

import ${basePackage}.model.${className};
import com.base.boot.utils.Result;
import com.github.pagehelper.Page;

public interface ${className}Service {

    Result select${className}ByPage(Page<${className}> pageInfo, ${className} ${classname});

    ${className} select${className}ByID(${pk.attrType} ${pk.attrname});

    Result save${className}(${className} ${classname});

    Result update${className}(${className} ${classname});

    Result remove${className}(${pk.attrType} ${pk.attrname});

}
