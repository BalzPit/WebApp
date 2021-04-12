package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.database.PrescriptionManagerDatabase;
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

public class PrescriptionManagerServlet extends AbstractDatabaseServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<Prescription> pending = null, approved = null, rejected = null;
        Message m = null;

        try {

            // TODO get doctor cf from HtppSession

            String doctor = "SON01MED1C0G1UR0";

            // Retrieve all prescriptions related to the current doctor

            pending = new PrescriptionManagerDatabase(getDataSource().getConnection(), doctor, Status.PENDING).getPrescriptionsByStatus();
            approved = new PrescriptionManagerDatabase(getDataSource().getConnection(), doctor, Status.APPROVED).getPrescriptionsByStatus();
            rejected = new PrescriptionManagerDatabase(getDataSource().getConnection(), doctor, Status.REJECTED).getPrescriptionsByStatus();

            m = new Message("Prescriptions successfully retrieved. \nPending: "+pending.size()+"; \nApproved: "+approved.size()+"; \n Rejected: "+rejected.size());

        } catch (SQLException ex) {
            m = new Message("Cannot search for prescriptions: unexpected error while accessing the database.", "E200", ex.getMessage());
        }

        // stores the pendig, approved and rejected prescriptions list and the message as a request attribute
        req.setAttribute("pending", pending);
        req.setAttribute("approved", approved);
        req.setAttribute("rejected", rejected);
        req.setAttribute("message", m);

        // forwards the control to the prescription-manager JSP
        req.getRequestDispatcher("/jsp/prescription-manager.jsp").forward(req, res);
    }
 
}