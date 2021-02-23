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
import ua.company.static_data.Affiliates;

@Component
public class ChooseDirectorateHandler implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;
    private EmployeeRepository repository;

    public ChooseDirectorateHandler(ReplyMessageService messageService, DataCache dataCache, EmployeeRepository repository) {
        this.messageService = messageService;
        this.dataCache = dataCache;
        this.repository = repository;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonAffiliates")){
            StringBuilder replyText = new StringBuilder(messageService.getReplyText("reply.chooseAffiliate"));
            for (String s: Affiliates.getAffiliates().values()) {
                replyText.append("\n- ").append(s);
            }
            replyMessage.setText(replyText.toString());
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsAffiliates());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.CHOOSE_AFFILIATE);
        }

        if (callbackQuery.getData().equals("buttonKyiv")){
            replyMessage.setText(messageService.getReplyText("reply.chooseDepartment"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsKyiv());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.CHOOSE_DEPARTMENT);
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
        return BotState.CHOOSE_DIRECTORATE;
    }

    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }

}
