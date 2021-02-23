package ua.company.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.company.bot.BotState;
import ua.company.cache.DataCache;
import ua.company.service.ReplyMessageService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Handler6 implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;

    public Handler6(ReplyMessageService messageService, DataCache dataCache) {
        this.messageService = messageService;
        this.dataCache = dataCache;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonNext")){
            return sendAnswerCallbackQuery(callbackQuery);
        }
        if (callbackQuery.getData().equals("buttonBack")){
            replyMessage.setText(messageService.getReplyText("reply.message5"));
            replyMessage.setReplyMarkup(getNavigationButtons());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT5);
        }

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP_TEXT6;
    }

    private InlineKeyboardMarkup getNavigationButtons(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonNext = new InlineKeyboardButton("Далі");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        InlineKeyboardButton buttonToStart = new InlineKeyboardButton("На початок");
        buttonNext.setCallbackData("buttonNext");
        buttonBack.setCallbackData("buttonBack");
        buttonToStart.setCallbackData("buttonToStart");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(buttonNext);
        row.add(buttonBack);
        row.add(buttonToStart);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(CallbackQuery callbackQuery){
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(true);
        answerCallbackQuery.setText("Функціонал кнопки в розробці");
        return answerCallbackQuery;
    }
}
