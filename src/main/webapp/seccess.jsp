<%--
  Created by IntelliJ IDEA.
  User: yzw
  Date: 2019/1/30
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎你登陆
<shiro:hasRole name="admin">
    欢迎有admin角色用户登录<shiro:principal/>
</shiro:hasRole>

<shiro:hasPermission name="student:create">
    欢迎student:create权限用户<shiro:principal/>
</shiro:hasPermission>
</body>
</html>
