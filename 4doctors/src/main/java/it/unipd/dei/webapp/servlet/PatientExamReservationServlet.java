package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.CreateMedicalExaminationDAO;
import it.unipd.dei.webapp.resource.MedicalExamination;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Allow the Patient to view a list of his/her own reserved
 * and past examinations, and to reserve new ones.
 *
 * @author Pietro Balzan
 */
public class PatientExamReservationServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new examination (visita) into the database.
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

        //fixed patient and doctor, TODO: will be set dynamically later on: patient depends on who's logged in and doctor will be chosen from the list
        String doctor_cf = "SON01MED1C0G1UR0"; //rossi
        String patient_cf = "GVNPVAG4RE44S3D9"; //piva

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

        //return JSP page as result
        // stores the employee and the message as a request attribute
        req.setAttribute("examination", newExamination);
        req.setAttribute("message", m);

        // forwards the control to the create-employee-result JSP
        req.getRequestDispatcher("/jsp/create-medical-examination-result.jsp").forward(req, res);
    }
}
