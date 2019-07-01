package servlets;

import templater.PageGenerator;
import user.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SignInServlet extends HttpServlet {
    private AccountService account;

    public SignInServlet(AccountService account) {
        this.account = account;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String generator = PageGenerator.instance().getPage("signin.html", account.getUserData());
        response.getWriter().println(generator);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");
        if (Objects.isNull(login) || login.isEmpty() || Objects.isNull(password) || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            if (account.getUserData().containsKey(login) && account.getUserData().containsValue(password)) {
                response.getWriter().println("Authorized: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
