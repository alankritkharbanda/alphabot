package telegramBotManager;

import alpha.QueryResponseBuilder;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Alankrit on 05-Feb-17.
 */
public class Alphabot extends TelegramLongPollingBot {
    public static String authKey = "300454626:AAE6dOKQG1TjIeNlj-enT-Rd10YHRpEi7NQ";
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText ;
            String messageReceived = update.getMessage().getText();
            if (update.getMessage().isCommand()) {
                if (messageReceived.contains("creator")) {
                    messageText = "It is I, Ser Alan, who created this bot.";
                } else if (messageReceived.contains("help")) {
                    messageText = "This is a wolfram query bot. Once you ping the bot, you can fetch directly the answer to your query. Eg. Who is Narendra Modi or Who killed Mahatma Gandhi";
                } else {
                    messageText = "No can't do baby doll. Valid commands only.";
                }
            } else {
                messageText = QueryResponseBuilder.getResponseForQueryAsString(update.getMessage().getText());
            }
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageText);
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "solutionbot";
    }

    public String getBotToken() {
        return authKey;
    }
}