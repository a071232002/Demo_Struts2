<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>會員中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userPage.css">

</head>
<body>
	<h1>Welcome!</h1>
	<%@ include file="/util/navi.jsp" %>
	<main class="container">
		<s:if test="#session.user != null">
			<s:form action="edit" class="form">
				<label>
					Hello, <s:property value="#session.user.userName" />
				</label>
				<br>
				<br>
			    <label>會員編號: <s:property value="#session.user.userNo" /></label>
				<br>
				<br>
			    <label>會員帳號: <s:property value="#session.user.userMail" /></label>
				<br>
				<br>
				<input type="submit" value="編輯資料" class="button">
			</s:form>
		</s:if> 
		<s:else>
			<h4>No user logged in</h4>
		</s:else>
	</main>
</body>
</html>
</body>
</html>