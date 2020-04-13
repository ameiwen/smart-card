package ${basePackage}.dao;

import ${basePackage}.model.${className};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ${className}Mapper {

    ${className} select${className}ByPrimaryKey(${pk.attrType} ${pk.attrname});

    List<${className}> select${className}ListWhere(Map<String, Object> map);

    int insert${className}Selective(${className} ${classname});

    int update${className}ByPrimaryKeySelective(${className} ${classname});

    int delete${className}ByPrimaryKey(${pk.attrType} ${pk.columnName});

}

