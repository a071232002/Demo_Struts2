<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>首頁</title>
</head>
<body>
	<h1>Index</h1>
	<a href="<%=request.getContextPath()%>/">首頁</a>
	<s:if test="#session.user != null">
		<h4 style="display: inline; margin-right:30px;">Hello, <s:property value="#session.user.userName"/></h4>
		<a href=user/memCenter>會員中心</a>
		<a href=user/logout>登出</a>
	</s:if>
	<s:else>
		<a href=user/register>註冊</a>
		<a href=user/login>登入</a>
	</s:else>

</body>

</html>