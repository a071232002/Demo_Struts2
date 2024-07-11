<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>後台管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navi.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userPage.css">

<style>
	  /* 覆盖层的样式 */
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
</style>

</head>
<body>
	<h1>Manage</h1>
	<%@ include file="/util/navi.jsp"%>
	<main>
	<div class="controlArea">
		<form id="proUpload" class="form" method="post" enctype="multipart/form-data">
			<label>請選擇Excel檔案</label> 
			<input type="file" id="proData" name="proData" class="input" />
			<input type="button" value="匯入商品" class="button" />
		</form>
		
	</div>
	
	<!-- 覆盖层 -->
    <div class="overlay">
        處理中，請稍候...
    </div>
	
	
	<div class="resultArea"></div>
	
	</main>


	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	
	<!-- 新增商品 -->
	<script>
		$(document).ready(function() {
			$('#proUpload .button').click(function(event) {
				event.preventDefault(); 
				// 显示覆盖层
                $('.overlay').css('display', 'flex');
                
				var formData = new FormData($('#proUpload')[0]);
				
				$.ajax({
					url : '<%=request.getContextPath()%>/manage/proUpload', 
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					success : function(response) {

						// 隐藏覆盖层
                        $('.overlay').hide();
																		 
						alert('匯入成功，新增' + response.insertCount + '項商品');

						// 清空之前的内容
				        $('.viewArea').empty();

				        // 生成表格 HTML 并插入到 .viewArea 元素中
				        var tableHtml = generateProductTable(response.newProList);
				        $('.resultArea').append(tableHtml);
				        $('#proData').val('')
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
						
						 // 隐藏覆盖层
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
	
</body>

</html>