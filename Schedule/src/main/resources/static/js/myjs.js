$(document).ready(function() {

	$("li").addClass("text-center");

	//把后台获取到的数据赋给定义好的数组变量
	for(var i = 0, l = leaderData.length; i < l; i++) {
		if(leaderData[i].week == '星期一') {
			if(leaderData[i].timenow == '上午') {
				MondayMorning.push(leaderData[i]);
				$("#MondayMorning").addClass("success");
			};
			if(leaderData[i].timenow == '下午') {
				MondayAfternoon.push(leaderData[i]);
				$("#MondayAfternoon").addClass("active");
			};
			if(leaderData[i].timenow == '晚上') {
				MondayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期二') {
			if(leaderData[i].timenow == '上午') {
				TuesdayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				TuesdayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				TuesdayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期三') {
			if(leaderData[i].timenow == '上午') {
				WednesdayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				WednesdayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				WednesdayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期四') {
			if(leaderData[i].timenow == '上午') {
				ThursdayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				ThursdayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				ThursdayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期五') {
			if(leaderData[i].timenow == '上午') {
				FridayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				FridayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				FridayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期六') {
			if(leaderData[i].timenow == '上午') {
				SaturdayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				SaturdayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				SaturdayNight.push(leaderData[i]);
			}
		};
		if(leaderData[i].week == '星期天') {
			if(leaderData[i].timenow == '上午') {
				SundayMorning.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '下午') {
				SundayAfternoon.push(leaderData[i]);
			};
			if(leaderData[i].timenow == '晚上') {
				SundayNight.push(leaderData[i]);
			}
		}
		
	}
});

var time_start;
var time_end;
var leader_name;

function outFile(){
	
}
//后台获取到的数据
var leaderData = [{
		name: '秦如培',
		datatitle: '开会',
		time: '2017-08-28',
		week: '星期一',
		timenow: '上午',
		place: "贵安新区",
		joinpersonnel: "张三，李四，王麻子",
		remarks: '有事微信留言'
	},
	{
		name: '马长青',
		datatitle: '外访',
		time: '2017-08-28',
		week: '星期一',
		timenow: '下午',
		place: "金阳高新区",
		joinpersonnel: "赵四，王麻子",
		remarks: '随时电话联系'
	},
	{
		name: '孙登峰',
		datatitle: '座谈会',
		time: '2017-08-28',
		week: '星期一',
		timenow: '晚上',
		place: "贵安新区",
		joinpersonnel: "全体干部",
		remarks: '请做好笔记'
	},
]

//自定义数组变量来存取数据
var MondayMorning = [];
var MondayAfternoon = [];
var MondayNight = [];
var TuesdayMorning = [];
var TuesdayAfternoon = [];
var TuesdayNight = [];
var WednesdayMorning = [];
var WednesdayAfternoon = [];
var WednesdayNight = [];
var ThursdayMorning = [];
var ThursdayAfternoon = [];
var ThursdayNight = [];
var FridayMorning = [];
var FridayAfternoon = [];
var FridayNight = [];
var SaturdayMorning = [];
var SaturdayAfternoon = [];
var SaturdayNight = [];
var SundayMorning = [];
var SundayAfternoon = [];
var SundayNight = [];

function MondayMorningModel() {
	$("#modelshowtime").text(MondayMorning[0].time + MondayMorning[0].timenow + "日程信息");
	/*$("#modeltable").*/
	$("#rcContent").append("<tr><td>111</td></tr>")
}

function MondayAfternoonModel() {
	$("#modelshowtime").text(MondayAfternoon[0].time + MondayAfternoon[0].timenow + "日程信息")
}

function MondayNightModel() {
	$("#modelshowtime").html(MondayNight[0].time + MondayNight[0].timenow + "日程信息")
}

function TuesdayMorningModel() {
	$("#modelshowtime").html(TuesdayMorning[0].time + TuesdayMorning[0].timenow + "日程信息")
}

function TuesdayAfternoonModel() {
	$("#modelshowtime").html(TuesdayAfternoon[0].time + TuesdayAfternoon[0].timenow + "日程信息")
}

function TuesdayNightModel() {
	$("#modelshowtime").html(TuesdayNight[0].time + TuesdayNight[0].timenow + "日程信息")
}
function WednesdayMorningModel() {
	$("#modelshowtime").html(WednesdayMorning[0].time + WednesdayMorning[0].timenow + "日程信息")
}

function WednesdayAfternoonModel() {
	$("#modelshowtime").html(WednesdayAfternoon[0].time + WednesdayAfternoon[0].timenow + "日程信息")
}

function WednesdayNightModel() {
	$("#modelshowtime").html(WednesdayNight[0].time + WednesdayNight[0].timenow + "日程信息")
}
function ThursdayMorningModel() {
	$("#modelshowtime").html(ThursdayMorning[0].time + ThursdayMorning[0].timenow + "日程信息")
}

function ThursdayAfternoonModel() {
	$("#modelshowtime").html(ThursdayAfternoon[0].time + ThursdayAfternoon[0].timenow + "日程信息")
}

function ThursdayNightModel() {
	$("#modelshowtime").html(ThursdayNight[0].time + ThursdayNight[0].timenow + "日程信息")
}

function FridayMorningModel() {
	$("#modelshowtime").html(FridayMorning[0].time + FridayMorning[0].timenow + "日程信息")
}

function FridayAfternoonModel() {
	$("#modelshowtime").html(FridayAfternoon[0].time + FridayAfternoon[0].timenow + "日程信息")
}

function FridayNightModel() {
	$("#modelshowtime").html(FridayNight[0].time + FridayNight[0].timenow + "日程信息")
}

function SaturdayMorningModel() {
	$("#modelshowtime").html(SaturdayMorning[0].time + SaturdayMorning[0].timenow + "日程信息")
}

function SaturdayAfternoonModel() {
	$("#modelshowtime").html(SaturdayAfternoon[0].time + SaturdayAfternoon[0].timenow + "日程信息")
}

function SaturdayNightModel() {
	$("#modelshowtime").html(SaturdayNight[0].time + SaturdayNight[0].timenow + "日程信息")
}

function SundayMorningModel() {
	$("#modelshowtime").html(SundayMorning[0].time + SundayMorning[0].timenow + "日程信息")
}

function SundayAfternoonModel() {
	$("#modelshowtime").html(SundayAfternoon[0].time + SundayAfternoon[0].timenow + "日程信息")
}

function SundayNightModel() {
	$("#modelshowtime").html(SundayNight[0].time + SundayNight[0].timenow + "日程信息")
}

//点击表格弹出模态框
function showAgentda(n) {
	$('#agendaShowModel').modal() // 以默认值初始化
	switch(n) {
		case 1:
			MondayMorningModel();
			break;
		case 2:
			MondayAfternoonModel();
			break;
		case 3:
			MondayNightModel();
			break;
		case 4:
			TuesdayMorningModel();
			break;
		case 5:
			TuesdayAfternoonModel();
			break;
		case 6:
			TuesdayNightModel();
			break;
		case 7:
			WednesdayMorningModel();
			break;
		case 8:
			WednesdayAfternoonModel();
			break;
		case 9:
			WednesdayNightModel();
			break;
		case 10:
			ThursdayMorningModel();
			break;
		case 11:
			ThursdayAfternoonModel();
			break;
		case 12:
			ThursdayNightModel();
			break;
		case 13:
			FridayMorningModel();
			break;
		case 14:
			FridayAfternoonModel();
			break;
		case 15:
			FridayNightModel();
			break;
		case 16:
			SaturdayMorningModel();
			break;
		case 17:
			SaturdayAfternoonModel();
			break;
		case 18:
			SaturdayNightModel();
			break;
		case 19:
			SundayMorningModel();
			break;
		case 20:
			SundayAfternoonModel();
			break;
		case 21:
			SundayNightModel();
			break;
		
	}
	console.log($("#agendaShowModel"));

}

function hideAgenda() {
	$("#agendaShowModel").modal("hide");
}
//点击左边
function clicleader(name) {
	$("#leaderName").html(name + "的工作日程");
}
//点击左边树形菜单

//导入文件
function inputFile() {
	var file = $("#filename").val();
	console.log(file);
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