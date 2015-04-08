package config;

import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Darya on 06.04.2015.
 */
public class DBUnitConfig extends DBTestCase {
    protected IDatabaseTester tester;           //объект, функционалом которого мы будем проводить сравнивание табличек и бд
    private Properties prop;                    //здесь мы будем хранить наши данные для БД. Настройки подключения
    protected IDataSet beforeData;              //объект, который содержит наши данные для инициализации бд перед выполнением теста.

    @Before
    public void setUp() throws Exception {      //в этом методе мы инициализируем данные, необходимые перед выполнением теста. Здесь мы определяем наш тестер
        tester = new JdbcDatabaseTester(prop.getProperty("db.driver"),
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"));
    }

    public DBUnitConfig(String name) {          //конструктор инициализирует нашу БД в системе для дальнейшего получения доступа и возможности в дальнейшем осуществлять взаимодействия.
        super(name);
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("db.config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {      //возвращает наш набор данных
        return beforeData;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {       //очищает БД после выполнения тестов
        return DatabaseOperation.DELETE_ALL;
    }

}
