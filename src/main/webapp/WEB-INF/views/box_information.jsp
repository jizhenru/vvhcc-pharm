<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script src="../../pharmjs/Administrator/machine_table.js"></script>
	<style>
		.container{
		margin-left:auto;
		margin-right:auto;
		margin-top:30px;
		}
	</style>
<script type="text/javascript">
 function Chkename(){
     if ($("#name").val() == "") {
         $("#name").addClass("error");
         $("#divname").html("&nbsp;设备名不能为空").css("color","red");
         return false;
     }
     //正确
     $("#name").removeClass("error");
     $("#divname").html("&nbsp;OK").css("color","green");
     return true;
 }
 function Chkdeviceid(){
     if ($("#device_id").val() == "") {
         $("#device_id").addClass("error");
         $("#divdevice_id").html("&nbsp;药筒编号不能为空").css("color","red");
         return false;
     }
     //数字格式验证
     var reg = /^[0-9]*$/;
     if (!reg.test($("#device_id").val())) {
         $("#device_id").addClass("error");
         $("#divdevice_id").html("药筒编号输入必须为数字").css("color","red");
         return false;
     } 
     //正确
     $("#device_id").removeClass("error");
     $("#divdevice_id").html("&nbsp;OK").css("color","green");
     return true;
 } 

 function Chktype(){
     if ($("#type").val() == "") {
         $("#type").addClass("error");
         $("#divtype").html("&nbsp;设备型号不能为空").css("color","red");
         return false;
     }
     
     //正确
     $("#type").removeClass("error");
     $("#divtype").html("&nbsp;OK").css("color","green");
     return true;
 } 

 function Chkpharmacyid(){
     if ($("#pharmacyid").val() == "") {
         $("#pharmacyid").addClass("error");
         $("#divpharmacyid").html("&nbsp;药房编号不能为空").css("color","red");
         return false;
     }
      var reg = /^[0-9]*$/;
     if (!reg.test($("#pharmacyid").val())) {
         $("#pharmacyid").addClass("error");
         $("#divpharmacyid").html("&nbsp;输入必须为数字").css("color","red");
         return false;
     } 
     //正确
     $("#pharmacyid").removeClass("error");
     $("#divpharmacyid").html("&nbsp;OK").css("color","green");
     return true;
 } 
 function ChkIPADDR(){
     if ($("#IPADDR").val() == "") {
         $("#IPADDR").addClass("error");
         $("#divIPADDR").html("&nbsp;设备IP不能为空").css("color","red");
         return false;
     }
     
     //正确
     $("#IPADDR").removeClass("error");
     $("#divIPADDR").html("&nbsp;OK").css("color","green");
     return true;
 } 
 
 function Chkstatus(){
     if ($("#status").val() == "") {
         $("#status").addClass("error");
         $("#divstatus").html("&nbsp;状态不能为空").css("color","red");
         return false;
     }
      var reg = /^[0-9]*$/;
     if (!reg.test($("#status").val())) {
         $("#status").addClass("error");
         $("#divstatus").html("&nbsp;状态输入必须为数字").css("color","red");
         return false;
     } 
     //正确
     $("#status").removeClass("error");
     $("#divstatus").html("&nbsp;OK").css("color","green");
     return true;
 } 
 
 $(function(){
	   $("#status").blur(Chkstatus);
    	   $("#name").blur(Chkename);
    	   $("#type").blur(Chktype);
 	      $("#device_id").blur(Chkdeviceid);
 	     $("#pharmacyid").blur(Chkpharmacyid);
 	    $("#IPADDR").blur(ChkIPADDR);
    	   $("#regForm1").submit(function () {
    		  	var b1 = Chkename();	
    		 	var b2 = Chkdeviceid();
    			var b3 = Chktype();
    			var b4=Chkpharmacyid();
    			var b5=ChkIPADDR();
    			var b6=Chkstatus();
    		  	return b1 && b2 && b3 && b4 && b5 && b6 ;
                 
    	     });
     });
 
 function ChkecoordinateX(){
	    if ($("#coordinateX").val() == "") {
	        $("#coordinateX").addClass("error");
	        $("#divcoordinateX").html("&nbsp;药筒列号不能为空").css("color","red");
	        return false;
	    }
	    var reg = /^[0-9]*$/;
	    if (!reg.test($("#coordinateX").val())) {
	        $("#coordinateX").addClass("error");
	        $("#divcoordinateX").html("&nbsp;药筒列号输入必须为数字").css("color","red");
	        return false;
	    } 
	    //正确
	    $("#coordinateX").removeClass("error");
	    $("#divcoordinateX").html("&nbsp;OK").css("color","green");
	    return true;
	}
	 function ChkecoordinateY(){
	    if ($("#coordinateY").val() == "") {
	        $("#coordinateY").addClass("error");
	        $("#divcoordinateY").html("&nbsp;药筒行号不能为空").css("color","red");
	        return false;
	    }
	    var reg = /^[0-9]*$/;
	    if (!reg.test($("#coordinateY").val())) {
	        $("#coordinateY").addClass("error");
	        $("#divcoordinateY").html("&nbsp;药筒行号输入必须为数字").css("color","red");
	        return false;
	    } 
	    //正确
	    $("#coordinateY").removeClass("error");
	    $("#divcoordinateY").html("&nbsp;OK").css("color","green");
	    return true;
	}
	function ChkecoordinateZ(){
	    if ($("#coordinateZ").val() == "") {
	        $("#coordinateZ").addClass("error");
	        $("#divcoordinateZ").html("&nbsp;药筒格号不能为空").css("color","red");
	        return false;
	    }
	    var reg = /^[0-9]*$/;
	    if (!reg.test($("#coordinateZ").val())) {
	        $("#coordinateZ").addClass("error");
	        $("#divcoordinateZ").html("&nbsp;药筒格号输入必须为数字").css("color","red");
	        return false;
	    } 
	    //正确
	    $("#coordinateZ").removeClass("error");
	    $("#divcoordinateZ").html("&nbsp;OK").css("color","green");
	    return true;
	}
	function Chkedrug(){
	    if ($("#drug").val() == "") {
	        $("#drug").addClass("error");
	        $("#divdrug").html("&nbsp;药品名称不能为空").css("color","red");
	        return false;
	    }
	    //正确
	    $("#drug").removeClass("error");
	    $("#divdrug").html("&nbsp;OK").css("color","green");
	    return true;
	}
	 
	$(function(){
		   $("#coordinateX").blur(ChkecoordinateX);
	 	   $("#coordinateY").blur(ChkecoordinateY);
		   $("#coordinateZ").blur(ChkecoordinateZ);
		   $("#drug").blur(Chkedrug); 
		   $("#regForm3").submit(function () {
			  	var b1 = ChkecoordinateX();	
		 	 	var b2 = ChkecoordinateY();
				var b3 = ChkecoordinateZ();
				var b4 = Chkedrug();  
		
			  	return b1 && b2 && b3 && b4 ;

		     });
	});

 
