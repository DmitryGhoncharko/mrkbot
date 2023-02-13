import cache.UsersCache;
import command.Command;
import command.InitialContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Bot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(Bot.class);
    private final InitialContext initialContext = new InitialContext();
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
        if (message!=null){
            if(message.startsWith("/dell")){

                try {
                    String[] data = message.split(" ");
                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setChatId(data[0]);
                    deleteMessage.setMessageId(Integer.parseInt(data[1]));
                    execute(deleteMessage);
                } catch (Throwable e) {
                    LOG.info(e.getMessage());
                }
            }


            Command memCommand = initialContext.getCommandFromCache("memcmnd");
            Command daCommand = initialContext.getCommandFromCache("dacmnd");
            try{
                SendSticker sendMessageMem = (SendSticker) memCommand.execute(update);
                execute(sendMessageMem);
            }catch (Throwable e){
                LOG.info(e.getMessage());
            }
            try {
                SendMessage sendStickerDa = (SendMessage) daCommand.execute(update);
                execute(sendStickerDa);
            }catch (Throwable e){
                LOG.info(e.getMessage());
            }
            Command command = initialContext.getCommandFromCache(message);
            if(command!=null){
                try{
                    SendMessage sendMessage = (SendMessage) command.execute(update);
                    execute(sendMessage);
                }catch (Throwable e){
                    LOG.error(e.getMessage());
                }
            }
        }
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
        return "5458626734:AAHpVJq4pa3oo8E7t9qdqviWUyDQq2zlTuI";
    }
}
