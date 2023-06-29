package org.example.bot;

import lombok.NonNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    //создаем две константы, присваиваем им значения токена и имя бота соответсвтенно
    //вместо звездочек подставляйте свои данные
    final private String BOT_TOKEN = "5821570586:AAFGOBCKfM___b5NRhNCZr4cX0C_CCP6Grs";
    final private String BOT_NAME = "contentinstructions_bot";
    String userName;
    Storage storage;


    public TelegramBot()
    {
        storage = new Storage();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {


        try {
            if (update.hasMessage() && update.getMessage().hasText()) {

                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();

                //получаем username пользователя
                String username = update.getMessage().getFrom().getUserName();
                userName = username;
                //логируем в консоли какой пользователь юзал бота
                System.out.println(username);
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();



                //Добавляем в наше сообщение id чата а также наш ответ

                outMess.setChatId(chatId);
                outMess.setText(response);
                setButtons(outMess);
                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) {
        String response;





        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if(textMsg.equals("/start") || (textMsg.toLowerCase()).equals("старт") || (textMsg.toLowerCase()).equals("привет"))
            response = "Приветствую, "+userName+"\nЖми /instruction или Инструкции, чтобы получить ссылки на инструкции.\n" +
                    "Жми /account_blocking или Разблокировка для получения руководства при блокировке аккаунта. \n" +
                    "Жми /contacts или Контакты контента, чтобы посмотреть контакты сотрудников\n" +
                    "Жми /needhelp или Позови человека, далее перейди по ссылке и опиши проблему\n" +
                    "Жми /formulas или Формулы Excel, чтобы посмотреть полезнные формулы и лайфхаки\n" +
                    "Жми /notes или Примечания (новый формат), чтобы открыть инструкцию по примечаниям";

        //else if(textMsg.equals("/start") || textMsg.equals("Привет"))
           // response = storage.getRandQuote();
        else if(textMsg.equals("/instruction") || (textMsg.toLowerCase()).equals("инструкции")) {
            response = storage.getInstruction();
        }else if(textMsg.equals("/account_blocking") || (textMsg.toLowerCase()).equals("разблокировка"))
            response = storage.getLink();
        else if(textMsg.equals("/contacts") || (textMsg.toLowerCase()).equals("контакты контента"))
            response = storage.getContact();
        else if(textMsg.equals("/needhelp") || (textMsg.toLowerCase()).equals("позови человека"))
            response = storage.getBot();
        else if(textMsg.equals("/formulas") || (textMsg.toLowerCase()).equals("формулы excel"))
            response = storage.getFormula();
        else if(textMsg.equals("/notes") || (textMsg.toLowerCase()).equals("примечания (новый формат)"))
            response = storage.getNotes();
        else
            response = "Сообщение не распознано";


        return response;

    }
    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);



        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Инструкции"));
        keyboardFirstRow.add(new KeyboardButton("Позови человека"));
        keyboardFirstRow.add(new KeyboardButton("Примечания (новый формат)"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Разблокировка"));
        keyboardSecondRow.add(new KeyboardButton("Формулы Excel"));
        // Первая строчка клавиатуры
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardThirdRow.add(new KeyboardButton("Контакты контента"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}

