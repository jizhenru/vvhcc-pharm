<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VVCS 用户功能</title>

<!-- Bootstrap -->


<link rel="shortcut icon" href="../img/logo_ico.ico" />
<script src="../pharmjs/jquery-1.11.1.min.js"></script>
<script src="../pharmjs/bootstrap.min.js"></script>
<link href="../pharmcss/bootstrap.min.css" rel="stylesheet" />
<style>

	.background_color{background:#d9d7d6}

	.width{width:200px}
	
	.height(padding:10px)
	
	.nav-header.collapsed > span.glyphicon-chevron-toggle:before {      content: "\e114";  }   
	.nav-header > span.glyphicon-chevron-toggle:after {      content: "\e113";  }
	.nav-header.collapsed > span.glyphicon-chevron-toggle:after {      content: "\e114";  } 

	.secondmenu a {
		font-size: 12px;
		color: #4A515B;
		text-align: center;
	}

	.secondmenu li.active {
		background-color: #eee;
		border-color: #428bca;
	}
		
	.panel {
  
    margin-bottom: 0;
	}
	#login_div{
	padding-top:20px;

}
	
</style>


</head>
<body>
	<%
			Cookie[] cookies=request.getCookies();
					String name = "";
			if(cookies != null){
			for(Cookie cookie:cookies){
			if(cookie.getName().equals("username"))
				name = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			}} %>
	<div class="container-fluid">

		<div class="row header" style="padding-top: 0px;">
			<div class="col-md-4">
				<span> <img src="../img/logo.png"
					style="width: 230px; height: 60px;">
				</span>
			</div>
	          	<div  class="col-md-2 col-md-offset-6" id="login_div" >
				 <a class=" dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					 欢迎：<%=name %>
					<span class="caret"></span>
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">个人信息</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="http://www.vvhcc.com/user/logout">退出</a></li>				
					
				  </ul>	
			</div>

		</div>

		<div class="row">
			<div class="col-md-2  width "  style="padding-right:0px" >
								
				<ul class="nav nav-pills nav-stacked " id="accordion">
					<li role="presentation" class="active"><a href="#">用户</a></li>
					
					<li class="panel ">
						<a href="#systemSetting1" class="nav-header collapsed" data-toggle="collapse" data-parent="#accordion">
						<i class="glyphicon glyphicon-cog"></i>
						处方列表
						   <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
						</a>
						<ul id="systemSetting1" class="nav nav-list panel-collapse collapse  secondmenu" style="height: 0px;">
							<li class="active"><a  href="unfinished_list.html" target="mainFrame"><i class="glyphicon glyphicon-edit"></i>&nbsp;未领药处方</a></li>
							<li><a href="history_list.html" target="mainFrame" > <i class="glyphicon glyphicon-eye-open"></i>&nbsp;历史处方</a></li>
						</ul>
					</li>
			
				</ul>

			</div>
			
			
				
			<div class="col-md-10 " style="padding-left:0px">
				<div class="background_color  " style="padding:10px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.vvhcc.com/" style="float: right"> 返回首页</a></div>
      	 		<!-- 嵌套网页开始 -->         
                <iframe src=""  frameborder="0"   name="mainFrame" width="100%" height="720"  ></iframe>
                <!-- 嵌套网页结束scrolling="no"  -->   
			</div>
		</div>
		
		<script src="../pharmjs/footer.js"></script>
	</div>
	
</body>
</html>