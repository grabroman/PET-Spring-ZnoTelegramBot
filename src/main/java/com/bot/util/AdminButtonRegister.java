package com.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class AdminButtonRegister {


    public static InlineKeyboardMarkup getAdminKeyboard(){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(getSingleButtonRow("Розіслати повідомлення усім користувачам",buildCallback("Send" ,"Розіслати")));

        keyboard.setKeyboard(rows);
        return keyboard;
    }

    public static InlineKeyboardMarkup getConfirmationKeyboard(){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(getSingleButtonRow("Відправити всім",buildCallback("Confirmed" ,"Відправити всім")));
        rows.add(getSingleButtonRow("Відмінити",buildCallback("Confirmed" ,"Відмінити")));

        keyboard.setKeyboard(rows);
        return keyboard;
    }

    protected static List<InlineKeyboardButton> getSingleButtonRow(String text, String callback){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callback);
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button);
        return row;
    }

    protected static String buildCallback(String ... callback){
        return String.join("/",callback);
    }

}
