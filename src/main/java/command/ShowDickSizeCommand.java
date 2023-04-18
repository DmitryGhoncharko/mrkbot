package command;

import entity.UserDick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.*;

public class ShowDickSizeCommand implements Command<SendMessage> {
    private static final Logger LOG  = LoggerFactory.getLogger(ShowDickSizeCommand.class);
    private static final List<UserDick> USER_DICKS = new ArrayList<>();
    private static int currentDayMem = new GregorianCalendar().getTime().getDay();

    @Override
    public SendMessage execute(Update update, Message... message) {
        return proceed(update);
    }

    private SendMessage proceed(Update update) {
        if (currentDayMem != new GregorianCalendar().getTime().getDay()) {
            currentDayMem = new GregorianCalendar().getTime().getDay();
            USER_DICKS.clear();
        }
        User user = update.getMessage().getFrom();
        String userName = user.getUserName();
        for (UserDick userDick : USER_DICKS) {
            if (userDick.getUserName().equals(userName)) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText(userDick.getDickMessage());
                return sendMessage;
            }
        }
        SendMessage sendMessage = new SendMessage();
        int randomPhraseNumber = new Random().nextInt(10);
        String firstPhrase = generateFirstPhrase(randomPhraseNumber);
        int sizeDick = new Random().nextInt(40);
        String dickMessage = "@" + userName + " " + firstPhrase + "у меня " + sizeDick + " см";
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(dickMessage);
        USER_DICKS.add(new UserDick(userName, dickMessage));
        LOG.info("Current day = " + currentDayMem);
        LOG.info("Real day = " + new GregorianCalendar().getTime().getDay());
        return sendMessage;

    }

    private String generateFirstPhrase(int random) {
        switch (random) {
            case 0:
                return "Большой палец ";
            case 1:
                return "Кабачок ";
            case 2:
                return "Пипидастр ";
            case 3:
                return "Колыбаха разрушения ";
            case 4:
                return "Волшебная палочка ";
            case 5:
                return "Джуниор младший ";
            case 6:
                return "Оксюморон ";
            case 7:
                return "Сосиска ";
            case 8:
                return "Пеннис ";
            case 9:
                return "Хуяра ";
            case 10:
                return "Болт ";
            default:
                return "Дамский сверчок ";
        }
    }
}
