package ua.company.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/*
* Different buttons layers for message handlers
* */

public class ButtonsProvider {
    public static InlineKeyboardMarkup getButtonNext(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonNext = new InlineKeyboardButton("Далі");
        buttonNext.setCallbackData("buttonNext");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(buttonNext);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsNextBack(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonNext = new InlineKeyboardButton("Далі");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        buttonNext.setCallbackData("buttonNext");
        buttonBack.setCallbackData("buttonBack");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(buttonNext);
        row.add(buttonBack);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsDirectorate(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonNext = new InlineKeyboardButton("Далі");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        InlineKeyboardButton buttonAffiliates = new InlineKeyboardButton("Вибір регіональної філії");
        InlineKeyboardButton buttonKyiv = new InlineKeyboardButton("Київ");
        buttonNext.setCallbackData("buttonNext");
        buttonBack.setCallbackData("buttonBack");
        buttonAffiliates.setCallbackData("buttonAffiliates");
        buttonKyiv.setCallbackData("buttonKyiv");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonNext);
        row1.add(buttonBack);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonAffiliates);
        row2.add(buttonKyiv);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        buttons.add(row2);

        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsChangeDirectorate(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonAffiliates = new InlineKeyboardButton("Вибір регіональної філії");
        InlineKeyboardButton buttonKyiv = new InlineKeyboardButton("Київ");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        buttonAffiliates.setCallbackData("buttonAffiliates");
        buttonKyiv.setCallbackData("buttonKyiv");
        buttonBack.setCallbackData("buttonBack");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonAffiliates);
        row1.add(buttonKyiv);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonBack);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        buttons.add(row2);

        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsKyiv(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonKMD = new InlineKeyboardButton("КМД");
        InlineKeyboardButton buttonCAU = new InlineKeyboardButton("ЦАУ");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        buttonKMD.setCallbackData("buttonKMD");
        buttonCAU.setCallbackData("buttonCAU");
        buttonBack.setCallbackData("buttonBack");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonKMD);
        row1.add(buttonCAU);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonBack);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        buttons.add(row2);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsAffiliates(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonAffiliate1 = new InlineKeyboardButton("Філія1");
        InlineKeyboardButton buttonAffiliate2 = new InlineKeyboardButton("Філія2");
        InlineKeyboardButton buttonAffiliate3 = new InlineKeyboardButton("Філія3");
        InlineKeyboardButton buttonAffiliate4 = new InlineKeyboardButton("Філія4");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        buttonAffiliate1.setCallbackData("affiliate1");
        buttonAffiliate2.setCallbackData("affiliate2");
        buttonAffiliate3.setCallbackData("affiliate3");
        buttonAffiliate4.setCallbackData("affiliate4");
        buttonBack.setCallbackData("buttonBack");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonAffiliate1);
        row1.add(buttonAffiliate2);
        row1.add(buttonAffiliate3);
        row1.add(buttonAffiliate4);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonBack);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        buttons.add(row2);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsDepartments(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonDepartment1 = new InlineKeyboardButton("Відділення1");
        InlineKeyboardButton buttonDepartment2 = new InlineKeyboardButton("Відділення2");
        InlineKeyboardButton buttonBack = new InlineKeyboardButton("Назад");
        buttonDepartment1.setCallbackData("department1");
        buttonDepartment2.setCallbackData("department2");
        buttonBack.setCallbackData("buttonBack");


        List<InlineKeyboardButton> row1= new ArrayList<>();
        row1.add(buttonDepartment1);
        row1.add(buttonDepartment2);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonBack);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        buttons.add(row2);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getButtonsNextBackStart(){
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

    public static InlineKeyboardMarkup getButtonChangeDirectorate(){
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonChangeDirectorate = new InlineKeyboardButton("Змінити дирекцію");
        InlineKeyboardButton buttonNext = new InlineKeyboardButton("Далі");
        buttonChangeDirectorate.setCallbackData("buttonChangeDirectorate");
        buttonNext.setCallbackData("buttonNext");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(buttonChangeDirectorate);
        row.add(buttonNext);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row);
        replyKeyboardMarkup.setKeyboard(buttons);
        return replyKeyboardMarkup;
    }
}
