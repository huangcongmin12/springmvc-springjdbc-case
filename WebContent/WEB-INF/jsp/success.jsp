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
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style="width:100%;height:50 ;background-color:green;text-align:center;">
   		<p/><br/><font size=3 color="white">您好，${user.userName}&nbsp;</font>
   </div>
   <div style="float:right"> <br/><a href="user/loginOut.html">退出登录</a></div>
   <form name="form1" action="user/list.html" method='GET'>  
		<input type="hidden" name="pageNow" value="1"/>  
		<a href="javascript:document.form1.submit();">查看用户列表</a>  
  </form>  
  </body>
</html>
