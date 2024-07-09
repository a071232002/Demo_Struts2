<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<title>購物車</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTable.css"> 

<style type="text/css">
		main {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border: 2px solid #ddd; /* 添加边框 */
        }
        table th, table td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #f7f7f7;
            color: #333;
            text-transform: uppercase;
            font-size: 14px;
            font-weight: bold;
            border-right: 1px solid #ddd; /* 添加右边框 */
        }
        table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tbody tr:hover {
            background-color: #e0e0e0;
        }
        form {
            margin: 0;
        }
</style>
    
</head>
<body>
	<h1>購物車</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
    	<table id="cartTable" class="cartTable">
	        <thead>
	            <tr>
	                <th>商品編號</th>
	                <th>商品名稱</th>
	                <th>訂購單價</th>
	                <th>訂購數量</th>
	            </tr>
	        </thead>
	        <tbody>
	            <s:iterator value="cartList" var="cart">
			            <tr>
	                 		<form action="<%=request.getContextPath()%>/cart/remove" >
								<td><s:property value="#cart.proNo" /></td>
								<td><s:property value="#cart.proName" /></td>
								<td><s:property value="#cart.ordPrice" /></td>
								<td>
									<s:property value="#cart.ordQty" />
								<input type="hidden" name="proNo" value="<s:property value="#cart.proNo" />"> 
								<input type="submit" value="刪除">
								</td>
	                  		</form>
						</tr>
	            </s:iterator>
	        </tbody>
	    </table>
	    <form action="<%=request.getContextPath()%>/cart/confirmOrder" >
	   		 <input type="submit" value="下單">
	    </form>
	</main>
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    
    
    <script type="text/javascript">
    $(document).ready(function() {
        $('#cartTable').DataTable({
       	  	 searching: false, // 禁用搜索功能
            
             info: false       // 禁用信息显示
        });
    });
</script>
</body>

</html>