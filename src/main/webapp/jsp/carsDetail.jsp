<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>信息详情</title>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- HTTP 1.1 -->
	<meta http-equiv="pragma" content="no-cache">
	<!-- HTTP 1.0 -->
	<meta http-equiv="cache-control" content="no-cache">
	<!-- Prevent caching at the proxy server -->
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<!-- jQuery -->
	<script type="text/javascript" src="static/jquery/jquery-1.12.4.min.js"></script>
	<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<!-- bootstrap validator -->
	<link href="static/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/bootstrapvalidator/js/bootstrapValidator.min.js"></script>

	<link rel="stylesheet" href="static/font-awesome-4.7.0/css/font-awesome.css">
	<link href="static/css/app.css?v=1" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="carForm">
			<input id="carId" name="id" type="hidden">
			<div class="form-group" style="margin: 50px auto 0 auto; padding: 0;">
				<label for="car_num"><label style="color: red;">*</label>车牌号:</label>
				<input type="text" class="form-control" id="car_num" name="num"
					   placeholder="eg:陕A XXXXX" data-bv-notempty="true" data-bv-notempty-message="不能为空">
				<br>
				<label for="car_driver_id"><label style="color: red;">*</label>用户ID</label>
				<input type="text" class="form-control" id="car_driver_id" name="driver_id"
					   placeholder="(1~5)" data-bv-notempty="true" data-bv-notempty-message="不能为空">
				<br>
				<label for="car_color"><label style="color: red;">*</label>颜色</label>
				<input type="text" class="form-control" id="car_color" name="color"
					   placeholder="XX色" data-bv-notempty="true" data-bv-notempty-message="不能为空">
				<br>
				<label for="car_type"><label style="color: red;">*</label>类型</label>
				<input type="text" class="form-control" id="car_type" name="type"
					   placeholder="eg:吉普" data-bv-notempty="true" data-bv-notempty-message="不能为空">
				<br>
				<label for="car_capacity"><label style="color: red;">*</label>载客量</label>
				<input type="text" class="form-control" id="car_capacity" name="capacity"
					   placeholder="(1~8)" data-bv-notempty="true" data-bv-notempty-message="不能为空">
				<br>
				<label for="car_message"><label style="color: red;">*</label>车辆信息</label>
				<input type="text" class="form-control" id="car_message" name="message"
					placeholder="汽车信息描述" data-bv-notempty="true" data-bv-notempty-message="不能为空"><br>
			</div>


			<div class="btn-group" role="group">
				<%--<button id="btn_clear" type="button" class="btn btn-danger">清除</button>--%>
				<button id="btn_submit" type="submit" class="btn btn-success">提交</button>
			</div>

		</form>
	</div>
</body>



<script type="text/javascript">


var carId = '<%=request.getParameter("id")%>';
var type = '<%=request.getParameter("type1")%>';
function carSubmit(){
	var uri = '#';
	var json = {
		num : $("#car_num").val(),
		driverId:$("#car_driver_id").val(),
		color:$("#car_color").val(),
		capacity:$("#car_capacity").val(),
		type:$("#car_type").val(),
		message:$("#car_message").val(),
	};

	// 新增博客文章，提交数据为：title,content,userAccount
    // 修改博客文章，提交数据：title,content,id
	if(type=="add"){
		uri = "carAdd";
		json.id = '<%=request.getParameter("id")%>';
	}else if(type=="update"){
		uri = "carUpdate";
		json.id = carId;
	}

	$.ajax({
		type:'post',
		url:uri,
		// ajax默认的请求类型
// 		contentType: "application/x-www-form-urlencoded",
// 		data:json,
		contentType: "application/json",
		data:JSON.stringify(json),
// 		contentType: "multipart/form-data",
		dataType:'json', // 返回值类型
		success:function(data){
			$('#carForm').bootstrapValidator('disableSubmitButtons', false);
			if(data.success){
				$('#fail-modal-content').css("width","200px");
				$('#tip_success').modal('show');
				setTimeout(function() {
					$("#tip_success").modal("hide");
				}, 1200);
			}else{
				$('#fail-modal-content').css("width","500px");
				$('#tip_failed').modal('show');
				var msg = '<i class="fa fa-check-circle text-danger mr-1" aria-hidden="true"></i>';
				if(typeof data.message === 'string'  &&  data.message.length  >  0){
					$("#tip_failed_message").html(msg+data.message);
				}else{
					$("#tip_failed_message").html(msg+"服务器内部错误");
				}
				if(typeof data.stackTrace === 'string'  &&  data.stackTrace.length  >  0){
					$("#tip_failed_dev_div").show();
					$("#tip_failed_trace").html('<div class="well">'+data.stackTrace+'</div>');
				}else{
					$("#tip_failed_dev_div").hide();
				}
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			$('#carForm').bootstrapValidator('disableSubmitButtons', false);
			$('#fail-modal-content').css("width","200px");
			$('#tip_failed').modal('show');
			if (jqXHR.status == 404){
				$("#tip_failed_message").html('<i class="fa fa-check-circle text-danger mr-1" aria-hidden="true"></i>找不到资源');
				$("#tip_failed_dev_div").hide();
			}
			console.log("jqXHR={\n"+jqXHR.status+",\n"+jqXHR.statusText+",\n"+jqXHR.responseText
					+"},\ntextStatus="+textStatus+",\nerrorThrown="+errorThrown);
		}
	});
}


$(function() {

	if(type == "add"){
		$("num").html("新增");
	}else if(type == "update"){
		$("num").html("修改车辆信息");
		$.get('carDetail?id='+carId, function(json){
			if(json.success){
				var data = json.data;
				$("#carId").val(data.id);
				$("#car_num").val(data.num);
				$("#car_driver_id").val(data.driver_id);
				$("#car_color").val(data.color);
				$("#car_capacity").val(data.capacity);
				$("#car_message").val(data.message);
			}else{
				var message = "获取信息失败："+json.message;
				alert(message);
			}

		});
	}

	// $("#b/*/!*tn_clear").click(function(e){
	// 	ue.setContent('');
	// });*!/*/

	$("#carForm").submit(function(ev){
		ev.preventDefault();
		$('#carForm').bootstrapValidator('disableSubmitButtons', false);
	});
	$("#btn_submit").click(function(){
		var bootstrapValidator = $("#carForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			// var content=ue.getContent().trim();
			// if (content.length==0){
			// 	$("#contentErrorInfo").text("内容不能为空");
			// 	ue.focus(true);
			// 	return;
			// }
			carSubmit();
		} else{
			return;
		}
	});

	$('#carForm').bootstrapValidator();

});
</script>
</html>