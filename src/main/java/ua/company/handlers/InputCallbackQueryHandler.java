package ua.company.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ua.company.bot.BotState;

/*
* Handler is used in order to handle button events
* */
public interface InputCallbackQueryHandler {
    BotApiMethod<?> handle(CallbackQuery callbackQuery);
    BotState getHandlerName();
}
