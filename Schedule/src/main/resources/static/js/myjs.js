$(document).ready(function() {

	
	
	//获取领导名字
	
	showMen();
	showTable();
	showAgenda();
	getWeekTime();
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
		},
		error : function(data) {
			alert("失败");
		}
	}); 
}


var leaderList;
var time_start;
var time_end ;
var leader_name;


function getWeekTime(){
	
}

function outFile() {

}
// 后台获取到的数据

//给表填充数据
function showAgenda(name,time) {
	
	if(name==null && time==null){
		var leaderData = new Array();
		$.ajax({
			type : "get",
			url : "/schedule/name/date",
			async : true,
			dataType : "json",
			success : function(data) {
				leaderData=data;
				var td_ID = "";
				for (var i = 0; i < leaderData.length; i++) {
					var agenda = leaderData[i];
					
					if (td_ID == "td_" + agenda.dayId + "_" + agenda.level + "_"
							+ agenda.periodId + "_") {
						td_ID = "td_" + agenda.dayId + "_" + agenda.level + "_"
								+ agenda.periodId + "_";
						$("#" + td_ID + "1").append("<br/>" + agenda.content);
						$("#" + td_ID + "2").append("<br/>" + agenda.address);
						$("#" + td_ID + "3").append("<br/>" + agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").append("<br/>" + agenda.comment);
					} else {
						td_ID = "td_" + agenda.dayId + "_" + agenda.level + "_"
								+ agenda.periodId + "_";
						$("#" + td_ID + "1").html(agenda.content);
						$("#" + td_ID + "2").html(agenda.address);
						$("#" + td_ID + "3").html(agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").html(agenda.comment);
						/*$("#" +week_ID).prepend("<br/>" +agenda.time);*/
					}
				}
			},
			error : function(data) {
				alert("失败");
			}
		});
	}else{
		var leaderData = new Array();
		$.ajax({
			type : "get",
			url : "/schedule/name/date",
			async : true,
			data : {
				name:leader_name,
				currentDate : time_start,
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
				console.log(leaderData);
				var td_ID = "";
				for (var i = 0; i < leaderData.length; i++) {
					var agenda = leaderData[i];
					console.log(agenda);
					if (td_ID == "td_" + agenda.dayId + "_" + agenda.level + "_"
							+ agenda.periodId + "_") {
						td_ID = "td_" + agenda.dayId + "_" + agenda.level + "_"
								+ agenda.periodId + "_";
						$("#" + td_ID + "1").append("<br/>" + agenda.content);
						$("#" + td_ID + "2").append("<br/>" + agenda.address);
						$("#" + td_ID + "3").append("<br/>" + agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").append("<br/>" + agenda.comment);
					} else {
						td_ID = "td_" + agenda.dayId + "_" + agenda.level + "_"
								+ agenda.periodId + "_";
						console.log(td_ID);
						$("#" + td_ID + "1").html(agenda.content);
						$("#" + td_ID + "2").html(agenda.address);
						$("#" + td_ID + "3").html(agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").html(agenda.comment);
						/*$("#" +week_ID).prepend("<br/>" +agenda.time);*/
					}
					console.log(td_ID);
				}
			},
			error : function(data) {
				alert("失败");
			}
		});
	}
	
	
}

//制作表格
function showTable1(name,time) {
	
	if(name==null && time == null){
		var leadersArray = new Array();
		$.ajax({
			type : "get",
			url : "leader",
			async : false,
			dataType : "json",
			success : function(data) {
				
				leaderList = data;
				for(var i=0;i<leaderList.length;i++){
					leadersArray.push(leaderList[i].leaderName);
				};
				
			},
			error : function(data) {
				alert("失败");
			}
		});
		
		var weeksArray = new Array();
		var period = new Array();
		
		$.ajax({
			type : "get",
			url : "dateall",
			async : true,
			dataType : "json",
			success : function(data) {
				
				listArray = data;
				time_start = listArray.startDate[0];
				time_end = listArray.startDate[1]
				$("#gettime").html(time_start+" ~ "+time_end);
				weeksArray = listArray.listWeek;
				
				period = listArray.period
				var tr_html = "";
				
				for (var a = 0; a < weeksArray.length; a++) {
					
					
					tr_html += "<tr><th height=54px rowspan=\"" + leadersArray.length * period.length
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
			},
			error : function(data) {
				alert("失败");
			}
		});
	}else{
		var leadersArray = new Array();
		
		$.ajax({
			type : "get",
			url : "leader",
			async : false,
			data:{name:name},
			dataType : "json",
			success : function(data) {
				
				leaderList = data;
				for(var i=0;i<leaderList.length;i++){
					leadersArray.push(leaderList[i].leaderName);
				};
				
			},
			error : function(data) {
				alert("失败");
			}
		});
		
		var weeksArray = new Array();
		var period = new Array();
		
		$.ajax({
			type : "get",
			url : "dateall",
			async : true,
			data:{name:name},
			dataType : "json",
			success : function(data) {
				
				listArray = data;
				time_start = listArray.startDate[0];
				time_end = listArray.startDate[1]
				$("#gettime").html(time_start+" ~ "+time_end);
				weeksArray = listArray.listWeek;
				period = listArray.period
				var tr_html = "";
				for (var a = 0; a < weeksArray.length; a++) {
					
					tr_html += "<tr><th height=54px rowspan=\"" + leadersArray.length * period.length
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
							tr_html += "<td id=\"td_" + (a + 2) + "_" + (b + 2) + "_"
									+ (c + 2) + "_2\"></td>";
							tr_html += "<td id=\"td_" + (a + 3) + "_" + (b + 3) + "_"
									+ (c + 3) + "_3\"></td>";
							tr_html += "<td id=\"td_" + (a + 4) + "_" + (b + 4) + "_"
									+ (c + 4) + "_4\"></td>";
							tr_html += "</tr>"
						}
					}
				}
				$("#tb_test").html(tr_html);
			},
			error : function(data) {
				alert("失败");
			}
		});
	}
	
}
function showTable(name,time) {
	var weeksArray = new Array();
	var period = new Array();
	var leadersArray = new Array();
	if(name==null && time == null){
		$.ajax({
			type : "get",
			url : "leader",
			async : false,
			dataType : "json",
			success : function(data) {
				
				leaderList = data;
				for(var i=0;i<leaderList.length;i++){
					leadersArray.push(leaderList[i].leaderName);
				};
				
			},
			error : function(data) {
				alert("失败");
			}
		});
		$.ajax({
			type : "get",
			url : "dateall",
			async : true,
			dataType : "json",
			success : function(data) {
				
				listArray = data;
				time_start = listArray.startDate[0];
				time_end = listArray.startDate[1]
				$("#gettime").html(time_start+" ~ "+time_end);
				weeksArray = listArray.listWeek;
				period = listArray.period
			},
			error : function(data) {
				alert("失败");
			}
		});
	}else{
		$.ajax({
			type : "get",
			url : "leader",
			async : false,
			data:{name:name},
			dataType : "json",
			success : function(data) {
				
				leaderList = data;
				for(var i=0;i<leaderList.length;i++){
					leadersArray.push(leaderList[i].leaderName);
				};
				
			},
			error : function(data) {
				alert("失败");
			}
		});	
		$.ajax({
			type : "get",
			url : "dateall",
			async : true,
			data:{name:name},
			dataType : "json",
			success : function(data) {
				
				listArray = data;
				time_start = listArray.startDate[0];
				time_end = listArray.startDate[1]
				$("#gettime").html(time_start+" ~ "+time_end);
				weeksArray = listArray.listWeek;
				period = listArray.period
			},
			error : function(data) {
				alert("失败");
			}
		});
	}
	generateTableFrame(leadersArray,weeksArray,period);
}

function generateTableFrame(leadersArray,weeksArray,period){
	    var tr_html = "";
	    for (var a = 0; a < weeksArray.length; a++) {
	        tr_html += "<tr><th rowspan=\"" + leadersArray.length * period.length + "\" style=\"text-align:center;\">" + weeksArray[a] + "</th>";
	        for (var b = 0; b < leadersArray.length; b++) {
	            if (b == 0) {
	                //alert(leadersArray.length + " " + weeksArray.length + " " + period.length);
	                tr_html += "<th rowspan=\""+period.length+"\">" + leadersArray[b] + "</th>";
	            } else {
	                tr_html += "<tr><th rowspan=\"" + period.length + "\">" + leadersArray[b] + "</th>";
	            }
	            for (var c = 0; c < period.length; c++) {
	                if (c >= 2) tr_html += "<tr>";
	                tr_html += "<td>" + period[c] + "</td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_" + (c + 1) + "_1\"></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_" + (c + 1) + "_2\"></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_" + (c + 1) + "_3\"></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + (b + 1) + "_" + (c + 1) + "_4\"></td>";
	                tr_html+="</tr>"
	            }
	        }
	    }
	    $("#tb_test").html(tr_html);
	}
}
function hideAgenda() {
	$("#agendaShowModel").modal("hide");
}
//点击左边
function clicleader(name) {
	$("#leaderName").html(name + "的工作日程");
	leader_name = name;
	if (leader_name=="所有领导") {
		leader_name="";
		showTable();
		showAgenda();
	}
	showTable(leader_name,time_start);
	showAgenda(leader_name,time_start);
	
	
}
//点击左边树形菜单

//导入文件
function inputFile() {
	var file = $("#filename").val();
	if(file == '' || file == null) {
		alert("请选择所要上传的文件！");
	} else {
		var index = file.lastIndexOf(".");
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