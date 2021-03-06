package main;


import dbService.dao.MyDAO;
import dbService.dataSets.User;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception{

        System.out.println("\nHibernate");
        Configuration configuration = new Configuration();


        //установка своейств для соединения с базой
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/service?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql","none");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        //привязка класса с анотациями
        configuration.addAnnotatedClass(User.class);

        //получение фабрики сессий
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //получение сессии для соединения с нашей базой данных
        Session session = sessionFactory.openSession();

        //инициализация объекта DAO(data access object) полученной сессией
        MyDAO myDAO = new MyDAO(session);


        for (int i = 0; i < 15; i++) {
            myDAO.insertUser(new User("ignat" + i));
        }

        session.close();
        sessionFactory.close();
    }
}
