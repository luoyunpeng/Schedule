package org.schedule.util;

import static org.mockito.Matchers.intThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.schedule.entity.Schedule;
import org.schedule.entity.Week;

import ch.qos.logback.classic.Level;
import groovyjarjarantlr.collections.List;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Boolean;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportDataToExcelUtil {
	public String fileFullPath;
	public File agentFile;
	public String tileOfSheet;
	public ExportDataToExcelUtil(String leader,Date currentDate,int dayNumber) {
		// TODO Auto-generated constructor stub
		try {
			String timeStamp= new Date().getTime()+"_";
			if( leader == null || leader.equals("")) leader="所有领导";
			tileOfSheet=leader+"_"+WeekUtil.getFirstDayOfCurrentWeek(currentDate, dayNumber)+"至"+WeekUtil.getLastDayOfCurrentWeek(currentDate, dayNumber)+"(周一至周日)的日程内容";
			fileFullPath="C:/Schedule/temp/"+tileOfSheet+timeStamp+".xls";
			agentFile=new File(fileFullPath);
			if(agentFile.exists()) {
				agentFile.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String generateExcelSheetHeader(java.util.List<org.schedule.entity.Level> allLeaderName, Week week,java.util.List<Schedule> agendaContentList) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fOutputStream=new FileOutputStream(agentFile);
			//创建工作薄
	        WritableWorkbook workbook = Workbook.createWorkbook(fOutputStream);
	        //创建新的一页
	        WritableSheet sheet = workbook.createSheet("领导日程", 0);
	        //构造表头
	        sheet.mergeCells(0, 0, 6, 0);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
	        WritableFont bold = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
	        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
	        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
	        titleFormate.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);  
	        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
	        Label title = new Label(0,0,tileOfSheet,titleFormate);
	        sheet.setRowView(0, 600, false);//设置第一行的高度
	        sheet.addCell(title);
	        sheet.setRowView(1, 300, false);//设置第二行的高度
	        
	        
	      //创建要显示的具体内容
	        WritableFont color = new WritableFont(WritableFont.ARIAL);//选择字体
	        color.setColour(Colour.GOLD);//设置字体颜色为金黄色
	        WritableCellFormat colorFormat = new WritableCellFormat(color);
	        Label dateHeader = new Label(0,1,"日期",titleFormate);
	        sheet.addCell(dateHeader);
	        Label leader = new Label(1,1,"领导",titleFormate);
	        sheet.addCell(leader);
	        Label period = new Label(2,1,"时间",titleFormate);
	        sheet.addCell(period);
	        Label agendaContent = new Label(3,1,"日程内容",titleFormate);
	        sheet.addCell(agendaContent);
	        Label place = new Label(4,1,"地点",titleFormate);
	        sheet.addCell(place);
	        Label joinPeopleSection = new Label(5,1,"参与部门或人员",titleFormate);
	        sheet.addCell(joinPeopleSection);
	        Label comment = new Label(6,1,"备注",titleFormate);
	        sheet.addCell(comment);
	        
	        
	        for(int a=0;a<week.getListWeek().length;a++) {
	        	Label dayInfo=new Label(0,a*allLeaderName.size()*week.getPeriod().length+2, week.getListWeek()[a],titleFormate);
	        	sheet.addCell(dayInfo);
	        	sheet.mergeCells(0, 2+a*allLeaderName.size()*week.getPeriod().length, 0, 1+(a+1)*allLeaderName.size()*week.getPeriod().length);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
	        	for(int b =0 ; b< allLeaderName.size(); b++) {
	        		Label leaderInfo=new Label( 1, 2+a*allLeaderName.size()*week.getPeriod().length+b*week.getPeriod().length,allLeaderName.get(b).getLeaderName(),titleFormate);
	        		sheet.addCell(leaderInfo);
	        		sheet.mergeCells(1,2+a*allLeaderName.size()*week.getPeriod().length+b*week.getPeriod().length, 1, 2+a*allLeaderName.size()*week.getPeriod().length+(b+1)*week.getPeriod().length-1);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
	        		for(int c=0; c<week.getPeriod().length;c++) {
	        			Label periodInfo=new Label( 2, 2+a*allLeaderName.size()*week.getPeriod().length+b*week.getPeriod().length+c,week.getPeriod()[c],titleFormate);
		        		sheet.addCell(periodInfo);
	        		}
	        	}
	        }
	        
	        WritableFont textbold = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
	        WritableCellFormat textFormat = new WritableCellFormat(textbold);//生成一个单元格样式控制对象
	        textFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);  
	        textFormat.setWrap(true);
	        //textFormat.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
	        textFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
	        
	        for(int i = 0; i<agendaContentList.size();i++ ) {
	        	int rowCursor=2;
	        	if(allLeaderName.size()>1) {
	        		rowCursor=1+(agendaContentList.get(i).getDayId()-1)*allLeaderName.size()*week.getPeriod().length+(agendaContentList.get(i).getLevel()-1)*week.getPeriod().length+agendaContentList.get(i).getPeriodId();
		        	
	        	}else if (allLeaderName.size()==1) {
	        		rowCursor=1+(agendaContentList.get(i).getDayId()-1)*allLeaderName.size()*week.getPeriod().length+agendaContentList.get(i).getPeriodId();
				}
	        	
	        	if(!sheet.getCell(3,rowCursor).getContents().equals("")||sheet.getCell(3,rowCursor).getContents()!="") {
	        		Label agendaContentInfo=(Label)sheet.getCell(3, rowCursor);
		        	agendaContentInfo.setString(sheet.getCell(3, rowCursor).getContents()+"\n"+agendaContentList.get(i).getContent().replace( "<br/>","\n"));
		        	Label agendaPlaceInfo=(Label)sheet.getCell(4, rowCursor);
		        	agendaPlaceInfo.setString(sheet.getCell(4, rowCursor).getContents()+"\n"+agendaContentList.get(i).getAddress().replace( "<br/>","\n"));
		        	Label agendaJionSecPInfo=(Label)sheet.getCell(5, rowCursor);
		        	agendaJionSecPInfo.setString(sheet.getCell(5, rowCursor).getContents()+"\n"+agendaContentList.get(i).getRelatedPeopleAndDep().replace( "<br/>","\n"));
		        	Label agendaCommentInfo=(Label)sheet.getCell(6, rowCursor);
		        	agendaCommentInfo.setString(sheet.getCell(6, rowCursor).getContents()+"\n"+agendaContentList.get(i).getComment().replace( "<br/>","\n"));
	        	}else {
	        		Label agendaContentInfo=new Label(3,rowCursor,agendaContentList.get(i).getContent().replace( "<br/>","\n"),textFormat);
		        	sheet.addCell(agendaContentInfo);
		        	Label agendaPlaceInfo=new Label(4,rowCursor,agendaContentList.get(i).getAddress().replace( "<br/>","\n"),textFormat);
		        	sheet.addCell(agendaPlaceInfo);
		        	Label agendaJionSecPInfo=new Label(5,rowCursor,agendaContentList.get(i).getRelatedPeopleAndDep().replace( "<br/>","\n"),textFormat);
		        	sheet.addCell(agendaJionSecPInfo);
		        	Label agendaCommentInfo=new Label(6,rowCursor,agendaContentList.get(i).getComment().replace( "<br/>","\n"),textFormat);
		        	sheet.addCell(agendaCommentInfo);
				}
	        	
	        }
	        /*	        CellView cView=new CellView();
	        cView.setAutosize(true);*/
	        for(int i =1 ; i< allLeaderName.size()*week.getListWeek().length*week.getPeriod().length+2; i++) {
	        	sheet.setRowView(i,600,false);
	        }
	        sheet.setColumnView(0,24);
	        sheet.setColumnView(1,12);
	        sheet.setColumnView(2,12);
	        sheet.setColumnView(3,48);
	        sheet.setColumnView(4,24);
	        sheet.setColumnView(5,36);
	        sheet.setColumnView(6,36);
	        for(int i=0 ; i<7; i++) {
	        	for(int j =0 ; j< allLeaderName.size()*week.getListWeek().length*week.getPeriod().length+2; j++) {
		        	if(sheet.getCell(i,j).getContents().equals("")||sheet.getCell(i,j).getContents()=="") {
		        		Label nullTextLable=new Label(i,j,"",textFormat);
		        		sheet.addCell(nullTextLable);
		        	}
		        }
	        }
	        workbook.write();
	        workbook.close();
	        fOutputStream.close();
	        return fileFullPath;
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
	}
	public String downloadAgenda(HttpServletRequest req, HttpServletResponse response,String fileFullPath) {
        return tileOfSheet;
   }
}
