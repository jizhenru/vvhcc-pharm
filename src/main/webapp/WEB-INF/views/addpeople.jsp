<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加人员</title>

<link rel="shortcut icon" href="../img/logo_ico.ico" />
<link href="../pharmcss/bootstrap.min.css" rel="stylesheet">
	  <script src="../../pharmjs/jquery-1.11.1.min.js"></script>
<script src="../pharmjs/jquery-1.11.1.min.js"></script>
<script src="../pharmjs/bootstrap.min.js"></script>
<link href="../pharmcss/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript">
function Chekname(){
    if ($("#name").val() == "") {
        $("#name").addClass("error");
        $("#divname").html("&nbsp;姓名不能为空").css("color","red");
        return false;
    }  
    //正确
    $("#name").removeClass("error");
    $("#divname").html("&nbsp;OK").css("color","green");
    return true;
	 
}
function Chekusername(){
    if ($("#username").val() == "") {
        $("#username").addClass("error");
        $("#divusername").html("&nbsp;用户名不能为空").css("color","red");
        return false;
    } 
    //正确
    $("#username").removeClass("error");
    $("#divusername").html("&nbsp;OK").css("color","green");
    return true;
	 
}
function Chekpwd(){
    if ($("#pwd").val() == "") {
        $("#pwd").addClass("error");
        $("#divpwd").html("&nbsp;密码不能为空").css("color","red");
        return false;
    }
    var reg =/^[a-zA-Z0-9]{6,10}$/;
    if (!reg.test($("#pwd").val())) {
        $("#pwd").addClass("error");
        $("#divpwd").html("&nbsp;密码长度6-10位由数字和字符组成").css("color","red");
        return false;
    } 
    //正确
    $("#pwd").removeClass("error");
    $("#divpwd").html("&nbsp;OK").css("color","green");
    return true;
	 
}
 function Chektelephone(){
    if ($("#telephone").val() == "") {
        $("#telephone").addClass("error");
        $("#divtelephone").html("&nbsp;联系电话不能为空").css("color","red");
        return false;
    }
    var phone = document.getElementById('telephone').value;
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){ 
        $("#divtelephone").html("&nbsp;手机号码有误，请重填").css("color","red");
        return false; 
    } 
    
    //正确
    $("#telephone").removeClass("error");
    $("#divtelephone").html("&nbsp;OK").css("color","green");
    return true;
 
} 
 function Chekroleid(){
	    if (document.getElementById("roleid").checked=="") {
	        $("#roleid").addClass("error");
	        $("#divroleid").html("&nbsp;角色必选").css("color","red");
	        return false;
	    }
	    
	    //正确
	    $("#roleid").removeClass("error");
	    $("#divroleid").html("&nbsp;OK").css("color","green");
	    return true;
		 
	}
$(function(){
	   $("#name").blur(Chekname);
	   $("#username").blur(Chekusername);
	   $("#pwd").blur(Chekpwd);
 	   $("#telephone").blur(Chektelephone); 
 	   $("#roleid").blur(Chekroleid); 
	   $("#regfrom").submit(function () {
		  	var b1 = Chekusername();	
		 	var b2 = Chekname();
			var b3 = Chekpwd();
			var b4 = Chektelephone(); 
			var b5 =Chekroleid(); 
		  	return b1 && b2 && b3 && b4 && b5;

	     });
});
        
</script>
</head>
<body>
<div class="container-fluid">
			<div class="row">
				<div class="col-md-10 col-md-offset-3">
					<br>
					<form id="regfrom" class="form-horizontal" action="../Administrator/AddAcount.do" method="post">
					  <div class="form-group">
						<label for="input3" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-5">
						  <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
						  <div id="divname"></div>
						</div>
					  </div>	
					<div class="form-group">
				     <label for="input3" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-5">
						  <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
						<div id="divusername"></div>
						</div>
					  </div>	
					  <div class="form-group">
						<label for="input3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-5">
						  <input type="password" class="form-control" name="pwd" id="pwd" placeholder="请输入密码">
						  <div id="divpwd"></div>
						</div>
					  </div> 
		
					  <div class="form-group">
						<label for="input1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-5">
							<select class="selectpicker form-control" name="sex" data-style="btn-primary">
								<option value="男">男</option>
								<option value="女">女</option>	
							</select>
						</div>
					  </div>
					  <div class="form-group">
						<label for="input2" class="col-sm-2 control-label">角色</label>
						<div class="col-sm-5"> 
              	<c:forEach var="ro" items="${role}">
            
               <label class="checkbox-inline">  
              <input type="checkbox" name="roleid" id="roleid" value="${ro.id}" <c:if test="${ro.id==4}">checked='checked'</c:if> >${ro.rolename}
              </label>
             </c:forEach>   
                <div id="divroleid" ></div> 
						</div>
					  </div>
					    <div class="form-group">
						<label for="input2" class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-5">
						  <input type="text" class="form-control" name="telephone" id="telephone" placeholder="请输入联系电话">
						<div id="divtelephone"></div>
						</div>
					  </div>
					  <div class="form-group">
						<label for="input4" class="col-sm-2 control-label">备注</label>
						<div class="col-sm-5">
						  <textarea class="form-control"  name="remark" rows="3"></textarea>
					    <div id="divremark"></div>
					  </div>
					  <br><br>
					  
					  <div class="form-group">
						<div class="col-sm-offset-4 col-sm-5">
						  <button type="submit" class="btn btn-default">添加</button>
						</div>
					  </div>
					</form>
				</div>
</body>
</html>