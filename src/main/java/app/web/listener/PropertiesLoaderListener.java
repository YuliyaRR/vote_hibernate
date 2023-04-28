package app.web.listener;

import app.dao.db.orm.factory.ManagerSingleton;
import app.service.fabrics.MailServiceSingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        File confDir = new File(System.getenv("catalina_base") + "/conf");
        File prop = new File(confDir + "/applicationHome.properties");
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(prop));
            MailServiceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл с настройками не найден", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ManagerSingleton.getInstance().close();
        try {
            DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
