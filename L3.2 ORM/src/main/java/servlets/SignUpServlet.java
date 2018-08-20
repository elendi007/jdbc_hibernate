package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");


        accountService.addNewUser(new UserProfile(login, pass));
        UserProfile profile = accountService.getUserByLogin(login);


        accountService.addSession(req.getSession().getId(), profile);

        Gson gson = new Gson();

        String json = gson.toJson(profile);

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(json);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
