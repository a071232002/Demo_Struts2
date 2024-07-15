<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>修改資料</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userPage.css">
</head>
<body>
	<h1>Update!</h1>
	<%@ include file="/util/navi.jsp" %>
	<main class="container">
		<s:if test="#session.user != null">
			<s:form action="update" class="form">
				
	     		<label>會員編號: <s:property value="#session.user.userNo" /></label>
				<br>
				<br>
	    		<label>帳號: <s:property value="#session.user.userMail" /></label> 
	    		
				<br>
				<br>
				<label>名稱:</label>
				<input type="text" name="userName" value="<s:property value='#session.user.userName' />">
				<br>
				<br>
				<label>密碼:</label>
				<input type="password" name="userPsw" value="<s:property value='#session.user.userPsw' />">
				<br>
				<br>
				<input type="submit" value="送出" class="button">
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