package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInServlet extends HttpServlet {
    private Statement statement;

    public SignInServlet(Statement statement) {
        this.statement = statement;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        try {
            try {
                statement.execute("use db_example");

                ResultSet resultSet = statement.executeQuery("select * from db_example.users where name = '"+ login + "'");
                resultSet.next();
                String user = resultSet.getString("name");

                if(user != null){
                    resp.getWriter().println("Authorized: "+login);
                }else {
                    resp.getWriter().println("Unauthorized");
                }
                resultSet.close();
            } catch (SQLException e) {
                resp.getWriter().println("Unauthorized");

                //e.printStackTrace();
            }
        } catch (Exception e) {
            resp.getWriter().println("Unauthorized");
            //e.printStackTrace();
        }



        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
