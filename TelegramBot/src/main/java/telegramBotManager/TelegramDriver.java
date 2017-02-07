package telegramBotManager;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.servlet.ServletContextListener;

/**
 * Created by Alankrit on 05-Feb-17.
 */
public class TelegramDriver  implements ServletContextListener {
    public static void main(String args[]) {
        ApiContextInitializer.init();
        System.out.println("Init");
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Alphabot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
