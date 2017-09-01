$(document).ready(function() {

	
	
	//获取领导名字
	
	showMen();
	showTable();
	showAgenda();
	$("li,th,td").addClass("text-center");
	
	$("#afterweek").on("click",function(){
		$('#afterweek').removeClass('btn-default');
		$('#afterweek').addClass('btn-primary');
		
		$('#nowweek').removeClass('btn-primary');
		$('#nowweek').addClass('btn-default');
		
		$('#nextweek').removeClass('btn-primary');
		$('#nextweek').addClass('btn-default');
		
		$.ajax({
			type : "post",
			url : "",
			async : true,
			data : {
				time_start : time_start,
				time_end:time_end,
				leader_name:leader_name
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
			},
			error : function(data) {
				alert("失败");
			}
		});
		
	});
	
	$("#nowweek").on("click",function(){
		$('#afterweek').removeClass('btn-primary');
		$('#afterweek').addClass('btn-default');
		
		$('#nowweek').removeClass('btn-default');
		$('#nowweek').addClass('btn-primary');
		
		$('#nextweek').removeClass('btn-primary');
		$('#nextweek').addClass('btn-default');
		
		$.ajax({
			type : "post",
			url : "",
			async : true,
			data : {
				time_start : time_start,
				time_end:time_end,
				leader_name:leader_name
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
			},
			error : function(data) {
				alert("失败");
			}
		});
	});
	
	$("#nextweek").on("click",function(){
		$('#afterweek').removeClass('btn-primary');
		$('#afterweek').addClass('btn-default');
		
		$('#nowweek').removeClass('btn-primary');
		$('#nowweek').addClass('btn-default');
		
		$('#nextweek').removeClass('btn-default');
		$('#nextweek').addClass('btn-primary');
		
		$.ajax({
			type : "post",
			url : "",
			async : true,
			data : {
				time_start : time_start,
				time_end:time_end,
				leader_name:leader_name
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
			},
			error : function(data) {
				alert("失败");
			}
		});
	});
	
	$("#gettime").html(time_start+" ~ "+time_end);
});

function showMen(){
	$.ajax({
		type : "get",
		url : "leader",
		async : true,
		dataType : "json",
		success : function(data) {
			
			leaderList = data;
			var ul ="<ul class='list-group'>"
			for(var i=0;i<leaderList.length;i++){
				ul +="<li class='list-group-item text-center'><a href='#' onclick='clicleader(\""+leaderList[i].leaderName+"\")'>"+leaderList[i].leaderName+"</a></li>"
			};
				ul+="<li class='list-group-item text-center'><a href='#' onclick='clicleader(\"所有领导\")'>所有领导</a></li></ul>";
				$("#showMen").html(ul);
			//console.log(leadersArray);
		},
		error : function(data) {
			alert("失败");
		}
	}); 
}

var listArray =[
	{startTime:["2017-08-28","2017-09-02"],
		week:["2017-08-28<br/>星期一","2017-08-29<br/>星期二","2017-08-30<br/>星期三","2017-08-31<br/>星期四",
		"2017-09-01<br/>星期五","2017-09-02<br/>星期六","2017-09-03<br/>星期日"],
		leader:["秦如培","马长青","孙登峰","宗文","曾瑜"],
		period:["上午","下午","晚上"]}
]
var leaderList;
var time_start = listArray[0].startTime[0];
var time_end = listArray[0].startTime[1];
var leader_name;
var leadersArray = new Array();



function outFile() {

}
// 后台获取到的数据
var leaderData = [ {
	nameId : '2',
	name : '李四',
	datatitle : '外访',
	time: '2017-08-28',
	week : '星期四',
	dayId : '4',
	timenow : '下午',
	periodId : '2',
	place : "金阳高新区",
	joinpersonnel : "赵四，王麻子",
	remarks : '随时电话联系'
}, {
	nameId : '2',
	name : '李四',
	datatitle : '开会',
	time: '2017-08-28',
	week : '星期四',
	dayId : '4',
	timenow : '下午',
	periodId : '2',
	place : "金阳高新区",
	joinpersonnel : "赵四，王麻子",
	remarks : '随时电话联系'
}, {
	nameId : '1',
	name : '张三',
	datatitle : '开会',
	time: '2017-08-28',
	week : '星期一',
	dayId : '1',
	timenow : '晚上午',
	periodId : '2',
	place : "金阳高新区",
	joinpersonnel : "赵四，王麻子",
	remarks : '随时电话联系'
}, {
	nameId : '4',
	name : '小辉',
	datatitle : '开会',
	time: '2017-08-28',
	week : '星期四',
	dayId : '4',
	timenow : '上午',
	periodId : '1',
	place : "金阳高新区",
	joinpersonnel : "赵四，王麻子",
	remarks : '随时电话联系'
}, {
	nameId : '5',
	name : '云鹏',
	datatitle : '开会',
	time: '2017-08-28',
	week : '星期一',
	dayId : '1',
	timenow : '晚上',
	periodId : '3',
	place : "金阳高新区",
	joinpersonnel : "赵四，王麻子",
	remarks : '随时电话联系'
} ]

