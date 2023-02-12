package command;

import cache.UsersCache;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.GregorianCalendar;
import java.util.Random;

public class ShowPidorCommand implements Command<SendMessage> {
    private static String currentPidor;
    private static int currentDay = new GregorianCalendar().getTime().getDay();

    @Override
    public SendMessage execute(Update update, Message... message) {
        return proceed(update);
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
        return sendMessage;
    }

    private String getPidorFromCache() {
        final int randomUserIndex = new Random().nextInt(UsersCache.getCacheSize());
        return UsersCache.getUserNameByIndex(randomUserIndex);
    }
}
