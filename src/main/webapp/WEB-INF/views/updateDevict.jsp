<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="img/logo_ico.ico" />
<link href="pharmcss/bootstrap.min.css" rel="stylesheet">
<script src="pharmjs/jquery-1.11.1.min.js"></script>
<script src="pharmjs/bootstrap.min.js"></script>
<link href="pharmcss/bootstrap.min.css" rel="stylesheet" />
    <script src="../../pharmjs/Administrator/machine_table.js"></script>
<style>
  .container{
	margin-top:20px	
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
    	   $("#type").blur(Chkename);
 	      $("#device_id").blur(Chktype);
 	     $("#pharmacyid").blur(Chkpharmacyid);
 	    $("#IPADDR").blur(ChkIPADDR);
    	   $("#regForm").submit(function () {
    		  	var b1 = Chkename();	
    		 	var b2 = Chkdeviceid();
    			var b3 = Chktype();
    			var b4=Chkpharmacyid();
    			var b5=ChkIPADDR();
    			var b6=Chkstatus();
    		  	return b1 && b2 && b3 && b4 && b5 && b6 ;
    		  	
    	     });
     });

 
  
</script>
</head>
<body>
<div class="container">
			<div class="row">
				<div class="col-md-10 ">
					<br>
					<form id="regForm" class="form-horizontal">
					  <div class="form-group">
						<label for="input1" class="col-sm-2 control-label">设备名</label>
						<div class="col-sm-5">
				 <input type="text" name="name" class="form-control" id="inputnumber" value="${device.name}">
						<div id="divname"></div>
						</div>
					  </div>
					  <div class="form-group">
						<label for="input2" class="col-sm-2 control-label">设备编号</label>
						<div class="col-sm-5">
						  <input type="text" name="device_id" id="device_id" class="form-control" id="inputnumber" value="${device.deviceid}">
						<div id="divdevice_id"></div>
						</div>
					  </div>
					  
					   <div class="form-group">
						<label for="input3" class="col-sm-2 control-label">设备型号</label>
						<div class="col-sm-5">
						  <input type="text" name="type" id="type" class="form-control" id="inputnumber" value="${device.type}">
						 <div id="divtype"></div>
						</div>
					  </div>
					  	   <div class="form-group">
						<label for="input4" class="col-sm-2 control-label">药房编号</label>
						<div class="col-sm-5">
						  <input type="text" name="pharmacyid" id="pharmacyid" class="form-control" id="inputnumber" value="${device.pharmacyid}">
							 <div id="divpharmacyid"></div>
						</div>
					  </div>	
					  <div class="form-group">
						<label for="input5" class="col-sm-2 control-label">设备IP</label>
						<div class="col-sm-5">
						  <input type="text" name="IPADDR" id="IPADDR" class="form-control" id="inputtext" value="${device.IPADDR}">
						 <div id="divIPADDR"></div>
						</div>
					  </div>
					  <div class="form-group">
						<label for="input6" class="col-sm-2 control-label">设备状态</label>
						<div class="col-sm-5">
						   <input type="text" name="status" id="status" class="form-control" id="inputtext" value="${device.status}">
					   <div id="divstatus"></div>
					  </div>
					  <br><br>
					  <div class="form-group">
						<div class="col-sm-offset-4 col-sm-5">
						 <button type="submit" class="btn btn-default" id="subupdate">确认</button>
						</div>
					  </div>
		      <input type="hidden" name="id" value="${device.id}">
					</form>
				</div>
         
</body>
</html>