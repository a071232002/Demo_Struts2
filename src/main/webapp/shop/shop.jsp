<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<title>商店</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTable.css">




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
							<td><s:property value="#pro.proNo" /></td>
							<td><s:property value="#pro.proName" /></td>
							<td><s:property value="#pro.proPrice" /></td>
							<td>
							 <s:if test="%{#pro.proQty > 0}">
                 				<form action="<%=request.getContextPath()%>/cart/add" >
								<label>請選擇數量</label> 
								<select name="proQty" id="proQtySelect">
									<s:iterator begin="1" end="%{#pro.proQty}" var="i">
										<option value="<s:property value="#i"/>"><s:property value="#i" /></option>
									</s:iterator>
								</select> 
								<input type="hidden" name="proNo" value="<s:property value="#pro.proNo"/>" />
								<input type="hidden" name="proName" value="<s:property value="#pro.proName"/>" />
								<input type="hidden" name="proPrice" value="<s:property value="#pro.proPrice"/>" />
								<input type="submit" class="submit-button" value="加入購物車" id="submitButton"/>
                  				</form>
                  			</s:if>
                  			<s:else>
			                    <p>缺貨</p>
			                </s:else>
							</td>
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
    
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var proQtySelect = document.getElementById('proQtySelect');
        var submitButton = document.getElementById('submitButton');
        
        // 初始时检查一次
        checkProQty();

        // 监听 select 变化事件
        proQtySelect.addEventListener('change', function() {
            checkProQty();
        });

        function checkProQty() {
            var selectedQty = parseInt(proQtySelect.value);
            if (selectedQty <= 0) {
                submitButton.disabled = true;
            } else {
                submitButton.disabled = false;
            }
        }
    });
</script>
</body>

</html>