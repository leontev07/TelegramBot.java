package org.example.bot;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;
    Storage()
    {
        quoteList = new ArrayList<>();
        quoteList.add("Весь наш мир скатился в ад,\n" +
                "Это Ларин виноват \n\nТема Косов.");
        quoteList.add("Je ungeheuerlicher die Lüge ist, desto eher glaubt die Menge an sie.\n\nAdolf Hitler");
        quoteList.add("Идея наказать страну, которая обладает крупнейшим ядерным потенциалом, абсурдна сама по себе. И потенциально создаеёт угрозу существованию человечества.\n\nДмитрий Медведев");
    }

    String getRandQuote()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }
}
