<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<title>shop</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css"> 

</head>
<body>
	<h1>Shop</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
	<!-- DataTables initialization -->
    <table id="proTable" class="shopTable">
        <thead>
            <tr>
                <th>商品編號</th>
                <th>商品名稱</th>
                <th>商品價格</th>
                <th>選購項目</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="proList" var="pro">
		            <tr>
                 		<form action="<%=request.getContextPath()%>/cart/add" >
							<td><s:property value="#pro.proNo" /></td>
							<td><s:property value="#pro.proName" /></td>
							<td><s:property value="#pro.proPrice" /></td>
							<td>
								<label>請選擇數量</label> 
								<select name="proQty">
									<s:iterator begin="1" end="%{#pro.proQty}" var="i">
										<option value="<s:property value="#i"/>"><s:property value="#i" /></option>
									</s:iterator>
								</select> 
								<input type="hidden" name="proNo" value="<s:property value="#pro.proNo"/>" />
								<input type="hidden" name="proName" value="<s:property value="#pro.proName"/>" />
								<input type="hidden" name="proPrice" value="<s:property value="#pro.proPrice"/>" />
								<input type="submit" value="加入購物車" />
							</td>
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
            $('#proTable').DataTable();
        });
    </script>
</body>

</html>