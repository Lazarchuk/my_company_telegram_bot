package ua.company.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ua.company.bot.BotState;
import ua.company.cache.DataCache;
import ua.company.service.ButtonsProvider;
import ua.company.service.ReplyMessageService;
import ua.company.static_data.Affiliates;

@Component
public class Handler3 implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;

    public Handler3(ReplyMessageService messageService, DataCache dataCache) {
        this.messageService = messageService;
        this.dataCache = dataCache;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonNext")) {
            replyMessage.setText(messageService.getReplyText("reply.message4"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT4);
        }
        if (callbackQuery.getData().equals("buttonBack")) {
            replyMessage.setText(messageService.getReplyText("reply.message2"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT2);
        }
        if (callbackQuery.getData().equals("buttonAffiliates")) {
            StringBuilder replyText = new StringBuilder(messageService.getReplyText("reply.chooseAffiliate"));
            for (String s : Affiliates.getAffiliates().values()) {
                replyText.append("\n- ").append(s);
            }
            replyMessage.setText(replyText.toString());
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsAffiliates());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.CHOOSE_AFFILIATE);
        }
        if (callbackQuery.getData().equals("buttonKyiv")) {
            replyMessage.setText(messageService.getReplyText("reply.chooseDepartment"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsKyiv());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.CHOOSE_DEPARTMENT);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP_TEXT3;
    }

}