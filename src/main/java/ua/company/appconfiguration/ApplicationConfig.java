package ua.company.appconfiguration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ua.company.bot.MyCompanyBot;
import ua.company.bot.UpdatesDispatcher;

@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class ApplicationConfig {
    private String botUserName;
    private String botPath;
    private String botToken;

    @Bean
    public MyCompanyBot engVocabularyBot(UpdatesDispatcher updatesDispatcher){
        DefaultBotOptions options = new DefaultBotOptions();
        MyCompanyBot bot = new MyCompanyBot(options, updatesDispatcher);
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setBotPath(botPath);
        return bot;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:messages");
        return messageSource;
    }
}
