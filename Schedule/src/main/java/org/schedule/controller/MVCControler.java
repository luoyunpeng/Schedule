package org.schedule.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.schedule.entity.Schedule;
import org.schedule.service.ScheduleService;
import org.schedule.util.ExcelReadUtil;
import org.schedule.util.WeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MVCControler {
	

    @RequestMapping("/index")
    public String index(HttpServletResponse response) {
		response.setContentType("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
       return "index";
    }
	
    @RequestMapping("/indexForLeader")
    public String indexForLeader(){
       return "indexLeader";
    }
	
    @Autowired
    private ScheduleService schService;
    
    @RequestMapping("/")
    public String projectIndex(){
    	return "indexLeader";
    }

    @RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpServletResponse response){
        try {
        	response.setContentType("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			InputStream inputStream = file.getInputStream();
			String timeStamp= new Date().getTime()+"_";
			File file2 = new File("C:/Schedule/temp/rctemp.xls"+timeStamp);
			if(!file2.getParentFile().exists()){
				if(!file2.getParentFile().mkdirs()){
					System.out.println("create dir failure");
				}
			}
			if(!file2.exists()){
			   file2.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(file2);
			byte[] buffer = new byte[512];
			int bytesToRead = -1;
			while ((bytesToRead = inputStream.read(buffer)) != -1) {
			   outputStream.write(buffer, 0, bytesToRead);
			}
			outputStream.close();
			System.out.println(file2.getPath());
			// InputStream is=new FileInputStream(new
			// File(request.getServletContext().getRealPath("/WEB-INF/res/rctemp.xls")));
			Workbook wb = null;
			try {
			   wb = WorkbookFactory.create(new File("C:/Schedule/temp/rctemp.xls"+timeStamp));
			} catch (EncryptedDocumentException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			} catch (InvalidFormatException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}
			Sheet sheet = wb.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();
			if(totalRow<2){
				wb.close();
				file2.delete();
				response.getWriter().print("<script>alert('无数据/文件格式异常');window.location.href='index';</script>");
				return "index";
			}
			ExcelReadUtil excelReadUtil=new ExcelReadUtil();
			List<Schedule> schedules=new ArrayList<>();
			for (int i = 2; i <= totalRow; i++) {
			   Row row = sheet.getRow(i);
			   if(row==null){
				   continue;
			   }
			   //1.Date
			   Cell dateCell=row.getCell(0);
			   System.out.println(dateCell);
			   if(dateCell==null){
				   continue;
			   }
			   Date date = excelReadUtil.getMergeOrDate(sheet, i, dateCell, row);
			   if(date==null){
				   continue;
			   }
			   //2.Leader
			   Cell leaderCell=row.getCell(1);
			   if(leaderCell==null){
				   continue;
			   }
			   String leader = excelReadUtil.getMergeOr(sheet, i, leaderCell, row);
			   
			   List<Schedule> scheduleByNameAndDate = schService.getScheduleByNameAndDate(leader, WeekUtil.format(date));
			   if(scheduleByNameAndDate!=null&&scheduleByNameAndDate.size()>0){
				   //删除leader的date所处一周的数据
				   schService.deleteScheduleByNameAndDate(leader, WeekUtil.format(date));
			   }
			   
			   //3.Time
			   Cell timeCell=row.getCell(2);
			   if(timeCell==null){
				   timeCell=row.createCell(2);
				   timeCell.setCellValue("");
			   }
			   String time = excelReadUtil.getMergeOr(sheet, i, timeCell, row);
			   //4.Content
			   Cell contentCell=row.getCell(3);
			   if(contentCell==null){
				   contentCell=row.createCell(3);
				   contentCell.setCellValue("");
				   
			   }
			   String content = excelReadUtil.getMergeOr(sheet, i, contentCell, row);
			   //5.Address
			   Cell addressCell=row.getCell(4);
			   if(addressCell==null){
				   addressCell=row.createCell(4);
				   addressCell.setCellValue("");
			   }
			   String address = excelReadUtil.getMergeOr(sheet, i, addressCell, row);
			  
			   //6.join people
			   Cell peopleCell=row.getCell(5);
			   if(peopleCell==null){
				   peopleCell=row.createCell(5);
				   peopleCell.setCellValue("");
			   }
			   String people = excelReadUtil.getMergeOr(sheet, i, peopleCell, row);
			   //7.remarks
			   Cell remarksCell=row.getCell(6);
			   if(remarksCell==null){
				   remarksCell=row.createCell(6);
				   remarksCell.setCellValue("");
			   }
			   String remarks = excelReadUtil.getMergeOr(sheet, i, remarksCell, row);
			   Schedule schedule = new Schedule(leader, content, address, date, time, people, remarks);
			   if(content==null||content==""||time==null||time==""){
				   continue;
			   }
			   if (leader != null && leader != "" && date != null && time != null) {
			       schedules.add(schedule);
			   }
			}
			if(schedules.size()==0){
				wb.close();
				file2.delete();
				response.getWriter().print("<script>alert('上传成功,成功录入:0');window.location.href='index';</script>");
				return "index";
			}
			int flag=schService.loadSchedule(schedules);
			System.out.println(flag);
			response.getWriter().print("<script>alert('上传成功,成功录入:"+flag+"');window.location.href='index';</script>");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return "index";
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest req, HttpServletResponse response) {

        ServletOutputStream out = null;
        java.io.InputStream fin = null;
        String fileName = "日程模板";
        String realPath = "C:/Schedule/temp/日程模板.xls";
        File file = new File(realPath);
        if(!file.getParentFile().exists()){
        	if(!file.getParentFile().mkdirs()){
        		System.out.println("create dir failure");
        	}
        }
        System.out.println(file.exists());
        try {
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition",
                  "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

            out = response.getOutputStream();
            byte[] buffer = new byte[512];
            int bytesToRead = -1;

            while ((bytesToRead = fin.read(buffer)) != -1) {
                 out.write(buffer, 0, bytesToRead);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "index";
   }
    
    
    @ExceptionHandler({Exception.class})
    public String processException(Exception exception){
    	
    	return "error";
    }
}
