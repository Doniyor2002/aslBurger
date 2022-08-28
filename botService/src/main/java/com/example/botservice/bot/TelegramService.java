package com.example.botservice.bot;

import com.example.botservice.dto.UserDtoForBot;
import com.example.botservice.entity.Order;
import com.example.botservice.entity.User;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TelegramService {
    SendMessage start(Update update);
    SendMessage region(Update update,String language);
    SendMessage sendPhone(Update update,String text) throws IOException;
    SendMessage acceptAll(Update update,String phone) throws IOException;
    SendMessage read(Update update) throws IOException;
    SendMessage order(Update update,String order) throws IOException;
    SendMessage orderSave(Update update) throws IOException;
    SendMessage parentCategory(Update update);
//    SendMessage parentCategoryCallbackquery(Update update);
    SendMessage  category(Update update);
    SendMessage productList(Update update);
    SendPhoto product(Update update);
    SendMessage saveDetail(Update update) throws FileNotFoundException;
    SendMessage detailList(Update update) throws TelegramApiValidationException;
    SendMessage delivery(Update update);
    SendMessage location (Update update);
    SendMessage payType(Update update);
    SendMessage discount(Update update);
    DeleteMessage delete(Update update);
    EditMessageReplyMarkup edit(Update update);
    SendMessage deleteDetail(Update update);
    SendMessage getAllOrder(Update update);
    SendMessage sozlamalar(Update update);
    SendMessage changeLanguage(Update update);
    SendMessage support(Update update);
    SendMessage supportSave(Update update, User user) throws IOException;
    SendMessage supportType(Update update);
    SendInvoice payClick(Update update);
    SendMessage detail(Update update,User user,String language);
    SendMessage checkPhoneNumber(Update update,User user) throws IOException;
    UserDtoForBot languageDetection(Update update, Long chatId);
    SendMessage sendOrderStatus(Order order);
}




















