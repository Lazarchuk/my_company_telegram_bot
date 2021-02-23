package ua.company.cache;

import ua.company.bot.BotState;

public interface DataCache {
    void setUserCurrentBotState(Integer userId, BotState botState);
    BotState getUserCurrentBotState(Integer userId);
}
