package action;

import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Administrator
 * Created by tbpwang
 * 2016/7/9.
 */

@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("login")) {
            login(req, resp);
        }
        if (action.equals("register")) {
            register(req, resp);
        }
        if (action.equals("logout")) {
            logout(req, resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephone").trim();
        String address = req.getParameter("address").trim();

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement statement = null;
        String sql = "INSERT INTO db_health.user(username, password, telephone, address) VALUES (?,?,?,?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, telephone);
            statement.setString(4, address);
            statement.executeUpdate();
            sendRedirect(resp, "");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,statement,connection);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_health.user WHERE username=?AND password=?";
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getBoolean("isAdmin") ? "admin" : "user";
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("username", username);
                req.getSession().setAttribute("password", password);
                try {
                    sendRedirect(resp, role);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                req.setAttribute("message", "用户名或密码错误");
                try {
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, prepareStatement, connection);
        }

    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        try {
            resp.sendRedirect("index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        private void sendRedirect(HttpServletResponse resp, String role) throws IOException {
        switch (role) {
            case "admin":
                resp.sendRedirect("/article?action=queryForAdmin");
                break;
            case "user":
                resp.sendRedirect("/article?action=queryForUser");
                break;
            default:
                resp.sendRedirect("/index.jsp");
        }
    }
}
