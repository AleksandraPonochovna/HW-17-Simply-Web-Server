package servlets;

import templater.PageGenerator;
import user.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private AccountService account;

    public SignInServlet(AccountService account) {
        this.account = account;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(PageGenerator.instance().getPage("signin.html", account.getUserData()));
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf-8");
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            if (account.getUserData().containsKey(login) && account.getUserData().containsValue(password)) {
                resp.getWriter().println("Authorized: " + login);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.getWriter().println("Unauthorized");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
