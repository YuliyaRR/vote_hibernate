package app.service.fabrics;

import app.dao.provider.ChoiceDaoProvider;
import app.service.MailService;
import app.service.api.IMailService;

import java.util.Properties;

public class MailServiceSingleton {

    private static Properties properties;

    private volatile static IMailService instance;

    private MailServiceSingleton() {
    }

    public static void setProperties(Properties properties){
        synchronized (MailServiceSingleton.class){
            if (instance != null) {
                throw new IllegalStateException("Нельзя менять настройки, приложение уже запущено");
            }
            MailServiceSingleton.properties = properties;
        }
    }

    public static IMailService getInstance() {
        if (instance == null){
            synchronized (MailServiceSingleton.class){
                if(instance == null) {
                    instance = new MailService(properties,
                            ChoiceDaoProvider.getInstance().mailDao());

                }
            }
        }
        return instance;
    }
}
