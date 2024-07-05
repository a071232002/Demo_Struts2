<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<h1>Register !</h1>

<s:form action="add">
 請輸入信箱	: <input type="text" name="userMail">
 <br><br>
 請輸入名稱: <input type="text" name="userName">
 <br><br>
 請輸入密碼: <input type="password" name="userPsw">
 <br><br>
 <s:submit/>
</s:form>

</body>
</html>