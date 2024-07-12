<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>後台管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userPage.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ordInfo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTable.css">

<style>
	  
      .overlay {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          background: rgba(0, 0, 0, 0.5);
          z-index: 1000;
          display: none;
          justify-content: center;
          align-items: center;
          color: #fff;
          font-size: 24px;
      }
      
      main {
      	  padding:20px;
      }
      
      .controlArea {
		  display: flex;
		  align-items: center;
		  padding: 10px;
		  margin-bottom: 20px;
	  }
		
	  .controlArea .proOption,
	  .controlArea .ordOption {
	  	  width: 290px;
	  	  margin-right: 20px;
	      display: flex;
	      flex-direction: column;
	      align-items: center;
	      padding: 10px;
    	  border: 1px solid #ccc;
    	  border-radius: 5px;
    	  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
    	  background-color: #fff;
      }
      
      .ordOption {
      	  margin-bottom: 5px;
		  font-weight: bold;
		  text-align: center;
      }
</style>

</head>
<body>
	<h1>Manage</h1>
	<%@ include file="/util/navi.jsp"%>
	<main>
	<div class="controlArea">
		<div class="proOption">
			<form id="proUpload" class="form" method="post" enctype="multipart/form-data">
				<label>請選擇Excel檔案</label> 
				<input type="file" id="proData" name="proData" class="input" />
				<input type="button" value="匯入商品" class="button" />
			</form>
		</div>
		<div class="ordOption">
			<label>訂單編號</label>
			<input type="text" id="ordNo" name="ordNo" class="input">
			<input type="button" id="queryOrdBtn" value="顯示訂單" class="button" />
		</div>
	</div>
    <div class="overlay">
        處理中...
    </div>
	<div class="resultArea"></div>
	</main>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	
	
	<!-- 新增商品 -->
	<script>
		$(document).ready(function() {
			$('#proUpload .button').click(function(event) {
				event.preventDefault(); 
                $('.overlay').css('display', 'flex');
                
				var formData = new FormData($('#proUpload')[0]);
				
				$.ajax({
					url : '<%=request.getContextPath()%>/manage/proUpload', 
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					success : function(response) {

                        $('.overlay').hide();
																		 
						alert('匯入成功，新增' + response.insertCount + '項商品');

				        $('.resultArea').empty();
				        var tableHtml = generateProductTable(response.newProList);
				        $('.resultArea').append(tableHtml);
				        $('#proData').val('')
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
						
                        $('.overlay').hide();
						
						alert('匯入失敗' + textStatus);
					}
				});
			});
		});

		function generateProductTable(products) {
		    var tableHtml = `
			    <p>商品新增結果如下</p>
		        <table class="newProTable">
		            <thead>
		                <tr>
		                    <th>商品編號</th>
		                    <th>商品名稱</th>
		                    <th>商品價格</th>
		                    <th>商品庫存</th>
		                </tr>
		            </thead>
		            <tbody>
		    `;
			console.log(products);
		    products.forEach(function(pro) {
			    console.log(pro);
		        tableHtml += `
		            <tr>
		                <td>\${pro.proNo}</td>
		                <td>\${pro.proName}</td>
		                <td>\${pro.proPrice}</td>
		                <td>\${pro.proQty}</td>
		            </tr>
		        `;
		    });
		    tableHtml += `
		            </tbody>
		        </table>
		    `;
		    return tableHtml;
		}
	</script>
	
	
	<!-- 顯示訂單 -->
	<script>
        $(document).ready(function() {
            $('#queryOrdBtn').click(function() {
            	$('.overlay').css('display', 'flex');
                var ordNo = $('#ordNo').val();
                console.log(ordNo);
                $.ajax({
                    url: '<%=request.getContextPath()%>/manage/getOrderInfo',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ ordNo: ordNo }),
                    success: function(response) {
                    	console.log(JSON.stringify({ ordNo: ordNo }));
                        $('.overlay').hide();
                      
                        $('.resultArea').empty();
				        var ordTableHtml = generateOrderTable(response.ordDTOList);
				        $('.resultArea').append(ordTableHtml);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error('Error: ' + textStatus, errorThrown);
                        $('.overlay').hide();
                    }
                });
            });
        });

        function generateOrderTable(ords) {
		    var ordTableHtml = '';
		    ordTableHtml += `<p>訂單查詢結果如下</p> `;
			ords.forEach(ord => {
				ordTableHtml += `
					<div class="order" onclick="toggleDetails(this)">
						<p>訂單編號: \${ord.ordNo}</p>
						<p>訂購人編號: \${ord.userNo}</p>
						<p>訂單金額: \${ord.ordPrice}</p>
						<p>訂單狀態: \${ord.ordSt}</p>
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
		   		 `;
			
			    ord.dtlDTOList.forEach(function(dtl) {
				    
			    	ordTableHtml += `
			            <tr>
			                <td>\${dtl.proNo}</td>
			                <td>\${dtl.proName}</td>
			                <td>\${dtl.ordPrice}</td>
			                <td>\${dtl.ordQty}</td>
			            </tr>
			        `;
			    });
			    ordTableHtml += `
			            	</tbody>
			        	</table>
			        </div>
			    `;
		    
			});
		    return ordTableHtml;
		}


        function toggleDetails(element) {
	        var dtlDiv = element.nextElementSibling;
	        
	        dtlDiv.style.display = (dtlDiv.style.display === 'none' || dtlDiv.style.display === '') ? 'block' : 'none';
	        element.classList.toggle('picked');
	    }
        
    </script>
	
	
	
</body>

</html>