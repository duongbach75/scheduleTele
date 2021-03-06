package com.savis.categories.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.savis.categories.entity.*;
import com.savis.categories.telegram.MyAmazingBot;
import com.savis.categories.common.*;
@Service
@Configuration
public class serviceSchedule {
    public static final int COLUMN_INDEX_ID = 3;
    public static final int COLUMN_INDEX_TITLE = 8;
    public static final int COLUMN_INDEX_PRICE = 10;
    public static final int COLUMN_INDEX_QUANTITY = 9;
    public static final int number = 0;
    
	public static List<schedule> readExcel(String excelFilePath) throws IOException {
		
        List<schedule> listBooks = new ArrayList<>();
 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = ExcelPoiUtil.getWorkbook(inputStream, excelFilePath);
 
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            schedule canhanImportDTO = new schedule();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = ExcelPoiUtil.getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_INDEX_ID:
                	canhanImportDTO.setName(ExcelPoiUtil.getCellValue(cell));
                    break;
                case COLUMN_INDEX_TITLE:
                	canhanImportDTO.setTime(ExcelPoiUtil.getCellValue(cell));
                    break;
                case COLUMN_INDEX_QUANTITY:
                	canhanImportDTO.setRoom(ExcelPoiUtil.getCellValue(cell));
                    break;
                case COLUMN_INDEX_PRICE:
                	canhanImportDTO.setDay(ExcelPoiUtil.getCellValue(cell));
                    break;
                case number:
                	canhanImportDTO.setNumber(ExcelPoiUtil.getCellValue(cell));
                    break;
                case 1:
                	canhanImportDTO.setCode(ExcelPoiUtil.getCellValue(cell));
                    break;
                }
 
            }
            if(canhanImportDTO.getNumber()!=null&& StringUtils.isNumeric(canhanImportDTO.getNumber()))
            listBooks.add(canhanImportDTO);
        }
 
        workbook.close();
        inputStream.close();
 
        return listBooks;
    }
	public String check() {
		 try {
			List<schedule> listBooks = readExcel("G:\\ThoiKhoaBieuSinhVien.xls");
			for (schedule schedule : listBooks) {
				Date today= new Date();
				Date from =UtilsDate.str2date(schedule.getDay().split("-")[0], "dd/MM/yyyy");
				Date to =UtilsDate.str2date(schedule.getDay().split("-")[1], "dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(today);
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				if(isWithinRange(today, from, to)==true && String.valueOf(dayOfWeek).equals(schedule.getNumber())) {
					callAPITele("Ca học: "+schedule.getTime() +" Môn học: "+schedule.getName()+" Phòng học "+schedule.getRoom());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public boolean isWithinRange(Date testDate,Date startDate,Date endDate) {
		   return !(testDate.before(startDate) || testDate.after(endDate));
		}
	public ResponseEntity<String> callAPITele(String url) {
		String tele="https://api.telegram.org/bot1664163037:AAFkkbXPI8b_7TD1NBRv21RhUqGoc5y8sbo/sendMessage?chat_id=-573307904&text="+url;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> request = new HttpEntity<>(headers);
		return restTemplate.exchange(tele, HttpMethod.GET, request, String.class);

	}
	@Bean
	public void runBot() {

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	    
	}
	public String schedule() {
		 try {
			List<schedule> listBooks = readExcel("G:\\ThoiKhoaBieuSinhVien.xls");
			for (schedule schedule : listBooks) {
				Date today= new Date();
				Date from =UtilsDate.str2date(schedule.getDay().split("-")[0], "dd/MM/yyyy");
				Date to =UtilsDate.str2date(schedule.getDay().split("-")[1], "dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(today);
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				if(isWithinRange(today, from, to)==true && String.valueOf(dayOfWeek).equals(schedule.getNumber())) {
					return "Ca học: "+schedule.getTime() +" Môn học: "+schedule.getName()+" Phòng học "+schedule.getRoom();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Lỗi rồi";
		}
		return "Không có ca học nào";
		
	}
}
