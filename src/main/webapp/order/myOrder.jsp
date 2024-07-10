<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<title>訂單</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ordInfo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTable.css">
</head>

<body>
	<h1>Order</h1>
	<%@ include file="/util/navi.jsp" %>
	<main>
    	<s:iterator value="ordList" var="ord">
    		<div class="order" onclick="toggleDetails(this)">
	                <p>訂單編號: <s:property value="#ord.ordNo" /></p>
	                <p>訂單金額: <s:property value="#ord.ordPrice" /></p>
	            
                    <p>訂單狀態:
					    <s:if test="%{#ord.ordSt == 0}">
					        未出貨
					    </s:if>
					    <s:elseif test="%{#ord.ordSt == 1}">
					        已出貨
					    </s:elseif>
					    <s:else>
					        其他狀態
					    </s:else>
					</p>
        	</div>
            <div class="dtl">
                <table class="dtlTable">
                    <thead>
                        <tr>
                            <th>商品編號</th>
                            <th>商品名稱</th>
                            <th>訂購單價</th>
                            <th>訂購數量</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<s:set var="dtlList" value="dtlMap[#ord.ordNo]" />
                        <!--<s:iterator value="dtlMap[#ord.ordNo]" var="dtlList"> -->
                            <s:iterator value="dtlList" var="dtl">
                                <tr>
                                    <td><s:property value="#dtl.pro.proNo" /></td>
                                    <td><s:property value="#dtl.pro.proName" /></td>
                                    <td><s:property value="#dtl.dtlPrice" /></td>
                                    <td><s:property value="#dtl.dtlQty" /></td>
                                </tr>
                            </s:iterator>
                        <!--</s:iterator>-->
                    </tbody>
                </table>
            </div>
    	</s:iterator>
	</main>

    
    <script>
    	function toggleDetails(element) {
	        var dtlDiv = element.nextElementSibling;
	        
	        dtlDiv.style.display = (dtlDiv.style.display === 'none' || dtlDiv.style.display === '') ? 'block' : 'none';
	        element.classList.toggle('picked');
	    }
	</script>
</body>

</html>