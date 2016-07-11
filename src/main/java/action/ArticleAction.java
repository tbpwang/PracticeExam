package action;

import model.Article;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Administrator
 * Created by tbpwang
 * 2016/7/11.
 */
@WebServlet(urlPatterns = "/article")
public class ArticleAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "queryForAdmin":
                queryForAdmin(req, resp);
                break;
            case "queryForUser":
                queryForUser(req, resp);
                break;
            case "queryByKey":
                queryByKey(req, resp);
                break;
            case "queryForView":
                queryForView(req, resp);
                break;
            case "queryForEdit":
                queryForEdit(req, resp);
                break;
            case "create":
                create(req, resp);
                break;
            case "remove":
                remove(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
        }
    }

    private void queryForEdit(HttpServletRequest req, HttpServletResponse resp) {
        search(req, resp);
        try {
            resp.sendRedirect("/admin/edit.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryForView(HttpServletRequest req, HttpServletResponse resp) {
        search(req, resp);
        try {
            resp.sendRedirect("/admin/article.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryForUser(HttpServletRequest req, HttpServletResponse resp) {
        query(req, resp);
        try {
            resp.sendRedirect("/user/user.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryForAdmin(HttpServletRequest req, HttpServletResponse resp) {
        query(req, resp);
        try {
            resp.sendRedirect("/admin/admin.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_health.article ORDER BY id";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("abstract"), resultSet.getString("content"), resultSet.getString("createDate").substring(0, 10)));
            }
            req.getSession().setAttribute("articles", articles);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, statement, connection);
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_health.article WHERE id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            Article article = new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("abstract"), resultSet.getString("content"), resultSet.getString("createDate").substring(0, 10));
            req.getSession().setAttribute("article", article);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, statement, connection);
        }

    }


    private void queryByKey(HttpServletRequest req, HttpServletResponse resp) {
        String title_keyword = req.getParameter("title_keyword");
        String content_keyword = req.getParameter("content_keyword");

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_health.article WHERE title LIKE ? AND article.content LIKE ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + title_keyword + "%");
            statement.setString(2, "%" + content_keyword + "%");
            resultSet = statement.executeQuery();

            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("abstract"), resultSet.getString("content"), resultSet.getString("createDate").substring(0, 10)));
            }
            req.getSession().setAttribute("articles", articles);
            resp.sendRedirect("/user/user.jsp");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, statement, connection);
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        String sql = "DELETE FROM db_health.article WHERE id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            resp.sendRedirect("/article?action=queryForAdmin");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, statement, connection);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) {

        String title = req.getParameter("title").trim();
        String abstracts = req.getParameter("abstract").trim();
        String content = req.getParameter("content").trim();

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        String sql = "INSERT INTO db_health.article(title, abstract, content) VALUES (?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, abstracts);
            statement.setString(3, content);
            statement.executeUpdate();

            resp.sendRedirect("/article?action=queryForAdmin");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, statement, connection);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title").trim();
        String abstracts = req.getParameter("abstract").trim();
        String content = req.getParameter("content").trim();

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;
        String sql = "UPDATE db_health.article SET title = ?, abstract = ?, content = ? WHERE id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, abstracts);
            statement.setString(3, content);
            statement.setInt(4, id);
            statement.executeUpdate();

            resp.sendRedirect("/article?action=queryForAdmin");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, statement, connection);
        }
    }

}
