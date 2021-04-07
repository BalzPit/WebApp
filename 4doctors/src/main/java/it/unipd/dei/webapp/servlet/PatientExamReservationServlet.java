package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.CreateMedicalExaminationDatabase;
import it.unipd.dei.webapp.resource.MedicalExamination;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Allow the Patient to view a list of his/her own reserved
 * and past examinations, and to reserve new ones.
 *
 * @author Pietro Balzan
 */
public class PatientExamReservationServlet extends AbstractDatabaseServlet {

    //add a new examination to the database

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
            new CreateMedicalExaminationDatabase(getDataSource().getConnection(), newExamination)
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
        //TODO: check for already existing examinations with same primary key and throw exceptions if any are found


        // print result page
        // set the MIME media type of the response
        res.setContentType("text/html; charset=utf-8");

        // get a stream to write the response
        PrintWriter out = res.getWriter();

        // write the HTML page
        out.printf("<!DOCTYPE html>%n");

        out.printf("<html lang=\"en\">%n");
        out.printf("<head>%n");
        out.printf("<meta charset=\"utf-8\">%n");
        out.printf("<title>New Medical Examination</title>%n");
        out.printf("</head>%n");

        out.printf("<body>%n");
        out.printf("<h1>Medical Examination</h1>%n");
        out.printf("<hr/>%n");

        if(m.isError()) {
            out.printf("<ul>%n");
            out.printf("<li>error code: %s</li>%n", m.getErrorCode());
            out.printf("<li>message: %s</li>%n", m.getMessage());
            out.printf("<li>details: %s</li>%n", m.getErrorDetails());
            out.printf("</ul>%n");
        } else {
            out.printf("<p>%s</p>%n", m.getMessage());
            out.printf("<ul>%n");
            out.printf("<li>Doctor: %s</li>%n", newExamination.getDoctor_cf());
            out.printf("<li>Patient: %s</li>%n", newExamination.getPatient_cf());
            out.printf("<li>Date: %s</li>%n", newExamination.getDate());
            out.printf("<li>Time: %s</li>%n", newExamination.getTime());
            out.printf("</ul>%n");
        }

        out.printf("</body>%n");

        out.printf("</html>%n");

        // flush the output stream buffer
        out.flush();

        // close the output stream
        out.close();
    }
}
