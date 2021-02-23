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
public class ChooseAffiliateHandler implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;
    private EmployeeRepository repository;

    public ChooseAffiliateHandler(ReplyMessageService messageService, DataCache dataCache, EmployeeRepository repository) {
        this.messageService = messageService;
        this.dataCache = dataCache;
        this.repository = repository;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        String chosenAffiliate = callbackQuery.getData();
        SendMessage replyMessage = messageService.getReplyMessage(chatId, "reply.message3");
        replyMessage.setReplyMarkup(ButtonsProvider.getButtonsDirectorate());
        dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT3);

        // If database contains employee -> update this employee, else -> insert new one
        if (Affiliates.getAffiliates().containsKey(chosenAffiliate)) {
            Employee employee = repository.findByUserName(getEmployeeName(callbackQuery.getFrom()));
            if (employee != null) {
                employee.setDepartment(Affiliates.getAffiliates().get(chosenAffiliate));
                repository.save(employee);
            }
            else {
                employee = new Employee();
                employee.setUserName(getEmployeeName(callbackQuery.getFrom()));
                employee.setDepartment(Affiliates.getAffiliates().get(chosenAffiliate));
                repository.insert(employee);
            }

            replyMessage.setText(messageService.getReplyText("reply.message4"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT4);
        }

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CHOOSE_AFFILIATE;
    }

    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }

}
