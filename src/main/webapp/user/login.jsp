<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>登入</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">

</head>
<body>
	<h1>Login !</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
		<s:if test="#session.error != null">
			<h4 style="color: red;">
				<s:property value="#session.error" />
			</h4>
		</s:if>
	
		<s:form action="welcome">
			信箱: <input type="text" name="userMail">
					<br>
					<br>
			密碼: <input type="password" name="userPsw">
					<br>
					<br>
			<s:submit value="登入" />
		</s:form>
	</main>
</body>
</html>