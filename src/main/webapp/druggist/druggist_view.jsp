<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VVCS 后台管理</title>

<link rel="shortcut icon" href="../img/logo_ico.ico" />
<script src="../pharmjs/jquery-1.11.1.min.js"></script>
<script src="../pharmjs/bootstrap.min.js"></script>
<link href="../pharmcss/bootstrap.min.css" rel="stylesheet" />
<style>
.background_color {
	background: #d9d7d6
}

.width {
	width: 200px
}

.height (padding: 10px)
	
	.nav-header.collapsed>span.glyphicon-chevron-toggle:before {
	content: "\e114";
}

.nav-header>span.glyphicon-chevron-toggle:after {
	content: "\e113";
}

.nav-header.collapsed>span.glyphicon-chevron-toggle:after {
	content: "\e114";
}

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
 <script>
	$(function (){
		 $.ajax({                 
             url: '../druggist/findDevice1.do',
             async: false,
             dataType:"json",
             type: "get",
             data: {},
             success: function (data) {
        	  	 var html="";
            	 var macList = eval(data);
            	 for(var i=0,mLen=macList.length;i<mLen;i++) {
          
            	html+= '<li> <a  href="../druggist/findDeviceId1.do?id='+macList[i].id+' "     target="mainFrame">'+ macList[i].name +
				 '</a></li>';	 
	
            	 }
            	 
            		$('#systemSetting1_1').append(html);
             }
             
		 });
	});
</script> 



</head>
<body>
		

		<%
			Cookie[] cookies=request.getCookies();
					String name = "";
			if(cookies != null){
			for(Cookie cookie:cookies){
			if(cookie.getName().equals("name"))
				name = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			}} %>
			
	      	<div class="row " >
			<div class="col-md-4  ">
				<img src="../img/logo.png" id="logo_img" style="width: 230px; height: 60px; ">
		     	</div>

			<div  class="col-md-2 col-md-offset-6" id="login_div" >
				 <a class=" dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					 欢迎：<%=name %>
					<span class="caret"></span>
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="findselectusername.do?username=<%=name%>">个人信息</a></li>
				
					<li role="separator" class="divider"></li>
					<li><a href="http://www.vvhcc.com/user/logout">退出</a></li>				
					
				  </ul>	
			</div>


		</div>
	

		<div class="row">
			<div class="col-md-2  width " style="padding-right: 0px">

				<ul class="nav nav-pills nav-stacked "  id="accordion">
					<li role="presentation" class="active"><a href="#" >药剂师</a></li>

					<li class="panel "><a href="#systemSetting1"
						class="nav-header collapsed" data-toggle="collapse"
						data-parent="#accordion"> <i class="glyphicon glyphicon-cog"></i>
							处方出药 <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
					</a>
						<ul id="systemSetting1"
							class="nav nav-list panel-collapse collapse  secondmenu"
							style="height: 0px;">
							<li class="active"><a href="search_medicinelist.html"
								target="mainFrame"><i class="glyphicon glyphicon-edit"></i>&nbsp;查询处方</a></li>
							<li><a href="medicinelist.html" target="mainFrame"> <i
									class="glyphicon glyphicon-eye-open"></i>&nbsp;处方列表
							</a></li>
						</ul></li>

					<li class="panel "><a href="#systemSetting2"
						class="nav-header collapsed" data-toggle="collapse"
						data-parent="#accordion"> <i
							class="glyphicon glyphicon-th-list"></i> 上药管理 <span
							class="pull-right glyphicon glyphicon-chevron-toggle"></span>
					</a>
						<ul id="systemSetting2"
							class="nav nav-list panel-collapse collapse  secondmenu"
							style="height: 0px;">

							<!-- <li><a href="uptable.html" target="mainFrame" > <i class="glyphicon glyphicon-eye-open"></i>&nbsp;设备药品列表</a></li> -->
							<li><a href="#systemSetting1_1" class="nav-header collapsed"
								data-toggle="collapse" data-parent="#systemSetting1"> <i
									class="glyphicon glyphicon-eye-open"></i> 设备列表 <span
									class="pull-right glyphicon glyphicon-chevron-toggle"></span>
							</a>
								<ul id="systemSetting1_1"
									class="nav nav-list panel-collapse collapse  secondmenu"
									style="height: 0px;">

								</ul></li>
						</ul></li>



				</ul>

			</div>



			<div class="col-md-10 " style="padding-left: 0px">



				<div class="background_color  " style="padding: 10px">
					管理控制台 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.vvhcc.com/"
						style="float: right"> 返回首页</a>
				</div>
				<!-- 嵌套网页开始 -->
				<iframe src="" frameborder="0" name="mainFrame" width="100%"
					height="720"  scrolling="auto"></iframe>
				<!-- 嵌套网页结束scrolling="no"  -->


			</div>
		</div>
		<script src="../pharmjs/footer.js"></script>

	</div>


</body>
</html>