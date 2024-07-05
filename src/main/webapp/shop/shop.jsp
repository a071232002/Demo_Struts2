<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
 
<title>shop</title>

</head>
<body>
	<h1>shop</h1>
	<a href="<%=request.getContextPath()%>/">首頁</a>
	<a href="<%=request.getContextPath()%>/shop/index">商城</a>
	<s:if test="#session.user != null">
		<h4 style="display: inline; margin-right:30px;">Hello, <s:property value="#session.user.userName"/></h4>
		<a href="<%=request.getContextPath()%>/user/memCenter">會員中心</a>
		<a href="<%=request.getContextPath()%>/user/logout">登出</a>
	</s:if>
	<s:else>
		<a href="<%=request.getContextPath()%>/user/register">註冊</a>
		<a href="<%=request.getContextPath()%>/user/login">登入</a>
	</s:else>
	
	<!-- DataTables initialization -->
    <table id="proTable">
        <thead>
            <tr>
                <th>商品編號</th>
                <th>商品名稱</th>
                <th>商品價格</th>
                <th>商品數量</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="proList" var="pro">
                <tr>
                    <td><s:property value="#pro.proNo"/></td>
                    <td><s:property value="#pro.proName"/></td>
                    <td><s:property value="#pro.proPrice"/></td>
                    <td><s:property value="#pro.proQty"/></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>

    
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    
    
    <script type="text/javascript">
        $(document).ready(function() {
            $('#proTable').DataTable();
        });
    </script>
</body>

</html>