package ua.company.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.company.bot.BotState;
import ua.company.cache.DataCache;
import ua.company.model.Employee;
import ua.company.repository.EmployeeRepository;
import ua.company.service.ButtonsProvider;
import ua.company.service.ReplyMessageService;

@Component
public class StartAppHandler implements InputMessageHandler, InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;
    private EmployeeRepository repository;

    public StartAppHandler(ReplyMessageService messageService, DataCache dataCache, EmployeeRepository repository) {
        this.messageService = messageService;
        this.dataCache = dataCache;
        this.repository = repository;
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        String chatId = message.getChatId().toString();
        SendMessage replyMessage = null;

        Employee employee = repository.findByUserName(getEmployeeName(message.getFrom()));
        if (employee != null){
            replyMessage = messageService.getReplyMessage(chatId, "reply.greetingEmployee");
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonChangeDirectorate());
        }
        else {
            replyMessage = messageService.getReplyMessage(chatId, "reply.startApplication");
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonNext());
        }

        Employee registeredEmployee = repository.findByUserName(getEmployeeName(message.getFrom()));
        if (registeredEmployee != null){
            replyMessage.setText(messageService.getReplyText("reply.greetingEmployee"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonChangeDirectorate());
        }
        return replyMessage;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = null;

        Employee employee = repository.findByUserName(getEmployeeName(callbackQuery.getFrom()));
        if (employee != null){
            replyMessage = messageService.getReplyMessage(chatId, "reply.greetingEmployee");
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonChangeDirectorate());
        }
        else {
            replyMessage = messageService.getReplyMessage(chatId, "reply.startApplication");
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonNext());
        }

        if (callbackQuery.getData().equals("buttonNext")){
            replyMessage.setText(messageService.getReplyText("reply.message1"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT1);
        }
        if (callbackQuery.getData().equals("buttonChangeDirectorate")){
            replyMessage.setText(messageService.getReplyText("reply.changeDirectorate"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsChangeDirectorate());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.CHOOSE_DIRECTORATE);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START_APP;
    }

    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
