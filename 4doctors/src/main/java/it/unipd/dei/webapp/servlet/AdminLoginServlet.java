package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.LoginDAO;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.utils.InputFormatException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Log an admin in the webapp
 */
public final class AdminLoginServlet extends AbstractDatabaseServlet{

    /**
     * Log an admin into the webapp.
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // request parameters
        String username = null;
        String password = null;

        // model
        Message message = null;

        try{
            // retrieves the request parameters
            username = req.getParameter("username");
            password = req.getParameter("password");

            // Check if some parameters are null
            if(username == null || password == null) {
                throw new InputFormatException("One or more input parameters are null");
            }

            // Removing leading and trailing white space
            username = username.trim();

            // Check if some parameters are empty
            if(username.isEmpty() || password.isEmpty()) {
                throw new InputFormatException("One or more input parameters are empty");
            }

            // Log the admin
            boolean authenticated = LoginDAO.authenticateAdmin(username, password);

            if(authenticated){
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", "admin");

                // forwards the control to the homepage
                req.getRequestDispatcher("/protected/jsp/admin/admin-homepage.jsp").forward(req, res);
            }
            else {
                message = new Message("Error while authenticating the admin");
                req.setAttribute("message", message);

                // forwards the control to the login
                req.getRequestDispatcher("/jsp/admin-login.jsp").forward(req, res);
            }

            return;

        } catch (InputFormatException e) {
            message = new Message("Cannot log in the admin. Invalid input parameters",
                    "E100", e.getMessage());
        } catch (SQLException ex) {
            message = new Message("Cannot log in the admin: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        } catch (NamingException ex) {
            message = new Message("Impossible to access the connection pool to the database.",
                    "E203", ex.getMessage());
        }

        req.setAttribute("message", message);

        // forwards the control to the login
        req.getRequestDispatcher("/jsp/admin-login.jsp").forward(req, res);
    }
}
