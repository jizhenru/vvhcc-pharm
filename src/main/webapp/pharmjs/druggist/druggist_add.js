$(function() {

	//1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	var deviceid = $('#deviceid').val();

	//初始化Table
	oTableInit.Init = function() {
		$('#map_info_table').bootstrapTable({
			url : 'pharm/showDrugbox.do?deviceid=' + deviceid, //请求后台的URL（*）
			method : 'get', //请求方式（*）
			toolbar : '#toolbar', //工具按钮用哪个容器
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : true, //是否启用排序
			sortOrder : "asc", //排序方式
			queryParams : oTableInit.queryParams,//传递参数（*）
			sidePagination : "client", //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, //是否显示所有的列
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			columns : [

			{
				field : 'coordinateX',
				title : '药桶（柜）列号',
				align : 'center',
				width : '200',
				valign : 'middle',
			}, {
				field : 'coordinateY',
				title : '药桶（柜）行号',
				align : 'center',
				width : '200',
				valign : 'middle',
			}, {
				field : 'coordinateZ',
				title : '药柜格号',
				align : 'center',
				width : '200',
				valign : 'middle',
			}, {
				field : 'drug',
				title : '所存药品',
				align : 'center',
				width : '200',
				valign : 'middle',

			}, {
				field : 'drugnum',
				title : '现存数量',
				align : 'center',
				width : '200',
				valign : 'middle',
				sortable : true,
			}, {
				field : 'status',
				title : '工作状态',
				align : 'center',
				width : '200',
				valign : 'middle',

				formatter : operateFormatter1

			},

			{
				field : 'operate',
				title : '操作',
				align : 'center',
				width : '200',
				valign : 'middle',
				events : operateEvents2,
				formatter : operateFormatter2
			}

			],

		});

	};
	return oTableInit;

};
function operateFormatter2(value, row, index) {
	var a;

	if (row.status == 0) {
		a = '<a class="like btn btn-success" href="javascript:void(0)"  >上药</a>';
	} else if (row.status == 1) {

	}
	return a;
}

window.operateEvents2 = {
	'click .like' : function(e, value, row, index) {
		bootbox.prompt({
			size : "small",
			title : "输入上药数量",
			inputType : "number",
			buttons : {
				confirm : {
					label : '确认',
					className : 'btn-success'
				},
				cancel : {
					label : '放弃',
					className : 'btn-danger'
				}
			},
			callback : function(result) {
				if (result > 0 && result < 21) {
					$.get("pharm/findrugboxupdateId.do?", {
						num : result,
						deviceId : row.deviceId,
						coordinateX : row.coordinateX,
						coordinateY : row.coordinateY
					});
					//刷新当前页面
					window.location.reload();
				}
			}

		});

	},

};

function operateFormatter1(value, row, index) {

	var a;
	if (value == 0) {
		a = '<a href="javascript:void(0)" class="like btn btn-success"  id="start">已启用</a>';
	} else if (value == 1) {
		a = '<a href="javascript:void(0)" class="like btn btn-danger"    id="start1">已禁用</a>';

	}
	return a;
}
