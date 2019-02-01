<%--
  Created by IntelliJ IDEA.
  User: yzw
  Date: 2019/1/30
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form action="login" method="post">
     userName:<input name="userName" type="text"/><br/>
     password:<input name="password" type="text"/><br/>
     ${errorInfo}<br/>
     <input type="submit" value="登陆"/>
 </form>
</body>
</html>
