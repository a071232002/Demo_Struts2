<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<title>購物車</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTable.css"> 

    
</head>
<body>
	<h1>購物車</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
	<!-- DataTables initialization -->
    	<table id="cartTable" class="cartTable">
	        <thead>
	            <tr>
	                <th>商品編號</th>
	                <th>商品名稱</th>
	                <th>訂購價格</th>
	                <th>訂購數量</th>
	            </tr>
	        </thead>
	        <tbody>
	            <s:iterator value="cartList" var="cart">
			            <tr>
	                 		<form action="<%=request.getContextPath()%>/cart/add" >
								<td><s:property value="#cart.proNo" /></td>
								<td><s:property value="#cart.proName" /></td>
								<td><s:property value="#cart.ordPrice" /></td>
								<td><s:property value="#cart.ordQty" /></td>
	                  		</form>
						</tr>
	            </s:iterator>
	        </tbody>
	    </table>
	</main>
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    
    
    <script type="text/javascript">
        $(document).ready(function() {
            $('#cartTable').DataTable();
        });
    </script>
</body>

</html>