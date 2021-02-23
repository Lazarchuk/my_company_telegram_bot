package ua.company.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.company.bot.BotState;

/*
* Handler is used only for handle text messages from users
* */
public interface InputMessageHandler {
    BotApiMethod<?> handle(Message message);
    BotState getHandlerName();
}
