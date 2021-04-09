package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.CreateMedicalExaminationDAO;
import it.unipd.dei.webapp.dao.MedicalExaminationsListDAO;
import it.unipd.dei.webapp.resource.MedicalExamination;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Allow the Patient to view a list of his/her own reserved
 * and past examinations.
 *
 * @author Pietro Balzan
 */
public class PatientExaminationsListServlet extends AbstractDatabaseServlet {

    /**
     * Retrieve lists of future and past examinations for the logged patient
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
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        //request parameters

        //fixed patient and doctor, TODO: will be set dynamically later on: patient depends on who's logged in
        String patient_cf = "GVNPVAG4RE44S3D9"; //piva

        //get current date as sql.date
        Date date = new java.sql.Date(new java.util.Date().getTime());

        // model
        List<MedicalExamination> futureExaminations = null;
        List<MedicalExamination> pastExaminations = null;
        Message m;

        try{
            //get lists of future and past examination of the logged patient
            MedicalExaminationsListDAO medicalExaminationsListDAO =
                    new MedicalExaminationsListDAO(getDataSource().getConnection(), patient_cf, date);

            futureExaminations = medicalExaminationsListDAO.searchFutureExaminations();
            //pastExaminations = medicalExaminationsListDAO.searchPastExaminations();

            m = new Message(String.format("Examinations successfully retrieved."));
        }
        catch (SQLException ex) {
            m = new Message("Failed to retrieve examinations lists: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        //return JSP page as result
        // stores the employee and the message as a request attribute
        req.setAttribute("futureExaminationsList", futureExaminations);
        //req.setAttribute("pastExaminationsList", pastExaminations);
        req.setAttribute("message", m);

        // forwards the control to the create-employee-result JSP
        req.getRequestDispatcher("/jsp/medical-examinations-list-result.jsp").forward(req, res);
    }
}