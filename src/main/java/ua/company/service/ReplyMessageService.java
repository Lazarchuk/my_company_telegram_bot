package ua.company.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Locale;

/*
* Provides the application with messages contained in messages.properties
* */

@Service
public class ReplyMessageService {

    private MessageSource messageSource;
    private Locale locale;

    public ReplyMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
        locale = Locale.forLanguageTag("ru-RU");
    }

    public SendMessage getReplyMessage(String chatId, String message){
        String replyMessage = messageSource.getMessage(message, null, locale);
        return new SendMessage(chatId, replyMessage);
    }

    public String getReplyText(String message){
        return messageSource.getMessage(message, null, locale);
    }

}
