package ua.company.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.company.cache.DataCache;

/*
* The class determines necessary bot state based on input message or callback query for current user
* */

@Component
@Slf4j
public class UpdatesDispatcher {

    private DataCache dataCache;
    private HandlersManager handlersManager;

    public UpdatesDispatcher(DataCache dataCache, HandlersManager handlersManager) {
        this.dataCache = dataCache;
        this.handlersManager = handlersManager;
    }

    public BotApiMethod<?> handleUpdate(Update update){
        BotApiMethod<?> replyMessage = null;

        if (update.hasCallbackQuery()){
            replyMessage = handleCallbackQuery(update.getCallbackQuery());
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()){
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    // Define bot state for input message
    private BotApiMethod<?> handleInputMessage(Message message){
        String inputMessage = message.getText();
        Integer userId = message.getFrom().getId();
        BotState botState;
        BotApiMethod<?> replyMessage;

        switch (inputMessage){
            case "/start":
                botState = BotState.START_APP;
                break;
            default:
                botState = dataCache.getUserCurrentBotState(userId);
                break;
        }

        if (botState == null){
            botState = BotState.START_APP;
        }

        log.info("New message \"{}\" from user \"{}\". Handler - {}", inputMessage, getEmployeeName(message.getFrom()), botState);
        dataCache.setUserCurrentBotState(userId, botState);
        replyMessage = handlersManager.processInputMessage(botState, message);
        return replyMessage;
    }

    // Define bot state for input callback query
    private BotApiMethod<?> handleCallbackQuery(CallbackQuery buttonQuery){
        Integer userId = buttonQuery.getFrom().getId();
        BotState botState = dataCache.getUserCurrentBotState(userId);
        BotApiMethod<?> replyMessage;

        if (botState == null){
            botState = BotState.START_APP;
        }

        log.info("Pressed button \"{}\" by user \"{}\". Handler - {}", buttonQuery.getData(), getEmployeeName(buttonQuery.getFrom()), botState);
        replyMessage = handlersManager.processInputCallbackQuery(botState, buttonQuery);
        return replyMessage;
    }


    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
