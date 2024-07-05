<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>shop</title>
</head>
<body>
	<h1>shop</h1>
	<a href="<%=request.getContextPath()%>/">首頁</a>
	<a href="<%=request.getContextPath()%>/shop/index">商城</a>
	<s:if test="#session.user != null">
		<h4 style="display: inline; margin-right:30px;">Hello, <s:property value="#session.user.userName"/></h4>
		<a href="<%=request.getContextPath()%>/user/memCenter">會員中心</a>
		<a href="<%=request.getContextPath()%>/user/logout">登出</a>
	</s:if>
	<s:else>
		<a href="<%=request.getContextPath()%>/user/register">註冊</a>
		<a href="<%=request.getContextPath()%>/user/login">登入</a>
	</s:else>

</body>

</html>