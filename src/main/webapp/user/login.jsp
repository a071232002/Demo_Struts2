<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>登入</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
	<style>

	.container {
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    width: 300px;
    margin: 50px auto;
    text-align: center;
}

.form {
    display: flex;
    flex-direction: column;
}

.form label {
    margin-bottom: 5px;
    font-weight: bold;
    text-align: center;
}

.input {
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    
}

.button {
    background-color: #007BFF;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    display: block; /* 让按钮成为块级元素 */
    width: 100%; /* 延展至整行宽度 */
    margin: 0 auto; /* 水平居中 */
    text-align: center; /* 文字居中 */
    box-sizing: border-box; /* 保证 padding 和 border 在计算宽度时不会额外增加 */
}

.button:hover {
    background-color: #0056b3;
}

.error {
    color: red;
    margin-bottom: 15px;
}	
	</style>
</head>
<body>
	<h1>Login !</h1>
	<%@ include file="/util/navi.jsp" %>
	<main class="container">
        <s:if test="#session.error != null">
            <h4 class="error">
                <s:property value="#session.error" />
            </h4>
        </s:if>
    
        <s:form action="welcome" class="form">
            <label for="userMail">帳號:</label>
            <input type="text" id="userMail" name="userMail" class="input">
            <br><br>
            <label for="userPsw">密碼:</label>
            <input type="password" id="userPsw" name="userPsw" class="input">
            <br><br>
            <input type="submit" value="登入" class="button">
        </s:form>
    </main>
</body>
</html>