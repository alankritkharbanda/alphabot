package telegramBotManager;

/**
 * Created by Alankrit on 05-Feb-17.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Alankrit on 05-Feb-17.
 */
public class ContextManager implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextManager destroyed");
    }
    //Run this before web application is started
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextManager started");
        new Thread() {
            public void run() {
                TelegramDriver.main(null);
            }
        }.start();
    }
}

