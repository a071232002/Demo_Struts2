<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>會員中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">

</head>
<body>
	<h1>Welcome!</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
		<s:if test="#session.user != null">
			<s:form action="edit">
				<h4>
					Hello, <s:property value="#session.user.userName" />
				</h4>
	    會員編號: <s:property value="#session.user.userNo" />
				<br>
				<br>
	    會員信箱: <s:property value="#session.user.userMail" />
				<br>
				<br>
				<s:submit value="編輯資料"/>
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