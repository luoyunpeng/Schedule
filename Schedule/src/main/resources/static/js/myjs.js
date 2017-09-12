$(document).ready(function() {

	
	
	//获取领导名字
	
	showMen();
	getLeaderInfo();
	getWeekTime();
	
	
	$("#afterweek").on("click",function(){
		$('#afterweek').removeClass('btn-default');
		$('#afterweek').addClass('btn-primary');
		
		$('#nowweek').removeClass('btn-primary');
		$('#nowweek').addClass('btn-default');
		
		$('#nextweek').removeClass('btn-primary');
		$('#nextweek').addClass('btn-default');
		getLeaderInfo1(leader_name,time_start,"/dateall/last","/schedule/name/date/last");
		
		//alert($("#afterweek").disabled)
	});
	
	$("#nowweek").on("click",function(){
		$('#afterweek').removeClass('btn-primary');
		$('#afterweek').addClass('btn-default');
		
		$('#nowweek').removeClass('btn-default');
		$('#nowweek').addClass('btn-primary');
		
		$('#nextweek').removeClass('btn-primary');
		$('#nextweek').addClass('btn-default');
		
		getLeaderInfo(leader_name,null);
	});
	
	$("#nextweek").on("click",function(){
		$('#afterweek').removeClass('btn-primary');
		$('#afterweek').addClass('btn-default');
		
		$('#nowweek').removeClass('btn-primary');
		$('#nowweek').addClass('btn-default');
		
		$('#nextweek').removeClass('btn-default');
		$('#nextweek').addClass('btn-primary');
		
		getLeaderInfo1(leader_name,time_start,"/dateall/next","/schedule/name/date/next");
	});
	
	$("li,th,td").addClass("text-center");
});

function showMen(){
	$.ajax({
		type : "get",
		url : "leader",
		async : true,
		dataType : "json",
		success : function(data) {
			
			
			leaderList = data;
			var ul ="<ul class='list-group'><li class='list-group-item text-center'><a href='#' onclick='clicleader(\"所有领导\")'>所有领导</a></li>";
			for(var i=0;i<leaderList.length;i++){
				ul +="<li class='list-group-item text-center'><a href='#' onclick='clicleader(\""+leaderList[i].leaderName+"\")'>"+leaderList[i].leaderName+"</a></li>"
			};
				ul+="</ul>";
				$("#showMen").html(ul);
		},
		error : function(data) {
			
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
function showAgenda1(leader_name,time,dateUrl) {
	if(name==null && time!=null){
		var leaderData = new Array();
		$.ajax({
			type : "get",
			url : dateUrl,
			async : true,
			data:{currentDate:time},
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
					//$("#sss")
					enableAllLinkandBtn();
			},
			error : function(data) {
				
			}
		});
	}else{
		var leaderData = new Array();
		$.ajax({
			type : "get",
			url : dateUrl,
			async : true,
			data : {
				name:leader_name,
				currentDate : time
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
				//console.log(leaderData);
				var td_ID = "";
				for (var i = 0; i < leaderData.length; i++) {
					var agenda = leaderData[i];
					//console.log(agenda);
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
						//console.log(td_ID);
						$("#" + td_ID + "1").html(agenda.content);
						$("#" + td_ID + "2").html(agenda.address);
						$("#" + td_ID + "3").html(agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").html(agenda.comment);
						/*$("#" +week_ID).prepend("<br/>" +agenda.time);*/
					}
					//console.log(td_ID);
				}
				enableAllLinkandBtn();
			},
			error : function(data) {
				
			}
		});
	}
	$("li,th").addClass("text-center");
}

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
				enableAllLinkandBtn();
			},
			error : function(data) {
				
			}
		});
	}else{
		var leaderData = new Array();
		$.ajax({
			type : "get",
			url : "/schedule/name/date",
			async : true,
			data : {
				name:name,
				currentDate : time,
			},
			dataType : "json",
			success : function(data) {
				leaderData=data;
				//console.log(leaderData);
				var td_ID = "";
				for (var i = 0; i < leaderData.length; i++) {
					var agenda = leaderData[i];
					//console.log(agenda);
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
						//console.log(td_ID);
						$("#" + td_ID + "1").html(agenda.content);
						$("#" + td_ID + "2").html(agenda.address);
						$("#" + td_ID + "3").html(agenda.relatedPeopleAndDep);
						$("#" + td_ID + "4").html(agenda.comment);
						/*$("#" +week_ID).prepend("<br/>" +agenda.time);*/
					}
					//console.log(td_ID);
				}
				enableAllLinkandBtn();
			},
			error : function(data) {
				
			}
		});
	}
	$("li,th").addClass("text-center");
}

