$(function () {

    // 1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

 $('#sub').click(function(){
        var deviceid1 = $('#deviceid1').val();
        var coordinateX = $('#coordinateX').val();
        var coordinateY = $('#coordinateY').val();
        var coordinateZ = $('#coordinateZ').val();
        var drug = $('#drug').val();
        if(deviceid1!="" && coordinateX!="" && coordinateY!="" &&　coordinateZ!=""　&& drug!="" ){
   	 $.post('../pharm/druggist/Addvvcsdrugbox.do?',{deviceid1:deviceid1,coordinateX:coordinateX,coordinateY:coordinateY,coordinateZ:coordinateZ,drug:drug },function(){
     	window.location.reload();
    


    });
        }        
  });
 
  
  
});

function update(id){
location.href="../druggist/findDeviceidID.do?id="+id;

}

var TableInit = function () {
    var oTableInit = new Object();
    var deviceid = $('#deviceid').val();
/* var id = $('#id').val(); */
    // 初始化Table
    oTableInit.Init = function () {
        $('#map_info_table').bootstrapTable({
            url: '../pharm/druggist/showDrugbox1.do?deviceid='+deviceid,         // 请求后台的URL（*）
            method: 'get',                      // 请求方式（*）
            toolbar: '#toolbar',                // 工具按钮用哪个容器
            striped: true,                      // 是否显示行间隔色
            cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   // 是否显示分页（*）
            sortable: false,                     // 是否启用排序
            sortOrder: "asc",                   // 排序方式
            queryParams: oTableInit.queryParams,// 传递参数（*）
            sidePagination: "client",           // 分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       // 初始化加载第一页，默认第一页
            pageSize: 10,                       // 每页的记录行数（*）
            pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
            search: false,                       // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  // 是否显示所有的列
            showRefresh: true,                  // 是否显示刷新按钮
            minimumCountColumns: 2,             // 最少允许的列数
            clickToSelect: true,                // 是否启用点击选中行
                             // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     // 每一行的唯一标识，一般为主键列
            showToggle:false,                    // 是否显示详细视图和列表视图的切换按钮
            cardView: false,                    // 是否显示详细视图
            detailView: false,                   // 是否显示父子表
            columns: [

			{
				field: 'coordinateX',
				title: '药桶（柜）列号',
				align: 'center',
				width: '200',
				valign: 'middle',
			}, {
				field: 'coordinateY',
				title: '药桶（柜）行号',
				align: 'center',
				width: '200',
				valign: 'middle',
			}, {
				field: 'coordinateZ',
				title: '药柜格号',
				align: 'center',
				width: '200',
				valign: 'middle',
			},
			{
				field: 'drug',
				title: '所存药品',
				align: 'center',
				width: '200',
				valign: 'middle',
				editable: {
	                    type: 'text',
	                    title: '所存药品',     
	                }
				
			},
			{
				field: 'drugnum',
				title: '现存数量',
				align: 'center',
				width: '200',
				valign: 'middle',
			},
			{
				field: 'status',
				title: '工作状态',
				align: 'center',
				width: '200',
				valign: 'middle',
			
				formatter:operateFormatter1
		
			 },
            
			{
				field: 'operate',
				title: '操作',
				align: 'center',
				width: '200',
				valign: 'middle',
				events:operateEvents2,
				formatter:operateFormatter2
			 }

            
            ],
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "../pharm/druggist/findupdatedrug.do",
                    data: row,
                    dataType: 'JSON',
                    success: function (data, status) {
                        if (status == "success") {
                            alert('提交数据成功');
                        }
             },
                    error: function () {
                        alert('编辑失败');
                    },
                    complete: function () {


                    }


                });
            }
            
        });

       
		 
		 
    };
    return oTableInit;
    
};
function operateFormatter2(value, row, index) {
	 var a;
	 
	 if(row.status==0){
	     a ='<a class="like" href="javascript:void(0)"   >上药</a>'+'&nbsp;&nbsp;'+
	     '<a class="like1 " href="javascript:void(0)"   >出药</a>'+'&nbsp;&nbsp;'+
	     '<a class="like2 " href="javascript:void(0)"   >删除</a>';
    }
   else if(row.status==1){	 
    a = '<a class="like2 " href="javascript:void(0)"   >删除</a>';
   }
    return a;
}

window.operateEvents2 = {
		'click .like': function (e, value, row, index) {
        	bootbox.prompt({ 
        		  size: "small",
        		  title: "输入上药数量", 
        		  inputType: "number",
        		   buttons: {
          	        confirm: {
          	            label: '确认',
          	            className: 'btn-success'
          	        },
          	        cancel: {
          	            label: '放弃',
          	            className: 'btn-danger'
          	        }
        		  },
        		  callback: function(result){ 
        			  if(result>0 && result<20){
        				  $.get("../pharm/druggist/findrugboxupdateId.do?", {num: result,deviceId:row.deviceId,coordinateX:row.coordinateX,coordinateY:row.coordinateY });
        				  // 刷新当前页面
        				  window.location.reload(); 
        			  }
        		  }
        		  
        		}) ;
            
        },
		'click .like1': function (e, value, row, index) {
	         // alert('You click like action, row: ' + JSON.stringify(row));
	        	bootbox.prompt({ 
	        		  size: "small",
	        		  title: "输入上药数量", 
	        		  inputType: "number",
	        		   buttons: {
	          	        confirm: {
	          	            label: '确认',
	          	            className: 'btn-success'
	          	        },
	          	        cancel: {
	          	            label: '放弃',
	          	            className: 'btn-danger'
	          	        }
	        		  },
	        		  callback: function(result){ 
	          
	        			  if(result>0 && result<20){
	        				  $.get("../pharm/druggist/findrugboxupdateId2.do?", {num: result,deviceId:row.deviceId,coordinateX:row.coordinateX,coordinateY:row.coordinateY });
	        				  // 刷新当前页面
	        				  window.location.reload(); 
	        			  }
	        		  }
	        		  
	        		}) ;
	            
	        },
	        'click .like2': function (e, value, row, index) {
		        	bootbox.confirm({ 
		        		  size: "small",
		        		  message: "确认删除此药筒？", 
		        		  buttons: {
		          	        confirm: {
		          	            label: '确认',
		          	            className: 'btn-success'
		          	        },
		          	        cancel: {
		          	            label: '放弃',
		          	            className: 'btn-danger'
		          	        }
		        		  },
		        		  callback: function(result){ 
		        			  if(result==true)
		        				  {
		        			  $.get("../pharm/druggist/deletevvcsdrugbox.do?", {id:row.id });
	        				  // 刷新当前页面
	        				  window.location.reload(); 
		        				  }
		        		  }
		        		  
		        		}) ;
		            
		        }
	
    };



function operateFormatter1(value, row, index) {

	var a;
	 if(value==0){
	  a ='<a href="javascript:void(0)" class="like btn btn-success"  id="start">已启用</a>';	 
	 }else if(value==1){ 
	 a='<a href="javascript:void(0)" class="like btn btn-danger"    id="start1">已禁用</a>';
	 
	 }		
	  return a;
	}

