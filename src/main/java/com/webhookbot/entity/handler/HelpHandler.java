package com.webhookbot.entity.handler;

import com.webhookbot.entity.BotMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@AllArgsConstructor
@Component
public class HelpHandler{
    private Message message;
    private final SendMessage sendMessage;
    private final BotMessages botMessages;

    public SendMessage handle(Update update) {
        message = update.getMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(botMessages.getRules());
        return sendMessage;
    }
}
