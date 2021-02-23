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
import ua.company.static_data.Departments;

@Component
public class ChooseDepartmentHandler implements InputCallbackQueryHandler {
    private ReplyMessageService messageService;
    private DataCache dataCache;
    private EmployeeRepository repository;

    public ChooseDepartmentHandler(ReplyMessageService messageService, DataCache dataCache, EmployeeRepository repository) {
        this.messageService = messageService;
        this.dataCache = dataCache;
        this.repository = repository;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage replyMessage = new SendMessage(chatId, "");

        if (callbackQuery.getData().equals("buttonKMD")){
            StringBuilder replyText = new StringBuilder(messageService.getReplyText("reply.chooseKMDDepartment"));
            for (String s: Departments.getDepartments().values()){
                replyText.append("\n- ").append(s);
            }
            replyMessage.setText(replyText.toString());
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsDepartments());
        }

        if (callbackQuery.getData().equals("buttonCAU")){
            Employee employee = repository.findByUserName(getEmployeeName(callbackQuery.getFrom()));

            // If database contains employee -> update this employee, else -> insert new one
            if (employee != null){
                employee.setDepartment(Departments.getAddressCAU());
                repository.save(employee);
            }
            else {
                employee = new Employee();
                employee.setUserName(getEmployeeName(callbackQuery.getFrom()));
                employee.setDepartment(Departments.getAddressCAU());
                repository.insert(employee);
            }

            replyMessage.setText(Departments.getAddressCAU());
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
        }

        if (callbackQuery.getData().equals("buttonNext")){
            replyMessage.setText(messageService.getReplyText("reply.message4"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT4);
        }

        if (callbackQuery.getData().equals("buttonBack")){
            replyMessage.setText(messageService.getReplyText("reply.message3"));
            replyMessage.setReplyMarkup(ButtonsProvider.getButtonsDirectorate());
            dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT3);
        }

        if (callbackQuery.getData().startsWith("department")){
            String chosenDepartment = callbackQuery.getData();

            // If database contains employee -> update this employee, else -> insert new one
            if (Departments.getDepartments().containsKey(chosenDepartment)){
                Employee employee = repository.findByUserName(getEmployeeName(callbackQuery.getFrom()));
                if (employee != null) {
                    employee.setDepartment(Departments.getDepartments().get(chosenDepartment));
                    repository.save(employee);
                }
                else {
                    employee = new Employee();
                    employee.setUserName(getEmployeeName(callbackQuery.getFrom()));
                    employee.setDepartment(Departments.getDepartments().get(chosenDepartment));
                    repository.insert(employee);
                }

                replyMessage.setText(messageService.getReplyText("reply.message4"));
                replyMessage.setReplyMarkup(ButtonsProvider.getButtonsNextBack());
                dataCache.setUserCurrentBotState(callbackQuery.getFrom().getId(), BotState.HELP_TEXT4);
            }
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CHOOSE_DEPARTMENT;
    }

    private String getEmployeeName(User user){
        String employeeName = user.getUserName();
        return employeeName != null? employeeName: String.format("%s %s", user.getFirstName(), user.getLastName());
    }

}
