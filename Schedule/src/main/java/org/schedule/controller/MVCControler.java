package org.schedule.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.schedule.entity.Schedule;
import org.schedule.service.ScheduleService;
import org.schedule.util.ExcelReadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MVCControler {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@Autowired
	private ScheduleService schService;

	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		InputStream inputStream = file.getInputStream();
		File file2 = new File(this.getClass().getResource("/").getPath()+"static/rctemp.xls");
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
			wb = WorkbookFactory.create(new File(this.getClass().getResource("/").getPath()+"static/rctemp.xls"));
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
		int totalRow = sheet.getLastRowNum();
		ExcelReadUtil excelReadUtil=new ExcelReadUtil();
		for (int i = 2; i <= totalRow; i++) {
			Row row = sheet.getRow(i);
			String leader = excelReadUtil.getMergeOr(sheet, i, row.getCell(0), row);
			String content = excelReadUtil.getMergeOr(sheet, i, row.getCell(1), row);
			String address = excelReadUtil.getMergeOr(sheet, i, row.getCell(2), row);
			Date date = excelReadUtil.getMergeOrDate(sheet, i, row.getCell(3), row);
			String time = excelReadUtil.getMergeOr(sheet, i, row.getCell(4), row);
			String people = excelReadUtil.getMergeOr(sheet, i, row.getCell(5), row);
			String remarks = excelReadUtil.getMergeOr(sheet, i, row.getCell(6), row);
			Schedule schedule = new Schedule(leader, content, address, date, time, people, remarks);
			if (leader != null && leader != "" && date != null && time != null) {
				schService.insertSchedule(schedule);
			}
		}
		wb.close();
		file2.delete();
		return "redirect:index2.jsp";
	}

	@RequestMapping("/download")
	public String download(HttpServletRequest req, HttpServletResponse response) {

		ServletOutputStream out = null;
		java.io.InputStream fin = null;
		String fileName = "rc";
		String realPath = this.getClass().getResource("/").getPath()+"static/rc.xls";
		File file = new File(realPath);
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

		return null;
	}
}
