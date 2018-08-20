package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import sun.security.pkcs11.Secmod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    //private final AccountService accountService;
    private final DBService dbService;
    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        try {
            dbService.addUser(login);
            //resp.getWriter().println("Authorized: " + login);
        } catch (DBException e) {
            e.printStackTrace();
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
