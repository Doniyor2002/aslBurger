package com.example.botservice.bot;

import com.example.botservice.entity.Detail;
import com.example.botservice.entity.User;
import com.example.botservice.repository.CategoryRepository;
import com.example.botservice.repository.DetailRepository;
import com.example.botservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    String username;
    @Value("${bot.token}")
    String token;
    private final TelegramServiceImp telegramServiceImp;
    private final CategoryRepository categoryRepository;
    private final DetailRepository detailRepository;
    private final UserRepository userRepository;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            if (update.getMessage().hasLocation()) {
                 execute(telegramServiceImp.payType(update));
               }
            else if(update.getMessage().hasContact()){
                execute(telegramServiceImp.acceptAll(update,""));
            }
//             NumberUtils.parseNumber(text.substring(1))
            else if (text.startsWith("+998")&&text.length()==13) {
                 execute(telegramServiceImp.acceptAll(update,update.getMessage().getText()));
            }
            else if (text.equals(Constant.chegirmalarRu)||text.equals(Constant.chegirmalarUz)) {
                SendMessage sendMessage1 = telegramServiceImp.discount(update);
                if (!sendMessage1.getText().equals("empty")) {
                    execute(sendMessage1);
                }
            }
            else if (text.equals("/start")) {
                Long chatId = update.getMessage().getChatId();
                Optional<User> optional = userRepository.findByChatId(chatId);
                if (optional.isPresent()){
                    execute(telegramServiceImp.order(update,""));
                }
                else execute(telegramServiceImp.start(update));
            }
            else if (text.equals(Constant.nameUz)||text.equals(Constant.nameRu)) {
                Long chatId = update.getMessage().getChatId();
                Optional<User> optional = userRepository.findByChatId(chatId);
                if (optional.isPresent()){
                    User user = optional.get();
                    if (!user.getPhone().equals("")){
                        Set<String> message=new HashSet<>();
                        if (text.equals(Constant.nameRu)){
                            message.add(Constant.nameRu);
                        }
                        else message.add(Constant.nameUz);
                        user.setMessageList(message);
                        userRepository.save(user);
                        execute(telegramServiceImp.order(update,""));
                    }
                }
                else execute(telegramServiceImp.region(update,""));
            }
            else if (text.equals(Constant.region)||text.equals(Constant.region1)||text.equals(Constant.region2)||text.equals(Constant.region3)||text.equals(Constant.region4)
                    ||text.equals(Constant.region5)|| text.equals(Constant.region6)||text.equals(Constant.region7)||text.equals(Constant.region8)||text.equals(Constant.region9)
                    ||text.equals(Constant.region10)||text.equals(Constant.region11)||text.equals(Constant.region12)||text.equals(Constant.region13)) {
                execute(telegramServiceImp.sendPhone(update,""));
            }

            else if (userRepository.findByChatId(update.getMessage().getChatId()).get().getRegion()==null) {
                execute(telegramServiceImp.sendPhone(update,""));
            }

            else if (text.equals(Constant.buyurtmaUz)||text.equals(Constant.buyurtmaRu)) {
                execute(telegramServiceImp.parentCategory(update));
            }
            else if (text.equals(Constant.sozlamalarUz)||text.equals(Constant.sozlamalarRu)) {
                execute(telegramServiceImp.sozlamalar(update));
            }
            else if (text.equals(Constant.buyurtmalarimUz)||text.equals(Constant.buyurtmalarimRu)) {
                SendMessage sendMessage = telegramServiceImp.getAllOrder(update);
                if (!sendMessage.getText().equals("empty")){
                    execute(sendMessage);
                }
            }
            else if (text.equals(Constant.fikrBildirishUz)||text.equals(Constant.fikrBildirishRu)) {
                execute(telegramServiceImp.supportType(update));
            }
            else {
                Long chatId = update.getMessage().getChatId();
                Optional<User> optional = userRepository.findByChatId(chatId);
                boolean result1=true;
                if (optional.isPresent()){
                    User user = optional.get();
                    for (String message : user.getMessageList()) {
                        if (message.equals(Constant.supportUz)){
                            result1=false;
                           execute(telegramServiceImp.supportSave(update,user));
                            break;
                        }
                    }
                }
                if (result1){
                    execute(telegramServiceImp.checkPhoneNumber(update,optional.get()));
//                    execute(telegramServiceImp.sendPhone(update));
                }
            }
        }
        else if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if (data.equals("empty")){
            } else if (data.equals("parentCategory")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.parentCategory(update));
            } else if (data.startsWith("nextOrder")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.getAllOrder(update));
            }
            else if (data.equals(Constant.numberUz)||data.equals(Constant.changeLanguageUz)||data.equals(Constant.changeRegionUz)) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.changeLanguage(update));}
            else if (data.equals(Constant.offerUz)||data.equals(Constant.complaintUz)) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.support(update));}
            else  if (data.equals("#savatcha")){
                if (!telegramServiceImp.detailList(update).getText().equals("Savatcha bo'm bo'sh")) {
                    execute(telegramServiceImp.delete(update));
                    execute(telegramServiceImp.detailList(update));
                }
           }
           else if (data.startsWith("quantity")){
               execute(telegramServiceImp.edit(update));}
           else if (data.startsWith("#product")){
               execute(telegramServiceImp.delete(update));
               execute(telegramServiceImp.product(update));
                }
           else if (data.equals(Constant.buyurtmaUz)||data.equals(Constant.buyurtmaRu)){
                execute(telegramServiceImp.delete(update));
               execute(telegramServiceImp.parentCategory(update));}
           else if (data.startsWith("next")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.discount(update));}
           else if (data.equals("deleteDetail")) {
                execute(telegramServiceImp.delete(update));
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                List<Detail> detailList = detailRepository.findAllByUser_ChatIdAndOrder_OrderStatus(chatId, null);
                for (Detail detail : detailList) {
                    detailRepository.deleteById(detail.getId());
                }
                execute(telegramServiceImp.parentCategory(update));}
           else if (data.equals("orderConfirmation")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.delivery(update));}
           else if (data.equals("read")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.read(update));}
           else if (data.equals("acceptance")||data.equals("back")) {
                execute(telegramServiceImp.delete(update));
                execute(telegramServiceImp.order(update,""));}
           else if (data.endsWith(".text")){
                execute(telegramServiceImp.delete(update));
               if (!data.startsWith("NAQD")){
                   execute(telegramServiceImp.payClick(update));}
                execute(telegramServiceImp.orderSave(update));
           }
           else if (data.endsWith(".type")) {
                        execute(telegramServiceImp.location(update));}
           else if (data.startsWith("#quantity")){
                            execute(telegramServiceImp.delete(update));
                            execute(telegramServiceImp.saveDetail(update));}
           else if (data.startsWith("delete")) {
                            execute(telegramServiceImp.delete(update));
                            execute(telegramServiceImp.deleteDetail(update));}
           else if (categoryRepository.findAllByParent_Id(Long.parseLong(data.substring(9))).isEmpty()) {
                            execute(telegramServiceImp.delete(update));
                            execute(telegramServiceImp.productList(update));}
           else if (data.startsWith("*category")){
                            execute(telegramServiceImp.delete(update));
                            execute(telegramServiceImp.productList(update));}
           else if (data.startsWith("#category")){
                            execute(telegramServiceImp.delete(update));
                            execute(telegramServiceImp.category(update));
           }
        }

    }
}