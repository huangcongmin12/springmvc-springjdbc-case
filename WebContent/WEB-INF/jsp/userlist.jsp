<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userlist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="resources/css/list.css">
	<script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>   
</head>
<script type="text/javascript">
	function submitForm() {
		var frm = document.myForm;
		frm.submit();
	} 
	$(document).ready(function(){
				$(".list_tb tr").mouseover(function(){
					$(this).addClass("highlight");
				}).mouseout(function(){
					$(this).removeClass("highlight");
				})
			});
</script>
<body>
	<div style="float:right"><a href="user/loginOut.html">退出登录</a> </div>
	<div class="list">
					<h3><font size=2>用户列表</font></h3>
					<table class="list_tb">
						<tr>
							<th>序号</th>
							<th>ID</th>
							<th>用户名</th>
							<th>登录密码</th>
							<th>操作</th>
							<th>操作</th>
						</tr>
						<c:set var="page" value="${page}" />
						<c:forEach var="user" varStatus="status" items="${user}">
						<c:if test="${status.count%2==1}">
						<tr style="background-color:#F7FFFF">
						</c:if>
						<c:if  test="${status.count%2==0}">
						<tr style="background-color:#E4E4E4">
						</c:if>
							<td><c:out value="${status.count+(page.pageNow-1)*page.pageSize}"/></td>
							<td><c:out value="${user.id}"/></td>
							<td><c:out value="${user.userName}"/></td>
							<td><c:out value="${user.password}"/></td>
							<td><a href="user/update.html?id=<c:out value="${user.id}"/>">更新</a></td>
							<td><a href="user/delete.html?id=<c:out value="${user.id}"/>"onClick="if(!confirm('确定删除该信息吗？'))return false;else return true;">删除</a></td>
						</tr>
						</c:forEach>
					</table>
					<font size="2">共</font>
					<c:out value="${page.totalPage}"/>
					<font size="2">页   &nbsp;&nbsp;当前第</font>
					<c:out value="${page.pageNow}"/>
					<font size="2">页&nbsp;&nbsp; 共</font>
					<c:out value="${page.totalSize}"/>
					<font size="2">条记录&nbsp;&nbsp;
					<!-- 首页始终显示第一页 -->
					<a href="user/list.html?pageNow=1">首页</a>
					<!-- 如果有前一页就提交前一页的pageNow值 -->
					<c:choose>
						<c:when test="${page.hasPre}">
							<a href="user/list.html?pageNow=<c:out value="${page.pageNow-1}"/>">上一页</a>
						</c:when>
						<c:otherwise>
							<a href="user/list.html?pageNow=1">上一页</a>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${page.hasNext}">
							<a href="user/list.html?pageNow=<c:out value="${page.pageNow+1}"/>">下一页</a>
						</c:when>
						<c:otherwise>
							<a href="user/list.html?pageNow=<c:out value="${page.totalPage}"/>">下一页</a>
						</c:otherwise>
					</c:choose>
					<!-- 尾页始终提交最后一页的pageNow值 -->
					<a href="user/list.html?pageNow=<c:out value="${page.totalPage}"/>">尾页</a>
					&nbsp;跳转到:
					</font>
					<form name="myForm" action="user/list.html" method="get" style="display:inline;">
						<select size="1" name="pageNow" style=" height: 18" onchange="submitForm()"> 
						<c:forEach begin="1" end="${page.totalPage}" varStatus="status">
						<option value="${status.index}"${status.index == page.pageNow ? 'selected' : ''}>
							${status.index}
						</option>
						</c:forEach>
						</select>
					</form>		
	</div>
</body>
</html>
