<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<body>
	<h1>Update!</h1>
	
	<s:if test="#session.user != null">
		<s:form action="update">
			
     會員編號: <s:property value="#session.user.userNo" />
			<br>
			<br>
    	信箱: <input type="text" name="userMail" value="<s:property value='#session.user.userMail' />">
			<br>
			<br>
		名稱: <input type="text" name="userName" value="<s:property value='#session.user.userName' />">
			<br>
			<br>
		密碼: <input type="password" name="userPsw" value="<s:property value='#session.user.userPsw' />">
			<br>
			<br>
			<s:submit value="送出" />
		</s:form>
	</s:if>
	<s:else>
		<h4>No user logged in</h4>
	</s:else>

</body>
</html>
</body>
</html>