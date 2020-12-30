<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>车辆管理</title>
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
	<script type="text/javascript" src="static\jquery\jquery-1.12.4.js"></script>
	<!-- bootstrap -->
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
	<div id="car_list_container">
		<div class="btn-group" role="group">
			<a id="car_add_btn" href="jsp/carsDetail.jsp?id=1&type1=add" class="btn btn-default" role="button" >添加新信息</a>
			<a id="car_update_btn" href="javascript:void(0);" class="btn btn-default" role="button" >修改信息</a>
			<a id="car_delete_btn" href="javascript:void(0);" class="btn btn-default" role="button">删除信息</a><br><br>

		</div>
		<div id="car_list_div"></div>
	</div>

	<div class="modal fade" id="delete_confirm" tabindex="-1" role="dialog"
		 aria-labelledby="delete_confirm_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="delete_confirm_label">操作提示</h4>
				</div>
				<div class="modal-body">
					<label>确认要删除选中的数据吗？</label>
				</div>
				<div class="modal-footer">
					<button type="button" id="car_delete_submit" class="btn btn-primary"
							data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
					</button>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
<script type="text/javascript">

	var devToken = '<%=request.getParameter("devToken")%>';
	var devUri = "";
	if (typeof devToken === 'string'  &&  devToken.length  >  0){
		devUri = "devToken="+devToken;
	}

	// 点击文章列表中的数据：当前行选中，其他行取消选中
	function chooseCar(id){
		$('.car_check').each(function(index){
			// 这里的this是html dom对象，直接使用.属性取值
			// 使用jquery对象为$(this)，获取chechbox的状态用$(this).prop("checked")或者$(this).is(":checked")
			if(id == this.value){
				if(!this.checked){
					this.checked = true;
				}
			}else{
				this.checked = false;
			}
		});
		resetCarBtns();
	}
	// 初始化及重置按钮组状态
	function resetCarBtns(){

		// 获取当前选中的文章并集中到数组
		var checked = new Array();
		$('.car_check:checked').each(function(){
			checked.push($(this).val());
		});
		checked = checked || new Array();

		// 未选中时不可操作删除按钮，其他情况可以删除
		if(checked.length==0){
			$("#car_delete_btn").addClass("disabled");
		}else{
			$("#car_delete_btn").removeClass("disabled");
		}

		// 只选中一条文章才能修改
		if(checked.length==1){
			$("#car_update_btn").removeClass("disabled");
		}else{
			$("#car_update_btn").addClass("disabled");
		}
	}
	$(function(){
		if (devUri.length  >  0){
			var addUri = $("#car_add_btn").attr("href");
			$("#car_add_btn").attr("href", addUri+"&"+devUri);
		}
		// 获取文章列表数据
		$.get('carList?id=1', function(json) {
			if(json.success){
				var data = json.data;
				var contents = "";
				for(var i in data){
					contents += '<div id="car_div'+data[i].id+'" class="input-group">';
					contents += 	'<span class="input-group-addon">';
					contents += 		'<input class="car_check" type="checkbox" value="'
							+data[i].id+'" onclick="resetCarBtns()">';
					contents += 	'</span>';
					contents += 	'<a href="javascript:void(0);" onclick="chooseCar('+data[i].id+')" class="list-group-item">';
					contents += 		"车主编号:"+data[i].driverId +" "+"车牌号:"+ data[i].num+" "+"车辆颜色:"+data[i].color+" "+"车辆类型:"+
										data[i].type+" "+"车辆信息:"+data[i].message;
					contents += 	'</a>';
					contents += '</div>';
				}
				$('#car_list_div').html(contents);
				resetCarBtns();
			}else{
				alert("信息列表获取失败："+json.message);
			}
		});

		// 修改文章：新开窗口
		$("#car_update_btn").click(function(){
			var id = $('.car_check:checked:first-child').val();
			var uri = "jsp/carsDetail.jsp?type1=update&id="+id;
			if(devUri.length>0){
				uri += "&"+devUri;
			}
			window.open(uri);
		});

		// 点击删除信息按钮：弹出确认框
		$("#car_delete_btn").click(function(){
			$('#delete_confirm').modal();
		});

		// 删除文章：在弹出窗确认后调用删除接口
		$("#car_delete_submit").click(function(){
			var checked = new Array();
			$('.car_check:checked').each(function(){
				checked.push($(this).val());
			});
			$.get("carDelete?ids="+checked.join(",")+"&"+devUri, function(json){
				if(json.success){
					for(var i in checked){
						$("#car_div"+checked[i]).remove();
					}
				}else{
					var message = "删除信息失败："+json.message;
					if(devUri.length>0){
						message += "\n";
						message += json.stackTrace;
					}
					// alert(message);
				}
			});
		});


	});

</script>
</html>