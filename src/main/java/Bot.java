import cache.UsersCache;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import sticker.Stickers;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Random;


public class Bot extends TelegramLongPollingBot {
    private  boolean firstMessage = false;
    private int currentDay = new GregorianCalendar().getTime().getDay();

    private int currentDayMem = new GregorianCalendar().getTime().getDay();
    private String currentPidor;
    private int bratCounter = 0;

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String message = update.getMessage().getText();
        addUserDataToCache(update);
        daPizda(message, update);
        mem(update);
        final Long chatId = update.getMessage().getChatId();
        switch (message) {
            case "/pidor": {
                showPidorDay(update);
            }
            case "/brat": {
                if (bratCounter == 1) {
                    bratCounter = 0;
                    SendSticker sticker = new SendSticker();
                    sticker.setChatId(update.getMessage().getChatId());
                    sticker.setSticker(new InputFile(Stickers.GACHI.getStickerId()));
                    try {
                        execute(sticker);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    bratCounter++;
                }
            }
        }

    }
    private void mem(Update update){
        if(currentDayMem != new GregorianCalendar().getTime().getDay()){
            currentDayMem = new GregorianCalendar().getTime().getDay();
            SendSticker sticker = new SendSticker();
            sticker.setChatId(update.getMessage().getChatId());
            sticker.setSticker(new InputFile(Stickers.EBAT.getStickerId()));
            try {
                execute(sticker);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void daPizda(String message, Update update) {
        if (Arrays.stream(message.split(" ")).filter(x -> x.equalsIgnoreCase("да")).findFirst().isPresent()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Пизда");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showPidorDay(Update update) {
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
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPidorFromCache() {
        final int randomUserIndex = new Random().nextInt(UsersCache.getCacheSize());
        return UsersCache.getUserNameByIndex(randomUserIndex);
    }

    private void addUserDataToCache(Update update) {
        final String userName = update.getMessage().getFrom().getUserName();
        if (userName.equals("Vlad_korzunov")) {
            return;
        }
        if (!UsersCache.userIsPresent(userName).isPresent()) {
            UsersCache.addUser(userName);
        }
    }

    @Override
    public String getBotUsername() {
        return "mrkBot";
    }

    @Override
    public String getBotToken() {
        return "5458626734:AAErADnNMVo-Aru7SCAWTq1ylcgwjjaj3p4";
    }
}
