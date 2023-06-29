package org.example.bot;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;
    Storage()
    {
        quoteList = new ArrayList<>();
        quoteList.add(
                "Если у вас заблокировалась учётка, её можно восстановить самостоятельно с помощью ADSelf Service Plus.\n"+
                "Для регистрации в ADSELF SERVICE PLUS необходимо воспользоваться инструкцией https://drive.google.com/file/d/1dbdgSBYT4GhRNzj3N-EwDWJmT9QdfMLY/view?usp=sharing\n"+
                "Инструкция по смене пароля - https://drive.google.com/file/d/1TTK_bxI9FO_VsKiptGl1VdU0uRrksgi4/view\n"+
                "Инструкция по разблокировке учётной записи - https://drive.google.com/file/d/1lrALI0ahCNs0sBqep-Wuw4UW_2UpWDbd/view");
        quoteList.add("Список ссылок для новичков - https://conf.goods.ru/pages/viewpage.action?pageId=259590143\n"+" \n"+
                "Инструкции по макросам Content_Helper - https://conf.goods.ru/pages/viewpage.action?pageId=217455969\n"+" \n"+
                "Инструкция по работе с тикетами https://conf.goods.ru/pages/viewpage.action?pageId=212313495\n"+" \n" +
                "Список формул Excel и их значения + лайфхаки https://conf.goods.ru/pages/viewpage.action?pageId=217448932");
        quoteList.add("Контакты контента - https://conf.goods.ru/pages/viewpage.action?pageId=264256730");
        quoteList.add("Напиши боту @smm_content_support_bot");
        quoteList.add("Список формул Excel и их значения + лайфхаки https://conf.goods.ru/pages/viewpage.action?pageId=217448932");
        quoteList.add("Примечания в PIM - Новый формат (Нидерландские, Немецкие, Испанские: ведение данных https://conf.goods.ru/pages/viewpage.action?pageId=242028936");
    }

    String getInstruction()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = 1;
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }
    String getLink()
    {
        int value = 0;
        return quoteList.get(value);
    }
    String getContact()
    {
        int value = 2;
        return quoteList.get(value);
    }
    String getBot() {
        int value = 3;
        return quoteList.get(value);
    }
    String getFormula() {
        int value = 4;
        return quoteList.get(value);
    }
    String getNotes() {
        int value = 5;
        return quoteList.get(value);}
}