//制作表格
function getLeaderInfo1(name,time,url,dateUrl){
	disableAllLinkandBtn();
	var leadersArray = new Array();
	if(name==null && time != null){
		$.ajax({
			type : "get",
			url : "leader",
			async : false,
			dataType : "json",
			success : function(data) {
				
				leaderList = data;
				for(var i=0;i<leaderList.length;i++){
					leadersArray.push(leaderList[i]);
					getDateInfo1(name,leadersArray,time,url,dateUrl);
				};
			},
			error : function(data) {
				
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
					leadersArray.push(leaderList[i]);
					getDateInfo1(name,leadersArray,time,url,dateUrl);
				};
				
			},
			error : function(data) {
				
			}
		});	
	}
	//console.log(weeksArray);
	//generateTableFrame(leadersArray,weeksArray,period);
}

function getDateInfo1(leader_name,leadersArray,time,url,dateUrl){
	var weeksArray = new Array();
	var period = new Array();
	$.ajax({
		type : "get",
		url : url,
		async : true,
		data:{currentDate:time},
		dataType : "json",
		success : function(data) {
			listArray = data;
			time_start = listArray.startDate[0];
			time_end = listArray.startDate[1]
			$("#gettime").html(time_start+" ~ "+time_end);
			weeksArray = listArray.listWeek;
			//console.log(listArray.listWeek);
			period = listArray.period;
			generateTableFrame(leadersArray,weeksArray,period);
			showAgenda1(leader_name,time,dateUrl);
		},
		error : function(data) {
			
		}
	});
}

//制作表格
function getLeaderInfo(name,time){
	disableAllLinkandBtn();
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
					leadersArray.push(leaderList[i]);
					getDateInfo(name,leadersArray);
				};
				
			},
			error : function(data) {
				
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
					leadersArray.push(leaderList[i]);
					getDateInfo(name,leadersArray);
				};
				
			},
			error : function(data) {
				
			}
		});	
		
	}
}

//获取时间信息构建表格
function getDateInfo(name,leadersArray){
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
			//console.log(listArray.listWeek);
			period = listArray.period;
			generateTableFrame(leadersArray,weeksArray,period);
			showAgenda(name,null);
		},
		error : function(data) {
			
		}
	});
}


