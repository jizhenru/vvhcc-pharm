<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>设备</title>
<script src="../../pharmjs/jquery-1.11.1.min.js"></script>
<script src="../../pharmjs/bootstrap.min.js"></script>
<link href="../../pharmcss/bootstrap.min.css" rel="stylesheet" />
<script src="../../pharmjs/bootstrap-table.js"></script>
<link href="../../pharmcss/bootstrap-table.css" rel="stylesheet" />
<link href="../../pharmcss/bootstrap-editable.css" rel="stylesheet" />
<script src="../../pharmjs/bootstrap-editable.js"></script>
<script src="../../pharmjs/bootstrap-table-editable.js"></script>
<script src="../../pharmjs/bootstrap-table-zh-CN.js"></script>
<script src="../../pharmjs/bootbox.min.js"></script>
<script src="../../pharmjs/druggist/machine_table.js"></script>
<style>
.container {
	margin-left: auto;
	margin-right: auto;
	margin-top: 30px;
}
</style>

<script type="text/javascript">
	function ChkecoordinateX() {
		if ($("#coordinateX").val() == "") {
			$("#coordinateX").addClass("error");
			$("#divcoordinateX").html("&nbsp;药筒列号不能为空").css("color", "red");
			return false;
		}
		var reg = /^[0-9]*$/;
		if (!reg.test($("#coordinateX").val())) {
			$("#coordinateX").addClass("error");
			$("#divcoordinateX").html("&nbsp;药筒列号输入必须为数字").css("color", "red");
			return false;
		}
		//正确
		$("#coordinateX").removeClass("error");
		$("#divcoordinateX").html("&nbsp;OK").css("color", "green");
		return true;
	}
	function ChkecoordinateY() {
		if ($("#coordinateY").val() == "") {
			$("#coordinateY").addClass("error");
			$("#divcoordinateY").html("&nbsp;药筒行号不能为空").css("color", "red");
			return false;
		}
		var reg = /^[0-9]*$/;
		if (!reg.test($("#coordinateY").val())) {
			$("#coordinateY").addClass("error");
			$("#divcoordinateY").html("&nbsp;药筒行号输入必须为数字").css("color", "red");
			return false;
		}
		//正确
		$("#coordinateY").removeClass("error");
		$("#divcoordinateY").html("&nbsp;OK").css("color", "green");
		return true;
	}
	function ChkecoordinateZ() {
		if ($("#coordinateZ").val() == "") {
			$("#coordinateZ").addClass("error");
			$("#divcoordinateZ").html("&nbsp;药筒格号不能为空").css("color", "red");
			return false;
		}
		var reg = /^[0-9]*$/;
		if (!reg.test($("#coordinateZ").val())) {
			$("#coordinateZ").addClass("error");
			$("#divcoordinateZ").html("&nbsp;药筒格号输入必须为数字").css("color", "red");
			return false;
		}
		//正确
		$("#coordinateZ").removeClass("error");
		$("#divcoordinateZ").html("&nbsp;OK").css("color", "green");
		return true;
	}
	function Chkedrug() {
		if ($("#drug").val() == "") {
			$("#drug").addClass("error");
			$("#divdrug").html("&nbsp;药品名称不能为空").css("color", "red");
			return false;
		}

		//正确
		$("#drug").removeClass("error");
		$("#divdrug").html("&nbsp;OK").css("color", "green");
		return true;
	}

	$(function() {
		$("#coordinateX").blur(ChkecoordinateX);
		$("#coordinateY").blur(ChkecoordinateY);
		$("#coordinateZ").blur(ChkecoordinateZ);
		$("#drug").blur(Chkedrug);
		$("#regForm").submit(function() {
			var b1 = ChkecoordinateX();
			var b2 = ChkecoordinateY();
			var b3 = ChkecoordinateZ();
			var b4 = Chkedrug();
			return b1 && b2 && b3 && b4;
		});
	});
</script>
</head>
<body>
	<div class="container">
		<input id="deviceid" type="hidden" name="deviceid"
			value="${device.deviceid}">
		<%-- <input id="id" type="hidden" name="id" value="${device.id}"> --%>
		<div class="row" id="row_div">

			<ul class="nav nav-tabs " role="tablist">
				<li class="active"><a href="#map_info" role="tab"
					data-toggle="tab">药筒(柜)信息</a></li>
				<!-- 	<li><a href="#base_info" role="tab" data-toggle="tab">基本信息</a></li> -->
			</ul>
			<div class="tab-content">
				<button id="btn_add" type="button" class="btn btn-default"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
				</button>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">新增药筒</h4>
							</div>
							<div class="modal-body">
								<form id="regForm" method="post" class="form-horizontal">
									<input id="deviceid1" type="hidden" name="deviceid1"
										value="${device.deviceid}">
									<div class="form-group">
										<label for="input1"
											class="col-sm-3 control-label col-sm-offset-1">药桶（柜）列号</label>
										<div class="col-sm-5 ">
											<input type="text" name="coordinateX" id="coordinateX"
												class="form-control">
											<div id="divcoordinateX"></div>
										</div>
									</div>
									<div class="form-group">
										<label for="input2"
											class="col-sm-3 control-label col-sm-offset-1">药桶（柜）行号</label>
										<div class="col-sm-5">
											<input type="text" name="coordinateY" id="coordinateY"
												class="form-control">
											<div id="divcoordinateY"></div>
										</div>
									</div>

									<div class="form-group">
										<label for="input3"
											class="col-sm-3 control-label col-sm-offset-1">药柜格号</label>
										<div class="col-sm-5">
											<input type="text" name="coordinateZ" id="coordinateZ"
												class="form-control">
											<div id="divcoordinateZ"></div>
										</div>
									</div>
									<div class="form-group">
										<label for="input4"
											class="col-sm-3 control-label col-sm-offset-1">药品名称</label>
										<div class="col-sm-5">
											<input type="text" name="drug" id="drug" class="form-control">
											<div id="divdrug"></div>
										</div>
									</div>


									<div class="form-group">
										<div class="col-sm-offset-5 col-sm-5">
											<button type="submit" class="btn btn-default" id="sub">确认</button>
										</div>
									</div>
								</form>

							</div>

						</div>
					</div>
				</div>

				<div class="tab-pane active" id="map_info">
					
					<div class="col-md-12" id="map_info_table"></div>
				</div>
				

			</div>
		</div>
</body>
</html>