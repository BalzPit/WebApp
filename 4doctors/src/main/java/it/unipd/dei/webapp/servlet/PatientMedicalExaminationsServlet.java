package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.CreateMedicalExaminationDAO;
import it.unipd.dei.webapp.dao.MedicalExaminationDAO;
import it.unipd.dei.webapp.resource.MedicalExamination;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Allow the Patient to view a list of his/her own reserved
 * and past examinations, and to reserve new ones.
 *
 * @author Pietro Balzan
 */
public class PatientMedicalExaminationsServlet extends AbstractDatabaseServlet {

    /**
     * Loads page and list of examinations
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
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String patient_cf = (String) session.getAttribute("cf");

        //get current date as sql.date
        Date date = new java.sql.Date(new java.util.Date().getTime());

        // model
        ArrayList<List<MedicalExamination>> examinations = null;
        List<MedicalExamination> futureExaminations = null;
        List<MedicalExamination> pastExaminations = null;
        Message m;

        try{
            //get lists of future and past examination of the logged patient
            MedicalExaminationDAO medicalExaminationDAO =
                    new MedicalExaminationDAO(getDataSource().getConnection(), patient_cf);

            //retrieve examinations from the db
            examinations = medicalExaminationDAO.getMedicalExaminations();

            pastExaminations = examinations.get(0);
            futureExaminations = examinations.get(1);

            m = new Message(String.format("Examinations successfully retrieved."));
        }
        catch (SQLException ex) {
            m = new Message("Failed to retrieve examinations lists: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // return JSP page as result
        req.setAttribute("futureExaminationsList", futureExaminations);
        req.setAttribute("pastExaminationsList", pastExaminations);
        req.setAttribute("message", m);

        // forwards the control back to the patient-medical-examinations JSP
        req.getRequestDispatcher("/protected/jsp/patient/patient-medical-examinations.jsp").forward(req, res);

    }



    /**
     * Create a new examination (visita) into the database and reload the page.
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

        //fixed patient and doctor, TODO: doctor will be chosen from the list
        HttpSession session = req.getSession(false);
        String patient_cf = (String) session.getAttribute("cf");

        String doctor_cf = "SON01MED1C0G1UR0"; //rossi

        Date date = null;
        Time time = null;
        String outcome = "epico";

        // model
        MedicalExamination newExamination  = null;
        Message m = null;

        try{
            //convert date and time strings to java.sql format for storage in the database
            java.util.Date selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
            java.util.Date selectedTime = new SimpleDateFormat("hh:mm").parse(req.getParameter("time"));

            date =  new java.sql.Date(selectedDate.getTime());
            time =  new java.sql.Time(selectedTime.getTime());

            // creates a new medical examination from the request parameters
            newExamination = new MedicalExamination(doctor_cf, patient_cf, date, time, outcome);
            // creates a new object for accessing the database and stores the patient
            new CreateMedicalExaminationDAO(getDataSource().getConnection(), newExamination)
                    .createMedicalExamination();

            m = new Message(String.format("Examination successfully added to the database."));

        }
        catch (ParseException ex){
            m = new Message("Cannot create the new Examination. Date or Time parsing failed.",
                    "E100", ex.getMessage());
        }
        catch (SQLException ex) {
                m = new Message("Cannot create the new Visita: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
        }

        //return JSP page with all examinations lists
        doGet(req, res);
    }
}
