<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="resources/css/list.css">
	

  </head>
  
  <body>
    <div class="list">
					<h3><font size=2>更新用户</font></h3>
					<c:set var="user" value="${user}" />
					<c:if test="${!empty msg }">
						<font color="red",size="2"> </font>
					</c:if>
    				<form action="user/update.html" method="post">
    					<input type="hidden" name="id" value="<c:out value="${user.id }"/>" readonly/>
						<table border="0">
							<tr>
								<td align="right">用户名：</td>
								<td align="left">
									<input name="userName" type="text" value="<c:out value="${user.userName }"/>" size="20">
								</td>
							</tr>
							<tr>
								<td align="right">登录密码：</td>
								<td align="left">
									<input name="password" type="text" value="<c:out value="${user.password }"/>" size="20">
								</td>
							</tr>
							<tr>
								<td align="right"><input type="submit" value="保存"/></td>
								<td align="left">
									<input type="reset" value="重置"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
  </body>
</html>
