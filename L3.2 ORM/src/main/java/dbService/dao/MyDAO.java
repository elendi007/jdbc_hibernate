package dbService.dao;

import dbService.dataSets.User;
import org.eclipse.jetty.server.Authentication;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.List;

public class MyDAO {
    private Session session;

    public MyDAO(Session session){
        this.session = session;
    }

    public User getUser(long id){
        return (User) session.get(User.class, id);
    }

    public List<User> getAllUsers(){
        String hql = "FROM User";//это указание на класс

        Query query = session.createQuery(hql);
        List<User> users = query.list();

        return users;
    }
}
