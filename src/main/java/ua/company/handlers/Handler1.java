package ua.company.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.company.bot.BotState;
import ua.company.cache.DataCache;
import ua.company.model.Employee;
import ua.company.repository.EmployeeRepository;
import ua.company.service.ButtonsProvider;
import ua.company.service.ReplyMessageService;


@Component
public class Handler1 implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;
    private EmployeeRepository repository;

    public Handler1(ReplyMessageService messageService, DataCache dataCache, EmployeeRepository repository) {
        this.messageService = messageService;
        this.dataCache = dataCache;
        this.repository = repository;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonNext")){
            replyMessage.setText(messageService.getReplyText("reply.message2"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT2);
        }
        if (callbackQuery.getData().equals("buttonBack")){
            Employee registeredEmployee = repository.findByUserName(getEmployeeName(callbackQuery.getFrom()));
            if (registeredEmployee != null){
                replyMessage.setText(messageService.getReplyText("reply.greetingEmployee"));
                replyMessage.setReplyMarkup(ButtonsProvider.getButtonChangeDirectorate());
            }
            else {
                replyMessage.setText(messageService.getReplyText("reply.startApplication"));
                replyMessage.setReplyMarkup(ButtonsProvider.getButtonNext());
            }
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.START_APP);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP_TEXT1;
    }

    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