//给表哥填充数据
function showAgenda() {
	
	var td_ID = "";
	for (var i = 0; i < leaderData.length; i++) {
		var agenda = leaderData[i];
		if (td_ID == "td_" + agenda.dayId + "_" + agenda.nameId + "_"
				+ agenda.periodId + "_") {
			td_ID = "td_" + agenda.dayId + "_" + agenda.nameId + "_"
					+ agenda.periodId + "_";
			$("#" + td_ID + "1").append("<br/>" + agenda.datatitle);
			$("#" + td_ID + "2").append("<br/>" + agenda.place);
			$("#" + td_ID + "3").append("<br/>" + agenda.joinpersonnel);
			$("#" + td_ID + "4").append("<br/>" + agenda.remarks);
		} else {
			td_ID = "td_" + agenda.dayId + "_" + agenda.nameId + "_"
					+ agenda.periodId + "_";
			week_ID="week_"+agenda.dayId;
			$("#" + td_ID + "1").html(agenda.datatitle);
			$("#" + td_ID + "2").html(agenda.place);
			$("#" + td_ID + "3").html(agenda.joinpersonnel);
			$("#" + td_ID + "4").html(agenda.remarks);
			/*$("#" +week_ID).prepend("<br/>" +agenda.time);*/
		}
	}
}

//制作表格
function showTable() {
	$.ajax({
		type : "get",
		url : "leader",
		async : false,
		dataType : "json",
		success : function(data) {
			//console.log(data.length);
			leaderList = data;
			for(var i=0;i<leaderList.length;i++){
				leadersArray.push(leaderList[i].leaderName);
			};
			//console.log(leadersArray);
		},
		error : function(data) {
			alert("失败");
		}
	});
	
	// 星期
	//var weeksArray = new Array("2017-08-28<br/>周一", "周二", "周三", "周四", "周五", "周六", "周日");
	var weeksArray = listArray[0].week;

	// 时间段
	//var period = new Array("上午", "下午", "晚上");
	var period = listArray[0].period;
	var tr_html = "";

	for (var a = 0; a < weeksArray.length; a++) {
		var b=a+1;
		tr_html += "<tr><th id=week_"+b +" height=54px rowspan=\"" + leadersArray.length * period.length
				+ "\" style=\"text-align:center;\">" + weeksArray[a] + "</th>";
		for (var b = 0; b < leadersArray.length; b++) {
			if (b == 0) {
				
				tr_html += "<th rowspan=\"" + period.length + "\">"
						+ leadersArray[b] + "</th>";
			} else {
				tr_html += "<tr><th rowspan=\"" + period.length + "\">"
						+ leadersArray[b] + "</th>";
			}
			for (var c = 0; c < period.length; c++) {
				if (c >= 2)
					tr_html += "<tr>";
				tr_html += "<td>" + period[c] + "</td>";
				tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_"
						+ (c + 1) + "_1\"></td>";
				tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_"
						+ (c + 1) + "_2\"></td>";
				tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_"
						+ (c + 1) + "_3\"></td>";
				tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_"
						+ (c + 1) + "_4\"></td>";
				tr_html += "</tr>"
			}
		}
	}
	$("#tb_test").html(tr_html);
}

function hideAgenda() {
	$("#agendaShowModel").modal("hide");
}
//点击左边
function clicleader(name) {
	$("#leaderName").html(name + "的工作日程");
	leader_name = name;
	$.ajax({
		type : "post",
		url : "",
		async : true,
		data : {
			time_start : time_start,
			time_end:time_end,
			leader_name:leader_name
		},
		dataType : "json",
		success : function(data) {
			leaderData=data;
		},
		error : function(data) {
			alert("失败");
		}
	});
}
//点击左边树形菜单

//导入文件
function inputFile() {
	var file = $("#filename").val();
	//console.log(file);
	if(file == '' || file == null) {
		alert("请选择所要上传的文件！");
	} else {
		var index = file.lastIndexOf(".");
		console.log(index);
		if(index < 0) {
			alert("上传的文件格式不正确，请上传Excel文件");
		} else {
			var ext = file.substring(index + 1, file.length);
			if(ext == "xls" || ext == "xlsx") {
				$.ajax({
					type: "post",
					url: "",
					async: true,
					data: {file:file},
					dataType: "",
					success: function(data) {
						alter("上传成功");
					},
					error: function(data) {
						alert("上传失败");
					}
				});
				$("#filename").text("")

			} else {
				alert("请上传Excel文件");
			}
		}
	}

}

var leader = "所有领导";
var startDate = "2017-8-28";
var endDate = "2017-9-3";
var weekName = "周一至周日";
var savingAgendaExcelName = "";
function showAgendaDownloadModal() {
    savingAgendaExcelName = "";
    $("#agendaInfo").html("<p>无</p>");
    savingAgendaExcelName = leader + "_" + startDate + "至" + endDate + "(周一至周日)的日程内容.xls";
    $("#agendaInfo").html("<p><i class=\"icon icon-file-excel\"></i>" + savingAgendaExcelName + "</p>");
    $("#agendaDownlaodModal").modal('show');
}

function outputTableToExcel(tableid) {//整个表格拷贝到EXCEL中 
    $("#agendaInfo").html("下载中...");
    $("#" + tableid).table2excel({
        exclude: ".noExl",
        name: "Excel Document Name",
        //filename: "myFileName" + new Date().toISOString().replace(/[\-\:\.]/g, ""),
        filename: savingAgendaExcelName,
        fileext: ".xls",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
    });
    $("#agendaDownlaodModal").modal('hide');
}