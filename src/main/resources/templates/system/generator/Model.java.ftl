package ${basePackage}.model;

<#if hasDate == true>
import java.util.Date;
</#if>
<#if hasBigDecimal == true>
import java.math.BigDecimal;
</#if>

/**
* generator code
* @author ${author}
*/
public class ${className} {

<#list  columns  as  item>
    private ${item.attrType} ${item.attrname};

</#list>
<#list columns as item>

    public ${item.attrType} get${item.attrName}() {
        return ${item.attrname};
    }

    public void set${item.attrName}(${item.attrType} ${item.attrname}) {
        this.${item.attrname} = ${item.attrname};
    }
</#list>

}
