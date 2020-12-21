<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>freeMarker入门demo</title>
</head>
<body>
    <#--注释内容-->
    ${name},你好,${message}

    <br><br>

    <#-- assign 指令，用于在页面定义一个简单类型变量-->
    <#assign param="这是一个自定义简单类型变量param">
    assign使用1：${param}

    <br><br>

    <#-- assign 指令，用于在页面定义一个对象类型变量-->
    <#assign info={"username":"root","password":"123456"}>
    assign使用2：<br>用户名：${info.username}<br>密 码：${info.password}

    <br><br>

    <#-- include 指令，用于模板文件的嵌套-->
    <#include "head.ftl">

    <br><br>

    <#-- if 指令-->
    <#if success=true>
        已通过实名认证
    <#else>
        未通过实名认证
    </#if>

    <br><br>

    List指令<br>
    序号&emsp;种类&emsp;价格
    <#list goodsList as item>
        <br>&emsp;${item_index+1}&emsp;${item.name}&emsp;${item.price}
    </#list>

    <br><br><#--内建函数 变量+？+函数名称-->
    共${goodsList?size}条记录

    <br><br>

    <#assign text="{'bank':'工商银行','account':'18901920201920212'}"/>
    <#--内建函数 eval,json字符串转对象-->
    <#assign data=text?eval />
    开户行：${data.bank}  账号：${data.account}

    <br><br>

    内建函数 日期格式化<br>
    当前日期：${today?date} <br>
    当前时间：${today?time} <br>
    当前日期+时间：${today?datetime} <br>
    日期格式化：  ${today?string("yyyy年MM月dd日")}

    <br><br>

    数字转字符串<br>
    默认情况：${point}
    转换：${point?c}

    <br><br>

    空字符串处理<br>
    <#if aaa??>
        aaa变量存在
    <#else>
        aaa变量不存在
    </#if>

    <br><br>

    缺失变量默认值:"!"<br>
    ${aaa!'请初始化变量'}
</body>
</html>