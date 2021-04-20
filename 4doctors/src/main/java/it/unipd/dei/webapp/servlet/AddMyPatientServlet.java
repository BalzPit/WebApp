package it.unipd.dei.webapp.servlet;


import it.unipd.dei.webapp.dao.ListMyPatientsDAO;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.utils.InputFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

public final class AddMyPatientServlet extends AbstractDatabaseServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String doctor_cf = (String) session.getAttribute("cf");

        String patient_cf = null;

        Message message = null;

        try {

            patient_cf = req.getParameter("patient_cf");

            if (patient_cf == null) {
                throw new InputFormatException("One or more input parameters are empty");
            }

            new ListMyPatientsDAO(getDataSource().getConnection(), doctor_cf).addMyPatient(doctor_cf, patient_cf);

        } catch (InputFormatException ex) {
            message = new Message("Cannot forward the request. Invalid input parameters",
                    "E100", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            message = new Message("Cannot forward the request: gender type is wrong.",
                    "E102", ex.getMessage());
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                message = new Message("Cannot forward the request: SQLException", "E300", ex.getMessage());
            } else {
                message = new Message("Cannot forward the request: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
            }
        }

        req.getRequestDispatcher("/list-my-patients").forward(req, resp);

    }
}