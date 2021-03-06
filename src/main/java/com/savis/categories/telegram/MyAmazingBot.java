package com.savis.categories.telegram;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.savis.categories.common.ExcelPoiUtil;
import com.savis.categories.common.UtilsDate;
import com.savis.categories.entity.schedule;
import com.savis.categories.service.serviceSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MyAmazingBot extends TelegramLongPollingBot {
	@Autowired
	private serviceSchedule serviceSchedule;

	@Override
	public void onUpdateReceived(Update update) {

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
			} else if (message_text.equals("/schedule")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(schedule());
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("/Bia")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Hôm nào?");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();
                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("thu 2");
                row.add("thu 3");
                row.add("thu 4");
                // Add the first row to the keyboard
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("thu 5");
                row.add("thu 6");
                row.add("chu nhat");
                // Add the second row to the keyboard
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (message_text.equals("thu 2")) {
            	SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.equals("thu 3")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.equals("thu 4")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.equals("thu 5")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.equals("thu 6")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (message_text.equals("chu nhat")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(message_text);
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
			} else {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Unknown command");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		} else if (update.hasMessage() && update.getMessage().hasPhoto()) {
			// Message contains photo
			// Set variables
			long chat_id = update.getMessage().getChatId();

			List<PhotoSize> photos = update.getMessage().getPhoto();
			String f_id = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getFileId();
			int f_width = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getWidth();
			int f_height = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getHeight();
			String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: "
					+ Integer.toString(f_height);
			SendPhoto msg = new SendPhoto().setChatId(chat_id).setPhoto(f_id).setCaption(caption);
			try {
				sendPhoto(msg); // Call method to send the message
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
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
	
	public boolean isWithinRange(Date testDate,Date startDate,Date endDate) {
		   return !(testDate.before(startDate) || testDate.after(endDate));
		}
}