</script>
<script type="text/javascript">

</script>
</head>
<body >

 <div class="container">
<input id="deviceid" type="hidden" name="deviceid" value="${device.deviceid}">
<%-- <input id="id" type="hidden" name="id" value="${device.id}"> --%>
      <div class="row" id="row_div">
      
			<ul class="nav nav-tabs " role="tablist" >
				<li class="active"><a href="#map_info" role="tab" data-toggle="tab">药筒(柜)信息</a></li>
				<li><a href="#base_info" role="tab" data-toggle="tab">基本信息</a></li>
			</ul>
			<div class="tab-content">
			 <button id="btn_add" type="button" class="btn btn-default"  data-toggle="modal" data-target="#myModal">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			           </button>
			             <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="myModalLabel">新增药筒</h4>
				      </div>
				      <div class="modal-body">
					      <form id="regForm3" method="post"   class="form-horizontal" >
							 <input id="deviceid1" type="hidden" name="deviceid1" value="${device.deviceid}">
							  <div class="form-group">
								<label for="input1" class="col-sm-3 control-label col-sm-offset-1">药桶（柜）列号</label>
								<div class="col-sm-5 ">
					     		 <input type="text" name="coordinateX"  id="coordinateX" class="form-control">
								<div id="divcoordinateX"></div>
								</div>
							  </div>
							  <div class="form-group">
								<label for="input2" class="col-sm-3 control-label col-sm-offset-1">药桶（柜）行号</label>
								<div class="col-sm-5">
								  <input type="text" name="coordinateY" id="coordinateY" class="form-control" >
								<div id="divcoordinateY"></div>
								</div>
							  </div>
							  
							   <div class="form-group">
								<label for="input3" class="col-sm-3 control-label col-sm-offset-1">药柜格号</label>
								<div class="col-sm-5">
								  <input type="text" name="coordinateZ" id="coordinateZ" class="form-control" >
								<div id="divcoordinateZ"></div>
								</div>
							  </div>
							  	   <div class="form-group">
								<label for="input4" class="col-sm-3 control-label col-sm-offset-1" >药品名称</label>
								<div class="col-sm-5">
								  <input type="text" name="drug" id="drug" class="form-control"  >
										<div id="divdrug"></div>
								</div>
							  </div>	
							  
					
							  <div class="form-group">
								<div class="col-sm-offset-5 col-sm-5">
			                <button type="submit" class="btn btn-default" id="sub" >确认</button>
								</div>
							  </div>		
						</form>
					
				      </div>
				    
				    </div>
				  </div>
				</div>
					
				<div class="tab-pane active" id="map_info">
				
					<!--  <button id="btn_add" type="button" class="btn btn-default"  data-toggle="modal" data-target="#myModal">
			                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			           </button> -->
			  
					<div class="col-md-12" id="map_info_table">
					</div>	
				</div>
				<div class="tab-pane" id="base_info">
				
				
					<div class="col-md-12" id="base_info_table">
					</div>
					
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">编辑设备信息</h4>
					      </div>
					      <div class="modal-body">
					        <form id="regForm1" class="form-horizontal" >
								  <div class="form-group">
									<label for="input1" class="col-sm-2 control-label">设备名</label>
									<div class="col-sm-5">
							  <input type="hidden" name="id" id="id" value="${device.id}">
							  <input type="text" name="name" class="form-control" id="name" value="${device.name}">
									<div id="divname"></div>
									</div>
								  </div>
								  <div class="form-group">
									<label for="input2" class="col-sm-2 control-label">设备编号</label>
									<div class="col-sm-5">
									  <input type="text" name="device_id" id="device_id" class="form-control"  value="${device.deviceid}">
									<div id="divdevice_id"></div>
									</div>
								  </div>
								  
								   <div class="form-group">
									<label for="input3" class="col-sm-2 control-label">设备型号</label>
									<div class="col-sm-5">
									  <input type="text" name="type" id="type" class="form-control"  value="${device.type}">
									 <div id="divtype"></div>
									</div>
								  </div>
								  	   <div class="form-group">
									<label for="input4" class="col-sm-2 control-label">药房编号</label>
									<div class="col-sm-5">
									  <input type="text" name="pharmacyid" id="pharmacyid" class="form-control" value="${device.pharmacyid}">
										 <div id="divpharmacyid"></div>
									</div>
								  </div>	
								  <div class="form-group">
									<label for="input5" class="col-sm-2 control-label">设备IP</label>
									<div class="col-sm-5">
									  <input type="text" name="IPADDR" id="IPADDR" class="form-control"  value="${device.IPADDR}">
									 <div id="divIPADDR"></div>
									</div>
								  </div>
					 		  <div class="form-group">
									<label for="input6" class="col-sm-2 control-label">设备状态</label>
									<div class="col-sm-5">
									   <input type="text" name="status" id="status" class="form-control"  value="${device.status}">
								   <div id="divstatus"></div>
								  </div> 
								  <br><br>
								  
								  <div class="form-group">
									<div class="col-sm-offset-4 col-sm-5">
									  <button type="submit" class="btn btn-default" id="subupdate">确认</button>
									</div>
								  </div>
					  			  
							</form>
					    </div>
					  </div>
					</div>
				</div>				
				
			</div>

		</div>
	</div>


</body>
</html>