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
            border: 2px solid #ddd;
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
            border-right: 1px solid #ddd;
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
        #orderAmount {
		    margin: 10px 20px;
		    color: blue;
		    font-size: 16px;
		    line-height: 1.4;
		    font-weight: bold;
		    text-decoration: underline;
		}
</style>
    
</head>
<body>
	<h1>購物車</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
    	<table  class="cartTable">
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
							<td>
								<s:property value="#cart.proNo" />
							</td>
							<td><s:property value="#cart.proName" /></td>
							<td><s:property value="#cart.ordPrice" /></td>
							<td>
								<div style="display: flex; align-items: center; justify-content: center;">
								<form action="<%=request.getContextPath()%>/cart/remove" >
										<input type="hidden" name="proNo" value="<s:property value="#cart.proNo" />"> 
										<input type="submit" value="X" class="delete-button">
			                  	</form>
								<button style="margin:0px 5px;" onclick="updateQuantity('<s:property value="#cart.proNo" />', -1)">-</button>
                				<span id="qty-<s:property value="#cart.proNo" />"><s:property value="#cart.ordQty" /></span>
                				<button style="margin:0px 5px;" onclick="updateQuantity('<s:property value="#cart.proNo" />', 1)">+</button>
                				</div>
							</td>
						</tr>
	            </s:iterator>
	        </tbody>
	    </table>
	    <div style="display: flex; justify-content: flex-end; align-items: center;">
	    <p id="orderAmount">總金額: <s:property value="#session.orderAmount" default="0"/>
	    <form action="<%=request.getContextPath()%>/cart/confirmOrder">
	   		 <input type="submit" value="下單" class="submit-button">
	    </form>
	    </div>
	</main>
	
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    
    
    <script type="text/javascript">
    $(document).ready(function() {
        $('#cartTable').DataTable({
       	  	 searching: false, 
             info: false       
        });
    });

    function updateQuantity(proNo, change) {
        var currentQty = parseInt(document.getElementById('qty-' + proNo).innerText);
        var newQty = currentQty + change;
        
        if (newQty < 1) {
            return;
        }

        document.getElementById('qty-' + proNo).innerText = newQty;

        fetch('<%=request.getContextPath()%>/cart/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'proNo=' + proNo + '&proQty=' + newQty
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .then(data => {
            console.log('Success:', data);
        	document.getElementById('orderAmount').innerText = '總金額: ' + data.orderAmount;
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
        
    
</script>
</body>

</html>