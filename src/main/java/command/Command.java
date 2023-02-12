package command;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command<T> {
    T execute(Update update, Message ... message);
}
