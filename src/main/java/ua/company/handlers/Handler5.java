package ua.company.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ua.company.bot.BotState;
import ua.company.cache.DataCache;
import ua.company.service.ButtonsProvider;
import ua.company.service.ReplyMessageService;


@Component
public class Handler5 implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;

    public Handler5(ReplyMessageService messageService, DataCache dataCache) {
        this.messageService = messageService;
        this.dataCache = dataCache;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonNext")){
            replyMessage.setText(messageService.getReplyText("reply.message6"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT6);
        }
        if (callbackQuery.getData().equals("buttonBack")){
            replyMessage.setText(messageService.getReplyText("reply.message4"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT4);
        }
        if (callbackQuery.getData().equals("buttonToStart")){
            replyMessage.setText(messageService.getReplyText("reply.message2"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT2);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP_TEXT5;
    }

}
