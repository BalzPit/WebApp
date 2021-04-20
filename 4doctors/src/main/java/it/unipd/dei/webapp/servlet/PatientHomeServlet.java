package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.MedicalExaminationDAO;
import it.unipd.dei.webapp.dao.ProfileDAO;
import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.MedicalExamination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * Collect medical examinations of the logged patient and show them as a reminder
 */
public final class PatientHomeServlet extends AbstractDatabaseServlet {

    /**
     * Searches employees by their salary.
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

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{

        HttpSession session = req.getSession(false);
        String user_cf = (String) session.getAttribute("cf");

        Message m = null;
        String query = null;

        // retrieves the request parameter
        String requestType = req.getParameter("type");


        // If the request is not null...
        if (requestType != null){

            // If the request type is reminder
            if (requestType.equals("Examinations")){

                ArrayList<List<MedicalExamination>> medicalExaminations = null;
                List<MedicalExamination> pastMedicalExaminationList = null;
                List<MedicalExamination> futureMedicalExaminationList = null;

                try {
                    // creates a new object for accessing the database and searching the medical examinations
                    MedicalExaminationDAO test = new MedicalExaminationDAO(getDataSource().getConnection(), user_cf);
                    query = test.GET_MEDICAL_EXAMINATIONS_STATEMENT;
                    medicalExaminations = test.getMedicalExaminations();
                    pastMedicalExaminationList = medicalExaminations.get(0);
                    futureMedicalExaminationList = medicalExaminations.get(1);

                    m = new Message("Examinations successfully shown.");

                } catch (SQLException ex) {

                    m = new Message("Cannot search for medical examinations: unexpected error while accessing the database.\n The query which will be performed is "+query+ ", Request type: " + requestType,
                            "E200", ex.getMessage());
                }

                // stores the medical examination list and the message as a request attribute
                req.setAttribute("pastMedicalExaminationList", pastMedicalExaminationList);
                req.setAttribute("futureMedicalExaminationList", futureMedicalExaminationList);



            } else if (requestType.equals("Profile")){

                Patient patient = null;
                try{
                    ProfileDAO overview = new ProfileDAO(getDataSource().getConnection(), user_cf);
                    patient = overview.getPatient();

                    m = new Message("Profile Overview successfully entered.\n Request type: " + requestType);
                } catch (SQLException ex) {

                    m = new Message("Cannot search for personal information: unexpected error while accessing the database.\n The query which will be performed is "+query+ ", Request type: " + requestType,
                            "E200", ex.getMessage());
                }

                // stores the medical examination list and the message as a request attribute
                req.setAttribute("personalInfo", patient);

            }

            req.setAttribute("cf", user_cf);
            req.setAttribute("message", m);
            req.setAttribute("requestType", requestType);

            // forwards the control to the search-employee-result JSP
            req.getRequestDispatcher("/protected/jsp/patient/patient-homepage-reminder-overview.jsp").forward(req, res);

        } else{ // If the request type is null
            throw new IOException();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        doGet(req,res);
    }
}
