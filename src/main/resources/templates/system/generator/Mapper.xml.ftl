<#include "common.ftl">
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePackage}.dao.${className}Mapper" >
    <resultMap id="BaseResultMap" type="${basePackage}.model.${className}" >
        <#list columns as item>
        <#if item.attrname = pk.attrname>
        <id column="${item.columnName}" property="${item.attrname}" jdbcType="${item.dataType}"/>
        </#if>
        <#if item.attrname != pk.attrname>
        <result column="${item.columnName}" property="${item.attrname}" jdbcType="${item.dataType}" />
        </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_Where">
        <#list columns as item>
        <if test="${item.attrname} != null">
            and ${item.columnName} = <@mapperEl item.attrname,item.dataType/>
        </if>
        </#list>
    </sql>
    <select id="select${className}ByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
        select * from ${tableName} where ${pk.attrname} =  <@mapperEl pk.attrname,pk.dataType/>
    </select>
    <update id="update${className}ByPrimaryKeySelective" parameterType="${basePackage}.model.${className}">
        update ${tableName}
        <set>
            <#list columns as item>
            <if test="${item.attrname} != null">
                ${item.columnName} = <@mapperEl item.attrname,item.dataType/>,
            </if>
            </#list>
        </set>
        where ${pk.attrname} =  <@mapperEl pk.attrname,pk.dataType/>
    </update>
    <insert id="insert${className}Selective" parameterType="${basePackage}.model.${className}">
        insert info ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list columns as item>
            <if test="${item.attrname} != null">
                ${item.columnName},
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#list columns as item>
            <if test="${item.attrname} != null">
                <@mapperEl item.attrname,item.dataType/>,
            </if>
            </#list>
        </trim>
    </insert>
    <delete id="delete${className}ByPrimaryKey" parameterType="${pk.attrType}">
        delete from ${tableName}
        where ${pk.attrname} =  <@mapperEl pk.attrname,pk.dataType/>
    </delete>
    <select id="select${className}ListWhere" resultMap="BaseResultMap" parameterType="java.util.Map">
        select * from ${tableName}
        where 1 = 1 <include refid="Base_Column_Where"/>
    </select>
</mapper>