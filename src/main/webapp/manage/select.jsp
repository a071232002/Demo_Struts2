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
		
	  .controlArea .downloadArea,
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
      
      .controlArea .downloadArea {
      	  width: 200px;
      }
      
      .ordOption {
      	  margin-bottom: 5px;
		  font-weight: bold;
		  text-align: center;
      }
      
	    .ordInfo {
		    display: flex;
		    align-items: center; 
		    margin-bottom: 20px; 
		}
		
		.ordInfo input,
		.ordInfo div	{
			vertical-align: middle;
		}
		
		.ordInfo div	{
			width :auto;
			flex: 1; 
		}
		
	    .order-checkbox {
		    width: 20px; 
		    height: 20px; 
		   	margin-right: 10px;
		}
		
		.checkAll {
		    width: 20px; 
		    height: 20px; 
		   	margin-right: 10px;
		}
      
</style>

</head>
<body>
	<h1>Manage</h1>
	<%@ include file="/util/navi.jsp"%>
	<main>
	<div class="controlArea">
		<div class="downloadArea">
			<input type="button" id="downloadSpec" value="下載商品匯入範本" class="button">
		</div>
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
	
	
	<script>
        $(document).ready(function() {
            $('#downloadSpec').click(function() {
                
                $.ajax({
                    url: 'downloadSpec',
                    method: 'GET',
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function(data) {
                        var a = document.createElement('a');
                        var url = window.URL.createObjectURL(data);
                        a.href = url;
                        a.download = 'example.xlsx';
                        document.body.appendChild(a);
                        a.click();
                        window.URL.revokeObjectURL(url);
                        document.body.removeChild(a);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error downloading file:', error);
                        alert('下載檔案失敗');
                    }
                });
            });
        });
    </script>
	
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
                        if (jqXHR.status === 404) {
							alert('請選擇檔案, 並檢查格式');
							$('#proData').val('')
                        } else {
                        	alert('匯入失敗, 請檢查格式');
                        	$('#proData').val('')
                            }
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
	                var ordNo = $('#ordNo').val().trim();
	                
	                if (ordNo !== "" &&!/^\d+$/.test(ordNo)) {
	                 alert('請輸入數字');
	                 $('.overlay').hide();
	                 return;
	                }
	                
	                
	                $.ajax({
	                    url: '<%=request.getContextPath()%>/manage/getOrderInfo',
	                    type: 'POST',
	                    contentType: 'application/json',
	                    
	                    data: JSON.stringify({ ordNo: ordNo }),
	                    success: function(response) {
	                    	
	                        $('.overlay').hide();
	                      
	                        $('.resultArea').empty();
					        var ordTableHtml = generateOrderTable(response.ordDTOList);
					        bindDeleteOrderButton();
					        $('.resultArea').append(ordTableHtml);
	                    },
	                    error: function(jqXHR, textStatus, errorThrown) {
	                        console.error('Error: ' + textStatus, errorThrown);
	                        alert('查無此訂單編號');
	                        $('.overlay').hide();
	                    }
	                });
	            });
        	});
       	

        function generateOrderTable(ords) {
		    var ordTableHtml = '';
		    ordTableHtml += `
			    			<p>訂單查詢結果如下</p>
			    			<div class="ordInfo" style="margin-bottom:10px;">
			    			<input type="checkbox" class="checkAll" id="checkAll" name="checkAll"><p style="margin-right:20px;">全選</p>
			    			<button id="deleteOrderBtn" class="delete-button">刪除</button>
			    			</div>
			    			`;
			ords.forEach(ord => {
				ordTableHtml += `
					<div class="ordInfo">
						<input type="checkbox" class="order-checkbox" id="ord-\${ord.ordNo}" name="ordNos" value="\${ord.ordNo}">
					    
						<div class="order" onclick="toggleDetails(this)">
							<p>訂單編號: \${ord.ordNo}</p>
							<p>訂購人編號: \${ord.userNo}</p>
							<p>訂單金額: \${ord.ordPrice}</p>
							<p>訂單狀態: \${ord.ordSt}</p>
						</div>
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
	        console.log(element.parentElement.nextElementSibling);
	        var dtlDiv = element.parentElement.nextElementSibling;
	        dtlDiv.style.display = (dtlDiv.style.display === 'none' || dtlDiv.style.display === '') ? 'block' : 'none';
	        element.classList.toggle('picked');
	    }


        function bindDeleteOrderButton() {
	        $(document).ready(function() {
	            $('#deleteOrderBtn').click(function() {
	        	$('.overlay').css('display', 'flex');
	                var ordNos = [];
	
	                $('.order-checkbox:checked').each(function() {
	                	ordNos.push($(this).val());
	                });
					console.log(ordNos);
	                sendOrdNos(ordNos);
	            });

	            $('#checkAll').click(function() {
	                var isChecked = $(this).prop('checked');
	                $('.order-checkbox').prop('checked', isChecked);
	            });
	          
	            function sendOrdNos(ordNos) {
	                
	                $.ajax({
	                    url: '<%=request.getContextPath()%>/manage/delete', 
	                    type: 'POST',
	                    contentType: 'application/json; charset=utf-8',
	                    data: JSON.stringify({ordNos}),
	                    success: function(response) {
	                    	alert('刪除成功');
	                    	$('.resultArea').empty();
	                    	$('.overlay').hide();
	                    },
	                    error: function(xhr, status, error) {
	                    	alert('刪除失敗');
	                    	$('.overlay').hide();
	                    }
	                });
	            }
      		});
       	}
        
    </script>
	
	
	
	
	
	
</body>

</html>