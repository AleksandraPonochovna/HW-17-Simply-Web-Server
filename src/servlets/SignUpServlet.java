package servlets;

import templater.PageGenerator;
import user.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private AccountService account;

    public SignUpServlet(AccountService account) {
        this.account = account;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(PageGenerator.instance().getPage("signup.html", account.getUserData()));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        account.getUserData().put(login, password);
        resp.getWriter().println("Authorized");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
