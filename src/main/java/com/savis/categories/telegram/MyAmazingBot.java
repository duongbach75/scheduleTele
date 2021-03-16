package com.savis.categories.telegram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.springframework.util.ResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.savis.categories.common.ExcelPoiUtil;
import com.savis.categories.common.UtilsDate;
import com.savis.categories.entity.keyValue;
import com.savis.categories.entity.schedule;
import java.nio.file.Path;

public class MyAmazingBot extends TelegramLongPollingBot {
	@Autowired
	ResourceLoader resourceLoader;
	List<String> list= new ArrayList<String>();
	List<String> blacklist= new ArrayList<String>();
	Random randomGenerator= new Random();
	@Value("${ThoiKhoaBieuSinhVien}")
	String thoiKhoaBieuSinhVien;
	@Override
	public void onUpdateReceived(Update update) {
		list.add("Thứ 2 được không?");
		list.add("Thứ 3 được không?");
		list.add("Thứ 4 được không?");
		list.add("Thứ 5 được không?");
		list.add("Thứ 6 được không?");
		list.add("Thứ 7 được không?");
		list.add("Chủ nhật được không?");
		
		blacklist.add("dmm");
		blacklist.add("địt");
		blacklist.add("cặc");
		blacklist.add("lồn");
		blacklist.add("đéo");
		blacklist.add("dcm");
		blacklist.add("deo");
		blacklist.add("cac");
		
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Set variables
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			if (message_text.equals("/start")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("schedule")) {
				SendMessage message=null;
				List<String> list = new ArrayList<String>();
				try {
					list = schedule();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (String string : list) {
					message = new SendMessage() // Create a message object object
							.setChatId(chat_id).setText(string);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
				}
					
			} else if (message_text.indexOf("Niệm")!=-1) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Trưởng phòng Cloud Viettel");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.indexOf("Trọng")!=-1) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Tổng giám đốc tập đoàn Shapro");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.indexOf("bánh mì")!=-1) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Có thằng nào đi ăn bánh mì không");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("bia")) {

				
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(list.get(randomGenerator.nextInt(list.size())));
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("khong")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Thế mày rảnh hôm nào? "+list.get(randomGenerator.nextInt(list.size())));
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("ok")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("ok cái đ!t mẹ m");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (checklist(message_text)==false) {
				List<String> blacklist= new ArrayList<String>();
				blacklist.add("im mồm đmm, cấm nói bậy");
				blacklist.add("Địt mẹ thằng láo cá chó này");
				blacklist.add("Mày đừng có mà ương");
				blacklist.add("á à đmm thích nói bậy à");
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(blacklist.get(randomGenerator.nextInt(blacklist.size())));
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("/hide")) {
				SendMessage msg = new SendMessage().setChatId(chat_id).setText("Keyboard hidden");
				ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
				msg.setReplyMarkup(keyboardMarkup);
				try {
					sendMessage(msg); // Call method to send the photo
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} 
		} else if (update.hasMessage() && update.getMessage().hasPhoto()) {
			
		}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "bachtes2bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1608177542:AAFifgOQotlUFQQhTGyxHM8Me8tUKUIIv44";
	}
public boolean checklist(String message_text) {
	for (String string : blacklist) {
		if (message_text.indexOf(string)!=-1)
			return false;
	}
	return true;
	
}
	public List<String> schedule() throws URISyntaxException {
		List<String> sche=new ArrayList<String>();
		try {
			List<schedule> listBooks = readExcel("/opt/ThoiKhoaBieuSinhVien.xls");
			
			for (schedule schedule : listBooks) {
				Date today = new Date();
				Date from = UtilsDate.str2date(schedule.getDay().split("-")[0], "dd/MM/yyyy");
				Date to = UtilsDate.str2date(schedule.getDay().split("-")[1], "dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(today);
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				if (isWithinRange(today, from, to) == true && String.valueOf(dayOfWeek).equals(schedule.getNumber())) {
					sche.add( "Ca học: " + schedule.getTime() + " Môn học: " + schedule.getName() + " Phòng học "
							+ schedule.getRoom()+". Kính mong được gặp các sếp trên giảng đường");
				}
			}
			if(sche.isEmpty()) sche.add("Không có lịch học các sếp ạ");
			return sche;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return sche;
		}
		

	}

	public static final int COLUMN_INDEX_ID = 4;
	public static final int COLUMN_INDEX_TITLE = 8;
	public static final int COLUMN_INDEX_PRICE = 10;
	public static final int COLUMN_INDEX_QUANTITY = 9;
	public static final int number = 0;
	private static String getProjectPath() throws URISyntaxException{
	    URL url = MyAmazingBot.class.getResource("/");
	    Path path = (Path) Paths.get(url.toURI());
	    Path subPath = path.subpath(0, path.getNameCount() -2);
	    return "/" + subPath.toString();
	}
	public static List<schedule> readExcel(String excelFilePath) throws IOException, URISyntaxException {

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
				// Read cell
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
			if (canhanImportDTO.getNumber() != null && StringUtils.isNumeric(canhanImportDTO.getNumber()))
				listBooks.add(canhanImportDTO);
		}

		workbook.close();
		inputStream.close();

		return listBooks;
	}

	public boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
		return !(testDate.before(startDate) || testDate.after(endDate));
	}
	
	public static List<keyValue> readExcelKeyword(String excelFilePath) throws IOException {

		List<keyValue> listBooks = new ArrayList<>();

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
			keyValue canhanImportDTO = new keyValue();
			while (cellIterator.hasNext()) {
				// Read cell
				Cell cell = cellIterator.next();
				Object cellValue = ExcelPoiUtil.getCellValue(cell);
				if (cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				// Set value for book object
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					canhanImportDTO.setKey(ExcelPoiUtil.getCellValue(cell));
					break;
				case 1:
					canhanImportDTO.setValue(ExcelPoiUtil.getCellValue(cell));
					break;
				case 2:
					canhanImportDTO.setType(ExcelPoiUtil.getCellValue(cell));
				
				}

			}
			
		}

		workbook.close();
		inputStream.close();

		return listBooks;
	}
}