function generateTableFrame(leadersArray,weeksArray,period){

	    var tr_html = "";
	    for (var a = 0; a < weeksArray.length; a++) {
	    	if(a==0 ||leadersArray.length==1 )
	    		tr_html += "<tr><th rowspan=\"" + leadersArray.length * period.length + "\" style=\"vertical-align: middle;\">" + weeksArray[a] + "</th>";
	    	else
	    		tr_html += "<tr style=\"border-top:4px solid #C0C0C0;\"><th rowspan=\"" + leadersArray.length * period.length + "\" style=\"vertical-align: middle;\">" + weeksArray[a] + "</th>";
	    	for (var b = 0; b < leadersArray.length; b++) {
	            if (b == 0) {
	                //alert(leadersArray.length + " " + weeksArray.length + " " + period.length);
	                tr_html += "<th rowspan=\""+period.length+"\" style='vertical-align: middle'>" + leadersArray[b].leaderName + "</th>";
	            } else {
	                tr_html += "<tr><th rowspan=\"" + period.length + "\" style='vertical-align: middle'>" + leadersArray[b].leaderName + "</th>";
	            }
	            for (var c = 0; c < period.length; c++) {
	                if (c >= 2) tr_html += "<tr>";
	                tr_html += "<th style='vertical-align: middle'>" + period[c] + "</th>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + leadersArray[b].level + "_" + (c + 1) + "_1\" style='vertical-align: middle'></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + leadersArray[b].level + "_" + (c + 1) + "_2\" style='vertical-align: middle'></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + leadersArray[b].level + "_" + (c + 1) + "_3\" style='vertical-align: middle'></td>";
	                tr_html += "<td id=\"td_" + (a + 1) + "_" + leadersArray[b].level + "_" + (c + 1) + "_4\" style='vertical-align: middle'></td>";
	                tr_html+="</tr>"
	            }
	        }
	    }
		//console.log(tr_html);
	    $("#tb_test").html(tr_html);
}

//禁用所有查询按钮和连接
function disableAllLinkandBtn(){
	$('button').attr('disabled',true); // Disables visually
	$('a').attr('disabled', true); // Disables visually
}

//禁用所有查询按钮和连接
function enableAllLinkandBtn(){
	$('button').attr("disabled",false); // Disables visually + functionally
	$('a').attr('disabled', false); // Disables visually
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
		getLeaderInfo();
	}else {
		getLeaderInfo(leader_name,null);
	}
	$('#afterweek').removeClass('btn-primary');
	$('#afterweek').addClass('btn-default');
	
	$('#nowweek').removeClass('btn-default');
	$('#nowweek').addClass('btn-primary');
	
	$('#nextweek').removeClass('btn-primary');
	$('#nextweek').addClass('btn-default');
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
				var spinnerOpts={
						lines: 13, // 共有几条线组成
					    length: 7, // 每条线的长度
					    width: 4, // 每条线的粗细
					    radius: 10, // 内圈的大小
					    corners: 1, // 圆角的程度
					    rotate: 0, // 整体的角度（因为是个环形的，所以角度变不变其实都差不多）
					    color: '#000', // 颜色 #rgb 或 #rrggbb
					    speed: 1, // 速度：每秒的圈数
					    trail: 60, // 高亮尾巴的长度
					    shadow: false, // 是否要阴影
					    hwaccel: false, // 是否用硬件加速
					    className: 'spinner', // class的名字
					    zIndex: 7, // z-index的值 2e9（默认为2000000000）
					    top: 'auto', // 样式中的top的值（以像素为单位，写法同css）
					    left: 'auto' // 样式中的left的值（以像素为单位，写法同css）	
					};
				var target=document.getElementById('foo');
				var spinner = new Spinner(spinnerOpts);
				spinner.spin(target);
				$("#form-1").submit();
				$("#filename").text("");

			} else {
				alert("请上传Excel文件");
			}
		}
	}

}

var leader;
var weekName = "周一至周日";
var savingAgendaExcelName = "";
function showAgendaDownloadModal() {
	
	if(leader_name){
		leader = leader_name;
	}else{
		leader = "所有领导";
	}
	
	var startDate = time_start;
	var endDate = time_end;
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
        //fileext: ".xlsx",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
    });
    $("#agendaDownlaodModal").modal('hide');
}

function downloadAgenda(){
	$.ajax({
		type : "get",
		url : "/schedule/downloadAgenda",
		async : false,
		data:{
			leader:leader_name,
			currentDate : time_start,
		},
		dataType : "json",
		success : function(data) {
			$("#agendaInfo").html(data);
			$("#agendaDownlaodModal").modal('hide');
		},
		error : function(data) {
			
		}
	});
}

//禁止所有ajax缓存
$(function(){
    $.ajaxSetup ({
        cache: false //false为关闭，ture为打开
    });
});