package com.example.botservice.bot;


import com.example.botservice.dto.UserDtoForBot;
import com.example.botservice.entity.*;
import com.example.botservice.entity.enums.OrderStatus;
import com.example.botservice.entity.enums.OrderType;
import com.example.botservice.entity.enums.PayType;
import com.example.botservice.entity.enums.SupportType;
import com.example.botservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TelegramServiceImp implements TelegramService {
    public static String providerTokenClick="398062629:TEST:999999999_F91D8F69C042267444B74CC0B3C747757EB0E065";
    public static List<Discount> discountList=new ArrayList<>();

    public static List<Order> orderList=new ArrayList<>();
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final SaleRepository saleRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final SupportRepository supportRepository;
    @Override
    public SendMessage start(Update update) {
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup.setSelective(true);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardRow row1=new KeyboardRow();
        KeyboardButton button=new KeyboardButton();
        KeyboardButton button1=new KeyboardButton();
        button.setText(Constant.nameUz);
        button1.setText(Constant.nameRu);
        row.add(button);
        row1.add(button1);
        list.add(row);
        list.add(row1);
        markup.setKeyboard(list);
        return SendMessage.builder().text("Здравствуйте! Давайте для начала выберем язык обслуживания!\n" + "\n" +
                "Keling, avvaliga xizmat ko’rsatish tilini tanlab olaylik.").replyMarkup(markup).chatId(String.valueOf(update.getMessage().getChatId())).build();
    }

    @Override
    public SendMessage region(Update update,String language) {
        String data = "";
        Long chatId =0L;
        if (update.hasCallbackQuery()){
            chatId= update.getCallbackQuery().getMessage().getChatId();
        }
        else {
            data=update.getMessage().getText();
            chatId= update.getMessage().getChatId();
        }
        if (language.equals("")){
            User user=new User();
            Set<String> messageList=new HashSet<>();
            messageList.add(data);
            user.setMessageList(messageList);
            user.setChatId(chatId);
            userRepository.save(user);
        }
        else {
            data=language;
        }
        String text="";
        if (data.equals(Constant.nameRu)) {
            text="Выберите свой регион";
        }
        else {
            text="Hududingizni tanlang";
        }
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup.setSelective(true);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();
        KeyboardRow row3=new KeyboardRow();
        KeyboardRow row4=new KeyboardRow();
        KeyboardRow row5=new KeyboardRow();
        KeyboardRow row6=new KeyboardRow();
        KeyboardRow row7=new KeyboardRow();
        KeyboardButton button=new KeyboardButton();
        KeyboardButton button1=new KeyboardButton();
        KeyboardButton button2=new KeyboardButton();
        KeyboardButton button3=new KeyboardButton();
        KeyboardButton button4=new KeyboardButton();
        KeyboardButton button5=new KeyboardButton();
        KeyboardButton button6=new KeyboardButton();
        KeyboardButton button7=new KeyboardButton();
        KeyboardButton button8=new KeyboardButton();
        KeyboardButton button9=new KeyboardButton();
        KeyboardButton button10=new KeyboardButton();
        KeyboardButton button11=new KeyboardButton();
        KeyboardButton button12=new KeyboardButton();
        KeyboardButton button13=new KeyboardButton();
        button.setText(Constant.region);
        button1.setText(Constant.region1);
        button2.setText(Constant.region2);
        button3.setText(Constant.region3);
        button4.setText(Constant.region4);
        button5.setText(Constant.region5);
        button6.setText(Constant.region6);
        button7.setText(Constant.region7);
        button8.setText(Constant.region8);
        button9.setText(Constant.region9);
        button10.setText(Constant.region10);
        button11.setText(Constant.region11);
        button12.setText(Constant.region12);
        button13.setText(Constant.region13);
        row.add(button);
        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(button5);
        row3.add(button6);
        row4.add(button7);
        row4.add(button8);
        row5.add(button9);
        row5.add(button10);
        row6.add(button11);
        row6.add(button12);
        row7.add(button13);
        list.add(row);
        list.add(row1);
        list.add(row2);
        list.add(row3);
        list.add(row4);
        list.add(row5);
        list.add(row6);
        list.add(row7);
        markup.setKeyboard(list);
        return SendMessage.builder().text(text)
                .replyMarkup(markup)
                .chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendMessage sendPhone(Update update,String info) throws IOException {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        String language = userDtoForBot.getLanguage();
        User user = userDtoForBot.getUser();
        StringBuilder builder=new StringBuilder();
        if (!info.equals("")){
            builder.append(info+"\n");
        }
       else {

            if (user.getRegion()!=null&&(text.equals(Constant.region)||text.equals(Constant.region1)||text.equals(Constant.region2)||text.equals(Constant.region3)||text.equals(Constant.region4)
                    ||text.equals(Constant.region5)|| text.equals(Constant.region6)||text.equals(Constant.region7)||text.equals(Constant.region8)||text.equals(Constant.region9)
                    ||text.equals(Constant.region10)||text.equals(Constant.region11)||text.equals(Constant.region12)||text.equals(Constant.region13))){
                user.setRegion(text);
                userRepository.save(user);
                return order(update,"");
            }
            else{
                user.setRegion(text);
                userRepository.save(user);
            }
        }
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup.setSelective(true);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardButton button=new KeyboardButton();

        if (language.equals(Constant.nameRu)){
            builder.append(Constant.sendPhoneRu);
            button.setText(Constant.phoneRu);
        }
        else {
            builder.append(Constant.sendPhoneUz);
            button.setText(Constant.phoneUz);
        }
        button.setRequestContact(true);
        row.add(button);
        list.add(row);
        markup.setKeyboard(list);
        return SendMessage.builder().text(String.valueOf(builder)).chatId(String.valueOf(update.getMessage().getChatId())).replyMarkup(markup).build();
    }

    @Override
    public SendMessage acceptAll(Update update,String phone) throws IOException {
        Long chatId = update.getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User users = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
//        String language = map.get(chatId);
//        Optional<User> optional = userRepository.findByChatId(chatId);
//        if (optional.isPresent()){
//            User user1 = optional.get();
//            if (phone.equals("")){
//                    user1.setPhone(update.getMessage().getContact().getPhoneNumber());
//            }
//            else user1.setPhone(phone);
//            userRepository.save(user1);
//            return order(update,"");
//        }
        if (users!=null&&users.getPhone()!=null){
            if (phone.equals("")){
                users.setPhone(update.getMessage().getContact().getPhoneNumber());
            }
            else users.setPhone(phone);
            userRepository.save(users);
            return order(update,"");
        }
        else {
//            User user=new User();
            Set<String> messageList=new HashSet<>();
            messageList.add(language);
            if (phone.equals("")){
                users.setPhone(update.getMessage().getContact().getPhoneNumber());}
            else users.setPhone(phone);
            users.setMessageList(messageList);
//            users.setChatId(chatId);
            userRepository.save(users);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            List<InlineKeyboardButton> row = new ArrayList<>();
            List<InlineKeyboardButton> row1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            String text="";
            if (language.equals(Constant.nameRu)){
                inlineKeyboardButton.setText(Constant.readRulesRu);
                inlineKeyboardButton.setCallbackData("read");
                inlineKeyboardButton1.setText(Constant.acceptanceRu);
                inlineKeyboardButton1.setCallbackData("acceptance");
                text="Прочитайте правила, чтобы продолжить";
            }
            else {
                inlineKeyboardButton.setText(Constant.readRulesUz);
                inlineKeyboardButton.setCallbackData("read");
                inlineKeyboardButton1.setText(Constant.acceptanceUz);
                inlineKeyboardButton1.setCallbackData("acceptance");
                text="Davom etish uchun qoidalarni o'qing";
            }
            row.add(inlineKeyboardButton);
            row1.add(inlineKeyboardButton1);
            markup.setKeyboard(new ArrayList<>(Arrays.asList(row,row1)));
            return SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId())).replyMarkup(markup).text(text).build();
        }

    }

    @Override
    public SendMessage read(Update update) throws IOException {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row=new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton=new InlineKeyboardButton();
        User user = userRepository.findByChatId(chatId).orElseThrow(() -> new RuntimeException("Not found"));
        Set<String> messageList = user.getMessageList();
        String text="";
        for (String message : messageList) {
            if (message.equals(Constant.nameRu)||message.equals(Constant.nameUz)){
                if (message.equals(Constant.nameRu)) {
                    inlineKeyboardButton.setText(Constant.acceptanceRu);
                    text="Это условия использования этого бота\n------------";
                }
                else {
                    inlineKeyboardButton.setText(Constant.acceptanceUz);
                    text="Bu botdan foydalanish qoidalari\n------------";
                }
                break;
            }
        }
        inlineKeyboardButton.setCallbackData("acceptance");
        row.add(inlineKeyboardButton);
        rowList.add(row);
        markup.setKeyboard(rowList);
        return SendMessage.builder().text(text).chatId(String.valueOf(chatId)).replyMarkup(markup).build();
    }

    @Override
    public SendMessage order(Update update,String order) throws IOException {
        Long userChatId =0L;
        User user=new User();
        if (update.hasCallbackQuery()){
            userChatId = update.getCallbackQuery().getMessage().getChatId();
            Optional<User> optionalUser = userRepository.findByChatId(userChatId);
            if (optionalUser.isPresent()){
                user =optionalUser.get();
            }
        }
        else{
            userChatId = update.getMessage().getChatId();
            Optional<User> optionalUser = userRepository.findByChatId(userChatId);
            if (optionalUser.isPresent()){
                user =optionalUser.get();
                Set<String> messageList = user.getMessageList();
                if (!update.getMessage().hasContact()){
                    String text = update.getMessage().getText();
                    if (text.equals(Constant.nameRu)||text.equals(Constant.nameUz)){
                        messageList.add(text);
                    }
                }
            }
        }
        ReplyKeyboardMarkup markup1=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup1.setOneTimeKeyboard(true);
        markup1.setSelective(true);
        markup1.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();
        KeyboardButton button=new KeyboardButton();
        KeyboardButton button1=new KeyboardButton();
        KeyboardButton button2=new KeyboardButton();
        KeyboardButton button3=new KeyboardButton();
        KeyboardButton button4=new KeyboardButton();
        for (String message : user.getMessageList()) {
            if (message.equals(Constant.nameRu)||message.equals(Constant.nameUz)){
                if (message.equals(Constant.nameRu)){
                    button.setText(Constant.buyurtmaRu);
                    button1.setText(Constant.buyurtmalarimRu);
                    button2.setText(Constant.chegirmalarRu);
                    button3.setText(Constant.fikrBildirishRu);
                    button4.setText(Constant.sozlamalarRu);
                    if (order.equals("")){
                        order="Главное меню";
                    }
                }
                else {
                    button.setText(Constant.buyurtmaUz);
                    button1.setText(Constant.buyurtmalarimUz);
                    button2.setText(Constant.chegirmalarUz);
                    button3.setText(Constant.fikrBildirishUz);
                    button4.setText(Constant.sozlamalarUz);
                    if (order.equals("")){
                        order="Asosiy menyu";
                    }
                }
                break;
            }
        }

        row.add(button);
        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        list.add(row);
        list.add(row1);
        list.add(row2);
        markup1.setKeyboard(list);
        if (update.hasCallbackQuery()){
            return SendMessage.builder().text(order).chatId(String.valueOf(userChatId)).replyMarkup(markup1).build();
        }
        else {
            return SendMessage.builder().text(order).chatId(String.valueOf(userChatId)).replyMarkup(markup1).build();
        }
    }

    @Override
    public SendMessage orderSave(Update update) throws IOException {
        String payType = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Order order = orderRepository.findByUser_ChatIdAndOrderStatus(chatId, null);
        order.setPayType(PayType.valueOf(payType.substring(0,payType.lastIndexOf(".text"))));
        if (order.getUser().getReliabilty()>=3) {
            order.setOrderStatus(OrderStatus.APPROVED);
        }
        else order.setOrderStatus(OrderStatus.NEW);
        Order save = orderRepository.save(order);
        List<Detail> detailList = detailRepository.findAllByUser_ChatIdAndOrder_OrderStatus(chatId, null);
        for (Detail detail : detailList) {
            detail.setOrder(save);
            detailRepository.save(detail);
        }
//        Optional<User> optional = userRepository.findByChatId(update.getCallbackQuery().getMessage().getChatId());
//        String language="";
//        if (optional.isPresent()){
//            User user = optional.get();
//            for (String message : user.getMessageList()) {
//                if (message.equals(Constant.nameRu)||message.equals(Constant.nameUz)){
//                    language=message;
//                    break;
//                }
//            }
//        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        if (!language.equals(Constant.nameRu)){
            return order(update,"Buyurtma qabul qilindi");
        }
        else return order(update,"Заказ принят");
    }

    //TODO category
    @Override
    public SendMessage parentCategory(Update update) {
        Long chatId=0L;

        if (update.hasCallbackQuery()){
            chatId=update.getCallbackQuery().getMessage().getChatId();
        }
        else{
            chatId=update.getMessage().getChatId();
        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();

        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getParent()==null) {
                List<InlineKeyboardButton> row=new ArrayList<>();
                InlineKeyboardButton button=new InlineKeyboardButton();
                if (language.equals(Constant.nameRu)){
                    button.setText(categories.get(i).getNameRu());
                }
                else button.setText(categories.get(i).getNameUz());
                button.setCallbackData("#category"+categories.get(i).getId());
                for (int j = i+1; j < categories.size(); j++) {
                    if (categories.get(j).getParent()==null) {
                        InlineKeyboardButton button1=new InlineKeyboardButton();
                        if (language.equals(Constant.nameRu)){
                            button1.setText(categories.get(j).getNameRu());
                        }
                        else button1.setText(categories.get(j).getNameUz());
                        button1.setCallbackData("#category"+categories.get(j).getId());
                        row.add(button1);
                        i=j;
                        break;
                    }
                }
                row.add(button);
                rowList.add(row);
            }
        }
        List<InlineKeyboardButton> row=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        String text="";
        if (language.equals(Constant.nameRu)){
            button.setText(Constant.savatchaRu);
            text="Сделаем заказ вместе? \uD83E\uDD17";
        }
        else {
            button.setText(Constant.savatchaUz);
            text="Buyurtmani birga joylashtiramizmi? \uD83E\uDD17";
        }
        button.setCallbackData("#savatcha");
        row.add(button);
        rowList.add(row);
        markup.setKeyboard(rowList);
        Set<String> set=new HashSet<>();
        set.add(language);
        user.setMessageList(set);
        userRepository.save(user);
        return SendMessage.builder().text(text)
                .chatId(String.valueOf(chatId)).replyMarkup(markup)
                .build();
    }

    @Override
    public SendMessage category(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        Long id= Long.parseLong(update.getCallbackQuery().getData().substring(9));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not found"));
        List<Category> categories = categoryRepository.findAllByParent_Id(id);
            for (int i = 0; i < categories.size(); i++) {
                List<InlineKeyboardButton> row=new ArrayList<>();
                InlineKeyboardButton button=new InlineKeyboardButton();
                if (language.equals(Constant.nameRu)){
                    button.setText(categories.get(i).getNameRu());
                }
                else button.setText(categories.get(i).getNameUz());
                button.setCallbackData("*category"+categories.get(i).getId());
                if((i+1)<categories.size()){
                    InlineKeyboardButton button1=new InlineKeyboardButton();
                    if (language.equals(Constant.nameRu)){
                        button1.setText(categories.get(i+1).getNameUz());
                    }
                    else button1.setText(categories.get(i+1).getNameUz());
                    button1.setCallbackData("*category"+categories.get(i+1).getId());
                    row.add(button1);
                    i=i+1;
                }
                row.add(button);
                rowList.add(row);
            }
        List<InlineKeyboardButton> row=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        Set<String> messageList = user.getMessageList();
        messageList.add(update.getCallbackQuery().getData());
        String text="";
        if (language.equals(Constant.nameRu)){
            button.setText(Constant.backRu);
            text=category.getNameRu();
        }
        else {
            button.setText(Constant.backUz);
            text=category.getNameUz();
        }
        button.setCallbackData("parentCategory");
        row.add(button);
        rowList.add(row);
        user.setMessageList(messageList);
        userRepository.save(user);
        markup.setKeyboard(rowList);
        return SendMessage.builder().replyMarkup(markup)
                .text(text)
                .chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendMessage productList(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String back="";
        String menyu="";
        String word="";
        String data = update.getCallbackQuery().getData();
        Long id= Long.parseLong(data.substring(9));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not found"));
        List<Product> productList = productRepository.findAllByCategory_Id(id);
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            List<InlineKeyboardButton> row=new ArrayList<>();
            InlineKeyboardButton button=new InlineKeyboardButton();
            if (language.equals(Constant.nameRu)){
                button.setText(productList.get(i).getNameRu());
                back=Constant.backRu;
                menyu=Constant.menyuRu;
                word=category.getNameRu();
            }
            else{
                button.setText(productList.get(i).getNameUz());
                back=Constant.backUz;
                menyu=Constant.menyuUz;
                word=category.getNameUz();
            }

            button.setCallbackData("#product"+productList.get(i).getId()+"l"+data);
            if((i+1)<productList.size()){
                InlineKeyboardButton button1=new InlineKeyboardButton();
                if (language.equals(Constant.nameRu)){
                    button1.setText(productList.get(i+1).getNameRu());
                }
                else button1.setText(productList.get(i+1).getNameUz());
                button1.setCallbackData("#product"+productList.get(i+1).getId()+"l"+data);
                row.add(button1);
                i+=1;
            }
            row.add(button);
            rowList.add(row);
        }
        Set<String> messageList = user.getMessageList();
        List<InlineKeyboardButton> row=new ArrayList<>();
        List<InlineKeyboardButton> row1=new ArrayList<>();
        messageList.add(data);
        InlineKeyboardButton button=new InlineKeyboardButton();
        boolean result=true;
        for (String message : messageList) {
            if (message.startsWith("*category")) {
                for (String text : messageList) {
                    if (text.startsWith("#category")){
                        button.setText(back);
                        button.setCallbackData(text);
                        row.add(button);
                        InlineKeyboardButton button1=new InlineKeyboardButton();
                        button1.setText("❌");
                        button1.setCallbackData(Constant.buyurtmaUz);
                        row1.add(button1);
                        rowList.add(row);
                        rowList.add(row1);
                        break;
                    }
                }
                result=false;
                break;
            }
        }
        if (result){
                    button.setText(back);
                    button.setCallbackData("parentCategory");
                    row.add(button);
                    rowList.add(row);
        }
        user.setMessageList(messageList);
        userRepository.save(user);
        markup.setKeyboard(rowList);
        return SendMessage.builder().replyMarkup(markup)
                .text(word+"           ")
                .chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendPhoto product(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String data = update.getCallbackQuery().getData();
        String back=data.substring(data.lastIndexOf("l")+1);
        data=data.substring(0,data.lastIndexOf("l"));
        Long id= Long.parseLong(data.substring(8));
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        String url = product.getPhoto().getUrl();
        StringBuilder builders=new StringBuilder();
        String word="",menyu="",text="",text1="",text2="",text3="",text4="",text5="", productName="";
        if (language.equals(Constant.nameRu)){
            menyu=Constant.menyuRu;
            word=Constant.backRu;
            text=Constant.textRu;
            text1=Constant.productPriceRu;
            text2=Constant.discountPriceRu;
            text3=Constant.priceRu;
            text4=Constant.chooseQuantityRu;
            text5=Constant.quantityRu;
            productName=product.getNameRu();
        }
        else {
            menyu=Constant.menyuUz;
            word=Constant.backUz;
            text=Constant.textUz;
            text1=Constant.productPriceUz;
            text2=Constant.discountPriceUz;
            text3=Constant.priceUz;
            text4=Constant.chooseQuantityUz;
            text5=Constant.quantityUz;
            productName=product.getNameUz();
        }
        builders.append(text+productName+"\n");
        if (product.getDiscount()!=null){
            builders.append(text1+product.getPrice()+"\n");
            builders.append(text2+product.getPrice()*product.getDiscount().getPercentage()/100.0+"\n");
        }
        else {
            builders.append(text3+product.getPrice()+"\n");
        }
        builders.append(text4);
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> row5=new ArrayList<>();
        InlineKeyboardButton button5=new InlineKeyboardButton();
        button5.setText(text5+": 0");
        button5.setCallbackData("empty");
        row5.add(button5);
        rowList.add(row5);
        for (int i = 1; i < 10; i+=3) {
            List<InlineKeyboardButton> row=new ArrayList<>();
            InlineKeyboardButton button=new InlineKeyboardButton();
            button.setText(""+i);
            button.setCallbackData("quantity"+id+"x"+i+"l"+back);
            InlineKeyboardButton button1=new InlineKeyboardButton();
            button1.setText(""+(i+1));
            button1.setCallbackData("quantity"+id+"x"+(i+1)+"l"+back);
            InlineKeyboardButton button2=new InlineKeyboardButton();
            button2.setText(""+(i+2));
            button2.setCallbackData("quantity"+id+"x"+(i+2)+"l"+back);
            row.add(button);
            row.add(button1);
            row.add(button2);
            rowList.add(row);
        }
//        List<InlineKeyboardButton> row7=new ArrayList<>();
//        InlineKeyboardButton button7=new InlineKeyboardButton();
//        button7.setText(Constant.delete);
//        button7.setCallbackData("empty.text");
//        row7.add(button7);
//        rowList.add(row7);
//        List<InlineKeyboardButton> row=new ArrayList<>();
//        InlineKeyboardButton button=new InlineKeyboardButton();
//        button.setText(Constant.saveSavatcha);
//        button.setCallbackData("empty.text");
//        row.add(button);
//        rowList.add(row);
        List<InlineKeyboardButton> rows=new ArrayList<>();
        List<InlineKeyboardButton> row1=new ArrayList<>();
        InlineKeyboardButton buttons=new InlineKeyboardButton();
        buttons.setText(word);
        buttons.setCallbackData(back);
        rows.add(buttons);
        InlineKeyboardButton button1=new InlineKeyboardButton();
//        button1.setText(menyu);
        button1.setText("❌");
        button1.setCallbackData(Constant.buyurtmaUz);
        row1.add(button1);
        rowList.add(rows);
        rowList.add(row1);
        markup.setKeyboard(rowList);
        SendPhoto sendPhoto=new SendPhoto();
        return SendPhoto.builder()
                .photo(new InputFile(new File(url)))
                .caption(String.valueOf(builders))
                .chatId(String.valueOf(chatId))
                .replyMarkup(markup)
                .build();
    }

    @Override
    public SendMessage saveDetail(Update update){
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String data = update.getCallbackQuery().getData();
        update.getCallbackQuery().getData();
        Integer quantity=Integer.parseInt(data.substring(data.lastIndexOf("x")+1));
        Long id=Long.parseLong(data.substring(9,data.lastIndexOf("x")));
        Detail detail2 = detailRepository.findByProduct_IdAndOrder(id,null);
        if (detail2!=null){
            int quantity1 = detail2.getQuantity();
            quantity1+=quantity;
            detail2.setQuantity(quantity1);
            detailRepository.save(detail2);
        }
        else {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            Detail detail=new Detail();
            detail.setProduct(product);
            detail.setUser(user);
            detail.setQuantity(quantity);
            detailRepository.save(detail);
        }
        return detail(update,user,language);
    }

    @Override
    public SendMessage detailList(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        return detail(update,user,language);
    }

    @Override
    public SendMessage delivery(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> row=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        List<InlineKeyboardButton> rows=new ArrayList<>();
        InlineKeyboardButton buttons=new InlineKeyboardButton();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String text="";
        if (language.equals(Constant.nameRu)){
            button.setText(Constant.DELIVERYRu);
            buttons.setText(Constant.SELFRu);
            text=Constant.chooseOrderTypeRu;
        }
        else {
            button.setText(Constant.DELIVERYUz);
            buttons.setText(Constant.SELFUz);
            text=Constant.chooseOrderTypeUz;
        }
        button.setCallbackData("DELIVERY.type");
        buttons.setCallbackData("SELF.type");
        row.add(button);
        rowList.add(row);
        rows.add(buttons);
        rowList.add(rows);
        markup.setKeyboard(rowList);
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .replyMarkup(markup)
                .text(text).build();
    }

    @Override
    public SendMessage location(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String delivery = update.getCallbackQuery().getData();
        String str="";
        Order order=new Order();
        order.setUser(user);
            if (delivery.length()>9){
                order.setOrderType(OrderType.DELIVERY);
                if (language.equals(Constant.nameRu)){
                    str="Отправить место для доставки";
                }
                else {
                    str="Yetkazib berish uchun locatsiya jo'nating";
                }
            }
            else{
                if (language.equals(Constant.nameRu)){
                    str="Отправьте нам свое местоположение, чтобы мы могли предоставить вам наш ближайший филиал";
                }
                else {
                    str="Sizga eng yaqin filialimizni taqdim etishimiz uchun locatsiya jo'nating  ";
                }
                order.setOrderType(OrderType.SELF);
            }
            orderRepository.save(order);
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup.setSelective(true);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardButton button=new KeyboardButton();
        if (language.equals(Constant.nameRu)){
            button.setText(Constant.locationRu);
        }else button.setText(Constant.locationUz);
        button.setRequestLocation(true);
        row.add(button);
        list.add(row);
        markup.setKeyboard(list);
        return SendMessage.builder()
                .text(str)
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .replyMarkup(markup)
                .build();
    }

    @Override
    public SendMessage payType(Update update) {
        Long chatId=update.getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        Double latitude = update.getMessage().getLocation().getLatitude();
        Double longitude = update.getMessage().getLocation().getLongitude();
        Address address=new Address();
        address.setLat(latitude);
        address.setLon(longitude);
        address.setUser(user);
        Address save = addressRepository.save(address);
        StringBuilder builder=new StringBuilder();
        Order order = orderRepository.findByUser_ChatIdAndOrderStatus(chatId, null);
        List<Detail> details = detailRepository.findAllByUser_ChatIdAndOrder_OrderStatus(user.getChatId(), null);
        String currency="",amount="",paymentAmount="",salePercentage,deliveryPrice="",payType="";
        if (language.equals(Constant.nameRu)){
            amount=Constant.totalAmountRu;
            paymentAmount=Constant.paymentAmountRu;
            salePercentage=Constant.saleRu;
            deliveryPrice=Constant.deliverPriceRu;
            payType=Constant.payTypeRu;
            currency=Constant.currencyRu;
        }
        else {
            amount=Constant.totalAmountUz;
            paymentAmount=Constant.paymentAmountUz;
            salePercentage=Constant.saleUz;
            deliveryPrice=Constant.deliverPriceUz;
            payType=Constant.payTypeUz;
            currency=Constant.currencyUz;
        }
        double summa=0;
        for (Detail detail1 : details) {
            if (language.equals(Constant.nameRu)){
                builder.append(detail1.getProduct().getNameRu()+"\n");
            }else builder.append(detail1.getProduct().getNameUz()+"\n");
            double price=0;
            if (detail1.getProduct().getDiscount()!=null){
                price=detail1.getProduct().getPrice();
                double discount=detail1.getProduct().getDiscount().getPercentage();
                double discountPrice=price*discount/100.0;
                price-=discountPrice;
                builder.append(detail1.getQuantity()+" x "+price+" = "+detail1.getQuantity()*price+currency+" \n");
            }else{
                price=detail1.getProduct().getPrice();
                builder.append(detail1.getQuantity()+" x "+price+" = "+detail1.getQuantity()*price+currency+" \n");
            }
            summa+=detail1.getQuantity()*price;
        }
        List<Sale> sales=saleRepository.findAll();
        Double foiz=0D;
        if (!sales.isEmpty()){
            for (int i = 0; i < sales.size()-1; i++) {
                Sale sale = sales.get(i);
                if (sale.getSumma()<=summa){
                    foiz=sale.getFoiz();
                }
            }
        }
        int price=0;
        if (String.valueOf(order.getOrderType()).equals("DELIVERY")){
            price=(int)(Math.random()*10+10);
        }
        price=price*1000;

        builder.append("\n"+amount+summa+currency+" \n");
        if (price!=0){
            builder.append(deliveryPrice+price*1000+" \n");
        }
        builder.append(salePercentage+foiz+"% \n");
        builder.append(paymentAmount+(price+(summa-summa*foiz/100.0))+currency+" \n");
        order.setSumma(summa-summa*foiz/100.0+price);
        order.setAddress(save);
        orderRepository.save(order);
        builder.append(payType+" \n");
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> row=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        button.setText(String.valueOf(PayType.CLICK));
        button.setCallbackData("PAYNET.text");
        row.add(button);
        rowList.add(row);
        List<InlineKeyboardButton> row1=new ArrayList<>();
        InlineKeyboardButton button1=new InlineKeyboardButton();
        button1.setText(String.valueOf(PayType.CLICK));
        button1.setCallbackData("CLICK.text");
        row1.add(button1);
        rowList.add(row1);
        List<InlineKeyboardButton> row2=new ArrayList<>();
        InlineKeyboardButton button2=new InlineKeyboardButton();
        button2.setText(String.valueOf(PayType.PAYME));
        button2.setCallbackData("PAYME.text");
        row2.add(button2);
        rowList.add(row2);
        List<InlineKeyboardButton> row3=new ArrayList<>();
        InlineKeyboardButton button3=new InlineKeyboardButton();
        button3.setText("NAQD");
        button3.setCallbackData("NAQD.text");
        row3.add(button3);
        rowList.add(row3);
        markup.setKeyboard(rowList);
        return SendMessage.builder().
                text(String.valueOf(builder))
                .chatId(String.valueOf(chatId))
                .replyMarkup(markup).build();
    }

    @Override
    public SendMessage discount(Update update) {
        Long chatId=0L;
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        StringBuilder builder=new StringBuilder();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        int id=0;
        if (update.hasCallbackQuery()) {
            chatId= update.getCallbackQuery().getMessage().getChatId();
            id=Integer.parseInt(update.getCallbackQuery().getData().substring(4));
        }
        else {
            discountList=discountRepository.findAll();
            chatId= update.getMessage().getChatId();
        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        if (!discountList.isEmpty()){
            String discountName="",name="",productPrice="",discountPrice="",back="",next="";
            if (language.equals(Constant.nameRu)){
                name=Constant.productNameRu;
                productPrice=Constant.priceRu;
                discountPrice=Constant.discountPriceRu;
                back=Constant.backRu;
                next=Constant.nextRu;
                discountName=Constant.discountRu;
            }
            else {
                name=Constant.productNameUz;
                productPrice=Constant.priceUz;
                discountPrice=Constant.discountPriceUz;
                back=Constant.backUz;
                next=Constant.nextUz;
                discountName=Constant.discountUz;
            }
            builder.append(discountName+" \n");
            List<Product> productList = productRepository.findAllByDiscount_Id(discountList.get(id).getId());
            if (productList!=null){
                for (Product product :productList) {
                        Double price = product.getPrice();
                        price-=price*product.getDiscount().getPercentage()/100.0;
                        String productName="";
                        if (language.equals(Constant.nameRu)){
                            productName=product.getNameRu();
                        }else productName=product.getNameUz();
                        builder.append(name+productName+"\n"+productPrice+product.getPrice()+discountPrice+price+"\n");
                }
            }
            List<InlineKeyboardButton> row=new ArrayList<>();
            List<InlineKeyboardButton> row1=new ArrayList<>();
            if (id>0){
                InlineKeyboardButton button1=new InlineKeyboardButton();
                button1.setText(back);
                button1.setCallbackData("next"+(id-1));
                row.add(button1);
                InlineKeyboardButton button2=new InlineKeyboardButton();
                button2.setText("❌");
                button2.setCallbackData("back");
                row1.add(button2);
            }
            else {
                InlineKeyboardButton button1=new InlineKeyboardButton();
                button1.setText("❌");
                button1.setCallbackData("back");
                row1.add(button1);
            }
            if ((id+1)<discountList.size()) {
                InlineKeyboardButton button=new InlineKeyboardButton();
                button.setText(next);
                button.setCallbackData("next"+(id+1));
                row.add(button);
            }
            rowList.add(row);
            rowList.add(row1);
            markup.setKeyboard(rowList);
            return SendMessage.builder().replyMarkup(markup)
                    .text(String.valueOf(builder)).chatId(String.valueOf(chatId)).build();
        }
        return SendMessage.builder().text("empty").chatId(String.valueOf(chatId)).build();
    }

    @Override
    public DeleteMessage delete(Update update) {
        return DeleteMessage.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .build();
    }

    @Override
    public EditMessageReplyMarkup edit(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String data = update.getCallbackQuery().getData();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String back="";
        Integer quantity=0;
        if (data.endsWith("del")){
            data=data.substring(0,data.length()-3);
            back=data.substring(data.lastIndexOf("l")+1);
            data=data.substring(0,data.lastIndexOf("l")-1);
            if (data.lastIndexOf("x")==data.length()-1){
                quantity=0;
            } else quantity=Integer.parseInt(data.substring(data.lastIndexOf("x")+1));
        }
        else {
            back=data.substring(data.lastIndexOf("l")+1);
            data=data.substring(0,data.lastIndexOf("l"));
            quantity=Integer.parseInt(data.substring(data.lastIndexOf("x")+1));
        }
        String menyu="",text="",saveSavatcha="",delete="",word="";
        if (language.equals(Constant.nameRu)){
            menyu=Constant.menyuRu;
            text=Constant.textRu;
            saveSavatcha=Constant.saveSavatchaRu;
            delete=Constant.deleteRu;
            word=Constant.backRu;
        }
        else {
            menyu=Constant.menyuUz;
            text=Constant.textUz;
            saveSavatcha=Constant.saveSavatchaUz;
            delete=Constant.deleteUz;
            word=Constant.backUz;
        }
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> rows=new ArrayList<>();
        InlineKeyboardButton buttons=new InlineKeyboardButton();

        buttons.setText(text+quantity);
                buttons.setCallbackData(data);
                buttons.setCallbackData("empty");
                rows.add(buttons);
                rowList.add(rows);
        for (int i = 1; i < 10; i+=3) {
            List<InlineKeyboardButton> row=new ArrayList<>();
            InlineKeyboardButton button=new InlineKeyboardButton();
            button.setText(""+i);
            button.setCallbackData(data+i+"l"+back);
            InlineKeyboardButton button1=new InlineKeyboardButton();
            button1.setText(""+(i+1));
            button1.setCallbackData(data+(i+1)+"l"+back);
            InlineKeyboardButton button2=new InlineKeyboardButton();
            button2.setText(""+(i+2));
            button2.setCallbackData(data+(i+2)+"l"+back);
            row.add(button);
            row.add(button1);
            row.add(button2);
            rowList.add(row);
        }
        if (quantity!=0){
            List<InlineKeyboardButton> row1=new ArrayList<>();
            InlineKeyboardButton button1=new InlineKeyboardButton();
            button1.setText("0");
            button1.setCallbackData(data+"0"+"l"+back);
            row1.add(button1);
            InlineKeyboardButton button2=new InlineKeyboardButton();
            button2.setText(delete);
            button2.setCallbackData(data+"l"+back+"del");
            row1.add(button2);
            rowList.add(row1);
            List<InlineKeyboardButton> row=new ArrayList<>();
            InlineKeyboardButton button=new InlineKeyboardButton();
            button.setText(saveSavatcha);
            button.setCallbackData("#"+data);
            row.add(button);
            rowList.add(row);
        }
        List<InlineKeyboardButton> row4=new ArrayList<>();
        List<InlineKeyboardButton> row5=new ArrayList<>();
        InlineKeyboardButton button4=new InlineKeyboardButton();
        button4.setText(word);
        button4.setCallbackData(back);
        row4.add(button4);
        InlineKeyboardButton button1=new InlineKeyboardButton();
        button1.setText("❌");
        button1.setCallbackData(Constant.buyurtmaUz);
        row5.add(button1);
        rowList.add(row4);
        rowList.add(row5);
        markup.setKeyboard(rowList);
        return EditMessageReplyMarkup.builder()
                .chatId(String.valueOf(chatId))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(markup)
                .build();
    }

    @Override
    public SendMessage deleteDetail(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        Long id=Long.parseLong(update.getCallbackQuery().getData().substring(6));
        detailRepository.deleteById(id);
        StringBuilder builder=new StringBuilder();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        SendMessage detail = detail(update, user, language);
        if (!detail.getText().equals("Savatcha bo'm bo'sh")){
            return detail(update,user,language);
        }
        else {
            return parentCategory(update);
        }
    }

    @Override
    public SendMessage getAllOrder(Update update) {
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        int id=0;
        Long chatId=0L;
        if (update.hasCallbackQuery()){
           id=Integer.parseInt(update.getCallbackQuery().getData().substring(9));
           chatId= update.getCallbackQuery().getMessage().getChatId();
        }
        else {
           chatId=update.getMessage().getChatId();
        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        orderList = orderRepository.findByUser_ChatId(chatId);
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderStatus()==null){
                orderList.remove(i);
            }
        }
        System.out.println(language);
        double summa=0;
        if (!orderList.isEmpty()){
            String currency="",orders="",orderId="",status="",orderType="",payType="",products="",salePercentage="",deliveryPrice="",total="",back="",next="";

            if (language.equals(Constant.nameRu)) {
                orders=Constant.orderRu;
                orderId=Constant.orderIdRu;
                orderType=Constant.orderTypeRu;
                payType=Constant.orderPayTypeRu;
                products=Constant.productsRu;
                salePercentage=Constant.saleRu;
                deliveryPrice=Constant.deliverPriceRu;
                total=Constant.totalRu;
                back=Constant.backRu;
                status=Constant.statusRu;
                next=Constant.nextRu;
                currency=Constant.currencyRu;
            }
            else {
                orders=Constant.orderUz;
                orderId=Constant.orderIdUz;
                orderType=Constant.orderTypeUz;
                payType=Constant.orderPayTypeUz;
                products=Constant.productsUz;
                salePercentage=Constant.saleUz;
                deliveryPrice=Constant.deliverPriceUz;
                total=Constant.totalUz;
                back=Constant.backUz;
                status=Constant.statusUz;
                next=Constant.nextUz;
                currency=Constant.currencyUz;
            }
            builder.append(orders+"\n");
            Order order = orderList.get(id);
            builder.append(orderId+" "+order.getId()+"\n");
            builder.append(status+" "+order.getOrderStatus()+"\n");
            builder.append(orderType+" "+order.getOrderType()+"\n");
            List<Detail> detailList = detailRepository.findByLevelBetween(order);
            for (Detail detail1 : detailList) {
                if (language.equals(Constant.nameRu)){
                    builder.append(detail1.getProduct().getNameRu()+"\n");
                }else builder.append(detail1.getProduct().getNameUz()+"\n");
                double price=0;
                if (detail1.getProduct().getDiscount()!=null){
                     price=detail1.getProduct().getPrice();
                    double discount=detail1.getProduct().getDiscount().getPercentage();
                    double discountPrice=price*discount/100.0;
                    price-=discountPrice;

                    builder.append(detail1.getQuantity()+" x "+price+" = "+detail1.getQuantity()*price+currency+"\n");
                }else{
                    price=detail1.getProduct().getPrice();
                    builder.append(detail1.getQuantity()+" x "+price+" = "+detail1.getQuantity()*price+currency+"\n");
                }
                summa+=detail1.getQuantity()*price;
            }
            builder.append(payType+" "+order.getPayType()+"\n");
            List<Sale> sales=saleRepository.findAll();
            Double foiz=0D;
            if (!sales.isEmpty()){
                for (int i1 = 0; i1 < sales.size()-1; i1++) {
                    Sale sale = sales.get(i1);
                    if (sale.getSumma()<=summa){
                        foiz=sale.getFoiz();
                    }
                }
            }
            Double orderSumma = order.getSumma();

            builder.append(products+" "+" "+summa+currency+"\n");
            summa-=summa*foiz/100.0;
            if (String.valueOf(order.getOrderType()).length()>4){
                builder.append(deliveryPrice+" "+(orderSumma-summa)+currency+"\n");
            }
            if (foiz!=0){
                builder.append(salePercentage+" "+foiz+"%\n");
            }
            builder.append(total+" "+orderSumma+currency+"\n");
            List<InlineKeyboardButton> row1=new ArrayList<>();
            List<InlineKeyboardButton> row2=new ArrayList<>();
            if (id>0){
                InlineKeyboardButton button1=new InlineKeyboardButton();
                button1.setText(back);
                button1.setCallbackData("nextOrder"+(id-1));
                row1.add(button1);
                InlineKeyboardButton button2=new InlineKeyboardButton();
                button2.setText(" ❌ ");
                button2.setCallbackData("back");
                row2.add(button2);

            }
            else {
                InlineKeyboardButton button1=new InlineKeyboardButton();
                button1.setText(" ❌ ");
                button1.setCallbackData("back");
                row2.add(button1);
            }
            if (orderList.size()>(id+1)){
                InlineKeyboardButton button=new InlineKeyboardButton();
                button.setText(next);
                button.setCallbackData("nextOrder"+(id+1));
                row1.add(button);
            }
            rowList.add(row1);
            rowList.add(row2);
            markup.setKeyboard(rowList);
            return SendMessage.builder().text(String.valueOf(builder))
                    .chatId(String.valueOf(chatId))
                    .replyMarkup(markup).build();
        }
        return SendMessage.builder().text("empty")
                .chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendMessage sozlamalar(Update update) {
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> row=new ArrayList<>();
        List<InlineKeyboardButton> row1=new ArrayList<>();
        List<InlineKeyboardButton> row2=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        InlineKeyboardButton button1=new InlineKeyboardButton();
        InlineKeyboardButton button2=new InlineKeyboardButton();
        InlineKeyboardButton button3=new InlineKeyboardButton();
        StringBuilder builder=new StringBuilder();
        Long chatId = update.getMessage().getChatId();
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        if (language.equals(Constant.nameRu)) {
            builder.append(Constant.numberRu+": "+user.getPhone()+"\n");
            builder.append(Constant.regionRu+": "+user.getRegion()+"\n");
            builder.append(Constant.communicationLanguageRu+": "+language+"\n");
            button.setText(Constant.changeLanguageRu);
            button1.setText(Constant.numberRu);
            button2.setText(Constant.changeRegionRu);
        }
        else {
            builder.append(Constant.numberUz+": "+user.getPhone()+"\n");
            builder.append(Constant.regionUz+": "+user.getRegion()+"\n");
            builder.append(Constant.communicationLanguageUz+": "+language+"\n");
            button.setText(Constant.changeLanguageUz);
            button1.setText(Constant.numberUz);
            button2.setText(Constant.changeRegionUz);
        }
        button3.setText("   ❌   ");
        button.setCallbackData(Constant.changeLanguageUz);
        button1.setCallbackData(Constant.numberUz);
        button2.setCallbackData(Constant.changeRegionUz);
        button3.setCallbackData("back");
        row.add(button);
        row.add(button1);
        row1.add(button2);
        row2.add(button3);
        rowList.add(row);
        rowList.add(row1);
        rowList.add(row2);
        markup.setKeyboard(rowList);
        return SendMessage.builder()
                .replyMarkup(markup).chatId(String.valueOf(chatId))
                .text(String.valueOf(builder)).build();
    }

    @Override
    public SendMessage changeLanguage(Update update) {
        ReplyKeyboardMarkup markup=new ReplyKeyboardMarkup();
        List<KeyboardRow> list=new ArrayList<>();
        markup.setSelective(true);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        KeyboardRow row=new KeyboardRow();
        KeyboardRow row1=new KeyboardRow();
        String data = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        KeyboardButton button=new KeyboardButton();
        KeyboardButton button1=new KeyboardButton();
        String text="";
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        if (data.equals(Constant.changeRegionUz)){
            return region(update,language);
        }
        else if (data.equals(Constant.numberUz)){
            if (language.equals(Constant.nameRu)) {
                text="Отправьте номер как +998************.";
                button.setText(Constant.phoneRu);
            }
            else {
                text="Raqamni +998********* shaklida yuboring";
                button.setText(Constant.phoneUz);
            }
            button.setRequestContact(true);
            row.add(button);
            list.add(row);
        }else {
            if (language.equals(Constant.nameRu)) {
                text="Выберите язык";
            }
            else text="Tilni tanlang";
            button.setText(Constant.nameUz);
            button1.setText(Constant.nameRu);
            row.add(button);
            row1.add(button1);
            list.add(row);
            list.add(row1);
        }
        markup.setKeyboard(list);
        return SendMessage.builder().text(text).replyMarkup(markup).chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendMessage support(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        StringBuilder builder=new StringBuilder();
//        String language ="";
//        Optional<User> optionalUser = userRepository.findByChatId(chatId);
//        User user=new User();
//        if (optionalUser.isPresent()){
//            user =optionalUser.get();
//        }
//        Set<String> messageList = user.getMessageList();
//        for (String messages :messageList ) {
//            if (messages.equals(Constant.nameRu)||messages.equals(Constant.nameUz)){
//                language=messages;
//                break;
//            }
//        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        Set<String> messageList = user.getMessageList();
        messageList.add(Constant.supportUz);
        messageList.add(update.getCallbackQuery().getData());
        user.setMessageList(messageList);
        userRepository.save(user);
        if (language.equals(Constant.nameRu)){
            builder.append(Constant.supportRu);
        }
        else {
            builder.append(Constant.supportUz);
        }

        return SendMessage.builder().text(String.valueOf(builder))
                .chatId(String.valueOf(chatId)).build();
    }

    @Override
    public SendMessage supportSave(Update update,User user) throws IOException {
        String text = update.getMessage().getText();
        String data="";
        String language="";
        for (String messages : user.getMessageList()) {
            if (messages.equals(Constant.offerUz)||messages.equals(Constant.complaintUz)){
                data=messages;
            } else if (messages.equals(Constant.nameUz)||messages.equals(Constant.nameRu)) {
                language=messages;
            }
        }
        Set<String> messageList=new HashSet<>();
        messageList.add(language);
        user.setMessageList(messageList);
        userRepository.save(user);
        Support support=new Support();
        Set<String> phone=new HashSet<>();
        phone.add(user.getPhone());
        support.setPhoneList(phone);
        if (data.equals("Taklif")){
            support.setSupportType(SupportType.OFFER);}
        else support.setSupportType(SupportType.COMPLAINT);
        support.setDescription(text);
        supportRepository.save(support);
        return order(update,"");
    }


    @Override
    public SendMessage supportType(Update update) {
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        List<InlineKeyboardButton> row=new ArrayList<>();
        List<InlineKeyboardButton> row1=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton();
        InlineKeyboardButton button1=new InlineKeyboardButton();
        InlineKeyboardButton button2=new InlineKeyboardButton();
        Long chatId = update.getMessage().getChatId();
//        String language ="";
//        Optional<User> optionalUser = userRepository.findByChatId(chatId);
//        User user=new User();
//        if (optionalUser.isPresent()){
//            user =optionalUser.get();
//        }
//        Set<String> messageList = user.getMessageList();
//        for (String messages :messageList ) {
//            if (messages.equals(Constant.nameRu)||messages.equals(Constant.nameUz)){
//                language=messages;
//                break;
//            }
//        }
        UserDtoForBot userDtoForBot = languageDetection(update, chatId);
        User user = userDtoForBot.getUser();
        String language = userDtoForBot.getLanguage();
        String text="";
        if (language.equals(Constant.nameRu)) {
            text=Constant.supportTypeRu;
            button.setText(Constant.complaintRu);
            button1.setText(Constant.offerRu);
        }
        else {
            text=Constant.supportTypeUz;
            button.setText(Constant.complaintUz);
            button1.setText(Constant.offerUz);
        }
        button2.setText("   ❌   ");
        button.setCallbackData(Constant.complaintUz);
        button1.setCallbackData(Constant.offerUz);
        button2.setCallbackData("back");
        row.add(button);
        row.add(button1);
        row1.add(button2);
        rowList.add(row);
        rowList.add(row1);
        markup.setKeyboard(rowList);
        return SendMessage.builder()
                .replyMarkup(markup).chatId(String.valueOf(chatId))
                .text(text).build();
    }

    @Override
    public SendInvoice payClick(Update update) {
        SendInvoice sendInvoice = new SendInvoice();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String language ="";
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        User user=new User();
        if (optionalUser.isPresent()){
            user =optionalUser.get();
            Set<String> messageList = user.getMessageList();
            for (String messages :messageList ) {
                if (messages.equals(Constant.nameRu)||messages.equals(Constant.nameUz)){
                    language=messages;
                    break;
                }
            }
            Order order = orderRepository.findByUser_ChatIdAndOrderStatus(user.getChatId(), null);
//            order.setOrderStatus(OrderStatus.NEW);
            orderRepository.save(order);
            if (language.equals(Constant.nameUz)) {
                sendInvoice.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChat().getId()));
                sendInvoice.setTitle("To'lov");
                sendInvoice.setDescription("\nTo'lovni amalga oshiring.");
                sendInvoice.setPayload(order.getId().toString());
            } else {
                sendInvoice.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChat().getId().intValue()));
                sendInvoice.setTitle("Оплата");
                sendInvoice.setDescription("\nСделать оплату.");
                sendInvoice.setPayload(order.getId().toString());
            }
            List<LabeledPrice> labeledPrices = new ArrayList<>();
            Double amount = order.getSumma();
            Integer integer = amount.intValue();
            labeledPrices.add(new LabeledPrice("label1", integer * 100));
            sendInvoice.setProviderToken(providerTokenClick); //test kluch
            sendInvoice.setStartParameter("busycube");
            sendInvoice.setCurrency("UZS");
            sendInvoice.setPayload(order.getId().toString());
            sendInvoice.setPrices(labeledPrices);
        }
        return sendInvoice;
    }

    @Override
    public SendMessage detail(Update update,User user,String language) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String currency = "", amount = "", paymentAmount = "", salePercentage, buyurtmaniTasdiqlash = "", deleteDetails = "", buyurtmaQoshish = "";

        if (language.equals(Constant.nameRu)) {
            builder.append(Constant.savatchaRu + "\n");
            amount = Constant.totalAmountRu;
            paymentAmount = Constant.paymentAmountRu;
            salePercentage = Constant.saleRu;
            buyurtmaniTasdiqlash = Constant.buyurtmaniTasdiqlashRu;
            deleteDetails = Constant.deleteDetailRu;
            buyurtmaQoshish = Constant.buyurtmaQoshishRu;
            currency = Constant.currencyRu;
        } else {
            builder.append(Constant.savatchaUz + "\n");
            amount = Constant.totalAmountUz;
            paymentAmount = Constant.paymentAmountUz;
            salePercentage = Constant.saleUz;
            buyurtmaniTasdiqlash = Constant.buyurtmaniTasdiqlashUz;
            deleteDetails = Constant.deleteDetailUz;
            buyurtmaQoshish = Constant.buyurtmaQoshishUz;
            currency = Constant.currencyUz;
        }
        double summa = 0;
        boolean result=true;
        List<Detail> detailList = detailRepository.findAllByUser_ChatIdAndOrder_OrderStatus(user.getChatId(), null);
        if (!detailList.isEmpty()) {
            result=false;
            for (Detail detail1 : detailList) {
                String productName = "";
                if (language.equals(Constant.nameRu)) {
                    productName = detail1.getProduct().getNameRu();
                } else {
                    productName = detail1.getProduct().getNameUz();
                }
                builder.append(productName + "\n");
                double price = 0;
                if (detail1.getProduct().getDiscount() != null) {
                    price = detail1.getProduct().getPrice();
                    double discount = detail1.getProduct().getDiscount().getPercentage();
                    double discountPrice = price * discount / 100.0;
                    price -= discountPrice;
                    builder.append(detail1.getQuantity() + " x " + price + " = " + detail1.getQuantity() * price + currency + " \n");
                } else {
                    price = detail1.getProduct().getPrice();
                    builder.append(detail1.getQuantity() + " x " + detail1.getProduct().getPrice() + " = " + detail1.getQuantity() * detail1.getProduct().getPrice() + currency + "\n");
                }
                InlineKeyboardButton buttons = new InlineKeyboardButton();
                buttons.setText("❎ " + productName);
                buttons.setCallbackData("delete" + detail1.getId());
                List<InlineKeyboardButton> rows = new ArrayList<>();
                rows.add(buttons);
                rowList.add(rows);
                summa += detail1.getQuantity() * price;
            }
            List<Sale> sales = saleRepository.findAll();
            Double foiz = 0D;
            if (!sales.isEmpty()) {
                for (int i = 0; i < sales.size() - 1; i++) {
                    Sale sale = sales.get(i);
                    if ((sale.getSumma() <= summa)) {
                        foiz = sale.getFoiz();
                    }
                }
            }

            builder.append("\n " + amount + summa + currency + " \n")
                    .append(salePercentage + foiz + "% \n")
                    .append(paymentAmount + (summa - summa * foiz / 100.0) + currency + " \n");

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buyurtmaniTasdiqlash);
            button.setCallbackData("orderConfirmation");
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            rowList.add(row);
            List<InlineKeyboardButton> rows = new ArrayList<>();
            InlineKeyboardButton buttons = new InlineKeyboardButton();
            buttons.setText(buyurtmaQoshish);
            buttons.setCallbackData("parentCategory");
            rows.add(buttons);
            rowList.add(rows);
            List<InlineKeyboardButton> row1 = new ArrayList<>();
            InlineKeyboardButton button2 = new InlineKeyboardButton();
            button2.setText(deleteDetails);
            button2.setCallbackData("deleteDetail");
            row1.add(button2);
            rowList.add(row1);
            markup.setKeyboard(rowList);
        }
        if (result){
            return SendMessage.builder().text("Savatcha bo'm bo'sh")
                    .chatId(String.valueOf(chatId))
                    .build();
        }
        else {
            return SendMessage.builder().text(String.valueOf(builder))
                    .chatId(String.valueOf(chatId))
                    .replyMarkup(markup)
                    .build();
        }
    }

    @Override
    public SendMessage checkPhoneNumber(Update update, User user) throws IOException {
        String language="";
        StringBuilder builder=new StringBuilder();
        if (user!=null){
            for (String message : user.getMessageList()) {
                if (message.equals(Constant.nameRu)||message.equals(Constant.nameUz)){
                    language=message;
                    break;
                }
            }
        }
        else language=Constant.nameUz;

        if (language.equals(Constant.nameRu)){
            builder.append("Номер телефона должен состоять из 13 цифр.");
        }else{
            builder.append("Telefon nomer 13 ta raqamdan iborat bo'lishi kerak");
        }
        return sendPhone(update, String.valueOf(builder));
    }

    @Override
    public UserDtoForBot languageDetection(Update update,Long chatId) {
        String language ="";
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        User user=new User();
        if (optionalUser.isPresent()){
            user =optionalUser.get();
            for (String messages : user.getMessageList()) {
                if (messages.equals(Constant.nameRu)||messages.equals(Constant.nameUz)){
                    language=messages;
                    break;
                }
            }
        }
        else {
            language=Constant.nameUz;
        }
        return new UserDtoForBot(user,language);
    }

    @Override
    public SendMessage sendOrderStatus(Order order) {
        User user = order.getUser();
        String language ="";
        StringBuilder builder=new StringBuilder();
        for (String messages : user.getMessageList()) {
               if (messages.equals(Constant.nameRu)||messages.equals(Constant.nameUz)){
                   language=messages;
                   break;
               }
        }
        if (language.equals(Constant.nameRu)){
           builder.append(Constant.orderIdRu+ order.getId()+"\n");
           builder.append(Constant.statusRu+ order.getOrderStatus());
        }
        else {
            builder.append(Constant.orderIdUz+ order.getId()+"\n");
            builder.append(Constant.statusUz+ order.getOrderStatus());
        }
        return SendMessage.builder().chatId(String.valueOf(user.getChatId()))
                .text(String.valueOf(builder))
                .build();
    }
}
