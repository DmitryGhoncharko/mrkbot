package command;

import exception.MessageDontDaError;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;

public class DaPizdaCommand implements Command<SendMessage> {
    @Override
    public SendMessage execute(Update update, Message... message) {
        return proceed(update);
    }

    private SendMessage proceed(Update update) {
        String messageText = update.getMessage().getText();
        Integer messageId = update.getMessage().getMessageId();
        if (messageText != null && (messageText.trim().equalsIgnoreCase("да") || messageText.trim().equalsIgnoreCase("da"))) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Пизда");
            sendMessage.setReplyToMessageId(messageId);
            return sendMessage;
        }
        throw new MessageDontDaError("Message dont da");
    }
}
