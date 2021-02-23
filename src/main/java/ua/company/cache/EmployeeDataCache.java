package ua.company.cache;

import org.springframework.stereotype.Component;
import ua.company.bot.BotState;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeDataCache implements DataCache {
    private Map<Integer, BotState> userBotStates = new HashMap<>();

    @Override
    public void setUserCurrentBotState(Integer userId, BotState botState) {
        userBotStates.put(userId, botState);
    }

    @Override
    public BotState getUserCurrentBotState(Integer userId) {
        return userBotStates.get(userId);
    }
}
