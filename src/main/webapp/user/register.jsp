<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>註冊</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userPage.css">
</head>
<body>
<h1>Register !</h1>
	<%@ include file="/util/navi.jsp" %>
	<main class="container">
		<s:form action="add" class="form">
		
		 <label> 請輸入帳號: </label><input type="text" name="userMail">
		 <br><br>
		 <label> 請輸入名稱: </label><input type="text" name="userName">
		 <br><br>
		 <label> 請輸入密碼: </label><input type="password" name="userPsw">
		 <br><br>
		 <input type="submit" value="送出" class="button">
		</s:form>
	</main>
</body>
</html>