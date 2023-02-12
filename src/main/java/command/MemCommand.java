package command;

import exception.StikerWasSendTodayError;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import sticker.Stickers;

import java.util.GregorianCalendar;

public class MemCommand implements Command<SendSticker> {
    private static int currentDayMem = -1;

    @Override
    public SendSticker execute(Update update, Message... message) {
        return proceed(update);
    }

    private SendSticker proceed(Update update){
        if(currentDayMem != new GregorianCalendar().getTime().getDay()){
            currentDayMem = new GregorianCalendar().getTime().getDay();
            SendSticker sticker = new SendSticker();
            sticker.setChatId(update.getMessage().getChatId());
            sticker.setSticker(new InputFile(Stickers.EBAT.getStickerId()));
            sticker.setReplyToMessageId(update.getMessage().getMessageId());
            return sticker;
        }
        throw new StikerWasSendTodayError("Sticker was send today");
    }
}
