package accounts;

import dbService.DBException;
import dbService.DBService;
import org.h2.jdbcx.JdbcDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    private final DBService dbService;

    String url = "jdbc:h2:./h2db";
    String name = "test";
    String pass = "test";

    JdbcDataSource ds = new JdbcDataSource();

    public AccountService(DBService dbService) {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        this.dbService = dbService;

        ds.setURL(url);
        ds.setUser(name);
        ds.setPassword(pass);
    }

    public void addNewUser(UserProfile userProfile) {
        try {
            dbService.addUser(userProfile.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public Map getLoginToProfile(){
        return loginToProfile;
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
