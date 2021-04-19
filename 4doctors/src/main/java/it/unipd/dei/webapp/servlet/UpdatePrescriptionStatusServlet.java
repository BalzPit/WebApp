package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.PrescriptionDAO;
import it.unipd.dei.webapp.resource.Prescription;
import it.unipd.dei.webapp.resource.Prescription.Status;
import it.unipd.dei.webapp.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class UpdatePrescriptionStatusServlet extends AbstractDatabaseServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Message m = null;

        try {

            String id = req.getParameter("id");
            Status status = Status.valueOf(req.getParameter("status"));

            //check parameters
            if(id == null) throw new IllegalArgumentException("All parameters must be not NULL");

            if((status != Status.APPROVED) && (status != Status.PENDING) && (status != Status.REJECTED)) throw new IllegalArgumentException("Status is "+ status +". Status parameter must be 'APPROVED', 'PENDING', or 'REJECTED'");

            new PrescriptionDAO(getDataSource().getConnection()).updatePrescriptionStatus(id, status);

            m = new Message("Prescription " + id + " successfully updated.");

        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot update the prescription. Invalid input parameters: status.", "E100", ex.getMessage());
        } /*catch (NumberFormatException ex) {
            m = new Message("Cannot update the prescription. Invalid input parameters: id must be integer.", "E100", ex.getMessage());
        }*/ catch (SQLException ex) {
            m = new Message("Cannot update the prescription: unexpected error while accessing the database.", "E200", ex.getMessage());
        }

        // stores the message as a request attribute
        req.setAttribute("message", m);
        // forwards the control to the PrescriptionManagerServlet
        req.getRequestDispatcher("/doctor/prescription-manager").forward(req, res);
        //req.getRequestDispatcher("jsp/prescription-update.jsp").forward(req, res);
    }

}