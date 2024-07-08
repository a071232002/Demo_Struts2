<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>註冊</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">

</head>
<body>
<h1>Register !</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
		<s:form action="add">
		 請輸入信箱	: <input type="text" name="userMail">
		 <br><br>
		 請輸入名稱: <input type="text" name="userName">
		 <br><br>
		 請輸入密碼: <input type="password" name="userPsw">
		 <br><br>
		 <s:submit/>
		</s:form>
	</main>
</body>
</html>