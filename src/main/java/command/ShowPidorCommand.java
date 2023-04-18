package command;

import cache.UsersCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.GregorianCalendar;
import java.util.Random;

public class ShowPidorCommand implements Command<SendMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(ShowPidorCommand.class);
    private static String currentPidor;
    private static int currentDay = new GregorianCalendar().getTime().getDay();

    @Override
    public SendMessage execute(Update update, Message... message) {
        return proceed(update);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int randomIndex = new Random().nextInt(UsersCache.getCacheSize());
            System.out.println(randomIndex);
            System.out.println(UsersCache.getUserNameByIndex(randomIndex));
        }
    }
    private SendMessage proceed(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (currentPidor == null) {
            currentPidor = getPidorFromCache();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Ебучий пидор дня @" + currentPidor);
        } else if (currentDay != new GregorianCalendar().getTime().getDay()) {
            currentPidor = getPidorFromCache();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Ебучий пидор дня @" + currentPidor);
            currentDay = new GregorianCalendar().getTime().getDay();
        } else {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Ебучий пидор дня @" + currentPidor);
        }
        LOG.info("Current pidor = " + currentPidor);
        LOG.info("Current day = " + currentDay);
        LOG.info("Day now = " + new GregorianCalendar().getTime().getDay());
        return sendMessage;
    }

    private String getPidorFromCache() {
        final int randomUserIndex = new Random().nextInt(UsersCache.getCacheSize());
        LOG.info("Pidor from cache = "  + UsersCache.getUserNameByIndex(randomUserIndex));
        return UsersCache.getUserNameByIndex(randomUserIndex);
    }
}
